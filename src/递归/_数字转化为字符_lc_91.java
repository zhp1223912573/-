package 递归;

/**
 * @author zhp
 * @date 2022-07-03 17:12
 *
 * 将给定的数转换为字符串，原则如下：1对应 a，2对应b，…..26对应z，例如12258
 * 可以转换为"abbeh", "aveh", "abyh", "lbeh" and "lyh"，个数为5，编写一个函
 * 数，给出可以转换的不同字符串的个数
 */
public class _数字转化为字符_lc_91 {

    /**递归
     *
     * 分类讨论：
     * 1.当前数字为0，则则直接返回0，因为0没有对应的字符
     * 2.除0外的数字，每个字符都可以分两类讨论：
     *      只计算当前字符，
     *      计算当前字符和后一个字符
     */
    public static int process(char[] chars,int index){
        //到达最后一个字符，说明当前组合是成功的
        if(index==chars.length){
            return 1;
        }

        if (chars[index] == '0') {//单单一个0，不合理，需要直接返回
            return 0;
        }

        int res = process(chars,index+1);//以当前字符为一种搭配方式
        if(index==chars.length-1){//不存在后续字符
            return res;
        }

        if((chars[index]-'0')*10+(chars[index+1]-'0')<27){//当前字符与下一个字符搭配时处于1-26的区间，则可以将搭配
            res += process(chars,index+2);
        }

        return res;
    }

    /**dp 该方法未通过
     *
     * dp表示以当前字符结尾时，可以组合的最大字符串种类个数。
     * 最后返回dp[n-1]即可。
     *
     *
     *
     */
    public static int process1(char [] chars){
        if(chars.length==0 || chars[0]=='0'){
            return 0;
        }

        int []dp = new int[chars.length];
        dp[0]=1;

        if((chars[0]-'0')*10+(chars[1]-'0')<27){
            dp[1]=2;
        }else{
            dp[1]=0;
        }

        if(chars.length<3){
            return dp[1];
        }

        for(int i=2;i<chars.length;i++){
            if(chars[i]=='0'){
                if(((chars[i-1]-'0')*10+(chars[i]-'0'))<27){
                    dp[i]=dp[i-2];
                }else{
                    return 0;
                }
            }else{
                if((chars[i-1]-'0')*10+(chars[i]-'0')<27&&chars[i-1]!='0'){

                    dp[i]=dp[i-2]+dp[i-1];
                }else{
                    dp[i]=dp[i-1];
                }
            }
        }

        return dp[chars.length-1];
    }

    /**力扣官方答案
     *  链接：https://leetcode.cn/problems/decode-ways/solution/jie-ma-fang-fa-by-leetcode-solution-p8np/
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' &&
                    ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }

    public static void main(String[] args) {
        System.out.println(process("12258".toCharArray(),0));
        System.out.println(process1("12258".toCharArray()));
    }
}
