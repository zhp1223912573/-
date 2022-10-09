package 递归;

/**
 * @author zhp
 * @date 2022-07-12 15:49
 * 判定一个由[a-z]字符构成的字符串和一个包含'?'和'*'通配符的字符串是否匹配。
 * 通配符'?'匹配任意单一字符,'*'匹配任意多个字符包括0个字符。
 * 字符串长度不会超过100，字符串不为空。
 * 输入描述：
 * 字符串 str 和包含通配符的字符串 pattern。1 <= 字符串长度 <= 100输出描述：
 * true 表示匹配，false 表示不匹配
 * https://leetcode.cn/problems/regular-expression-matching/
 */
public class 字符匹配_lc_10 {
    /**
     * 递归
     * 分析各种情况下的字符匹配情形
     */
    /**
     * 检验原字符串和通配表式的合法性
     * @param s
     * @param e
     * @return
     */
    public static boolean isValid(char[] s,char[] e){
        //匹配字符串不应该包含"*","."
        for(int i=0;i<s.length;i++){
            if(s[i]=='*' || s[i]=='.'){
                return false;
            }
        }

        //"*"前必须包含非"*"字符
        for(int i=0;i<e.length;i++){
            if(e[i]=='*'&&(i==0 || e[i-1]=='*')){
                return false;
            }
        }
        return true;
    }
    /**检验是否匹配
     *
     * @param str 匹配字符串
     * @param exp 通配符字符串
     * @return
     */
    public static boolean isMatch(String str,String exp){
        if(str==null || exp==null){
            return false;
        }

        if(!isValid(str.toCharArray(),exp.toCharArray())){
            return false;
        }

        return process(str.toCharArray(),exp.toCharArray(),0,0);
    }
    /**递归匹配过程
     *
     * @param s
     * @param e
     * @param si 指向正在匹配的原字符串的字符
     * @param ei 指向正在匹配的通配符字符串的字符
     * @return
     */
    private static boolean process(char[] s, char[] e, int si, int ei) {
        //base：当前到达了通配符字符串的最后一个字符的后一个位置，检查是否匹配成功
        if(ei==e.length){
            //si也指向了最后的字符后的位置时，说明匹配成功
            return s.length==si;
        }

        //1.当前通配符字符的后一个字符不是*,e[ei+1]!='*'
        if(ei+1==e.length ||(e[ei+1]!='*')){
            //原字符不是最后一个字符，当前匹配字符和原字符一致或者原字符为.时，两者匹配，
            //递归判断后续字符是否匹配。
            return si!=s.length && (e[ei]==s[si] || e[ei]=='.') &&process(s,e,si+1,ei+1);
        }

        //2.当前通配符字符的后一个字符是*，也就是e[ei+1]='*',需要尝试多种匹配情况下是否符合完全匹配
        while(si!=s.length&&(e[ei]==s[si]|| e[ei]=='.')){
                //例str:aaab exp:a*b
                //str[0]==exp[i]，所以进入循环尝试各种情况
                if(process(s,e,si,ei+2)){//不匹配*+字符
                    return true;
                }
                //此处开始让a*p形成一个a，两个a等直到符合条件，或者不符合退出循环
                si++;
        }

        //此处的递归可能是两种可能
        //1：如果出现了*前的字符与si字符不一致，那么直接假定.*为空，向后找到递归匹配
        //2：在上述可能性2中剩下了最后一种尝试还么匹配就退出来了，需要继续尝试这种匹配
        return process(s,e,si,ei+2);


    }


    /**动规
     * 没别的，cv的，自己看去吧：
     * https://leetcode.cn/problems/regular-expression-matching/solution/zheng-ze-biao-da-shi-pi-pei-by-leetcode-solution/
     */
    public boolean isMatch1(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean f[][] = new boolean[m+1][n+1];
        f[0][0] = true;

        for(int i=0;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(p.charAt(j-1)=='*'){
                    f[i][j] = f[i][j-2];
                    if(matches(s,p,i,j-1)){
                        f[i][j] = f[i][j]||f[i-1][j];
                    }
                }else{
                    if(matches(s,p,i,j)){
                        f[i][j] |= f[i-1][j-1];
                    }
                }
            }
        }
        return f[m][n];
    }

    private boolean matches(String s, String p, int i, int j) {
        if(i==0){
            return false;
        }

        if(p.charAt(j-1)=='.') return true;

        return p.charAt(j-1)==s.charAt(i-1);
    }

    public static void main(String[] args) {
        String str = "aab";
        String exp = "c*a*b";
        System.out.println(isMatch(str, exp));
    }
}
