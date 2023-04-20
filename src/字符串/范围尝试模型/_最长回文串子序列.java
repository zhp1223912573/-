package 字符串.范围尝试模型;

/**
 * @author zhp
 * @date 2022-07-11 16:09
 * 给定一个字符串str，求最长的回文子序列。注意区分子序列和子串的不同
 *
 *
 */
public class _最长回文串子序列 {
    /**
     * dp
     * 设定二维数组dp，dp[i][j]表示str[i...j]子字符串上最长的回文子序列长度
     * 预设情况：
     *      j-i=1以及j-i=0的情况都可以直接判断，即范围为2以及1的情况。
     *
     * i恒小于等于j，所以二维数组的下三角部分无效，
     * 经过可能性分析，有以下四种情况：
     *      1.形成的最长回文子序列不以i开头，不以j结尾，则
     *      dp[i][j] = dp[i+1][j-1]
     *      2.形成的最长回文子序列不以i开头，以j结尾，则
     *      dp[i][j] = dp[i+1][j]
     *      3.形成的最长回文子序列以i开头，不以j结尾，则
     *      dp[i][j] = dp[i][j-1]
     *      4.形成的最长回文子序列以i开头，以j结尾，则
     *      dp[i][j] = dp[i+1][j-1] +2(新增的首尾字符）
     * 取上述四种情况最大值
     *
     * 最终dp[0][j-1](也就是整个字符串范围）就是最大值。
     */
    public static int maxLen2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[][] dp = new int[str.length][str.length];
        for (int i = 0; i < str.length; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < str.length - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int i = str.length - 2; i >= 0; i--) {
            for (int j = i + 2; j < str.length; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                if (str[i] == str[j]) {
                    dp[i][j] = Math.max(dp[i + 1][j - 1] + 2, dp[i][j]);
                }
            }
        }
        return dp[0][str.length - 1];
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int dp[][] = new int[n][n];
        int cnt = 0;
        for(int j=0;j<n;j++){
            for(int i=j;i>=0;i--){
                if(i==j) dp[i][j] = 1;
                else if(i+1==j) dp[i][j] = s.charAt(i)==s.charAt(j)?2:1;
                else if(s.charAt(i)==s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1]+2;
                }else{
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        String test = "A1BC2D33FG2H1I";
         //System.out.println(maxLen1(test));
        System.out.println(maxLen2(test));
    }


}
