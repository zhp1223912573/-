package 递归;

/**
 * @author zhp
 * @date 2022-07-30 0:49
 * https://leetcode.cn/problems/wildcard-matching/
 */
public class 通配符匹配_lc_44 {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean f[][] = new boolean[m+1][n+1];
        f[0][0] = true;
        for(int i=1;i<=n;i++){
            if(p.charAt(i-1)=='*'){
                f[0][i]=true;
            }else{
                break;
            }
        }

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(p.charAt(j-1)=='*'){
                    f[i][j] = f[i-1][j] || f[i][j-1];
                }else if(p.charAt(j-1)=='?'||(s.charAt(i-1)==p.charAt(j-1))){
                    f[i][j] = f[i-1][j-1];
                }
            }
        }
        return f[m][n];

    }


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
            return si!=s.length && (e[ei]==s[si] || e[ei]=='?') &&process(s,e,si+1,ei+1);
        }

        //2.当前通配符字符的后一个字符是*，也就是e[ei+1]='*',需要尝试多种匹配情况下是否符合完全匹配
        while(si!=s.length&&(e[ei]==s[si])){
            //例str:aaab exp:a*b
            //str[0]==exp[i]，所以进入循环尝试各种情况
            if(process(s,e,si,ei+1)){//不匹配*+字符
                return true;
            }
            //此处开始让a*p形成一个a，两个a等直到符合条件，或者不符合退出循环
            si++;
        }

        //此处的递归可能是两种可能
        //1：如果出现了*前的字符与si字符不一致，那么直接假定.*为空，向后找到递归匹配
        //2：在上述可能性2中剩下了最后一种尝试还么匹配就退出来了，需要继续尝试这种匹配
        return process(s,e,si,ei+1);


    }
}
