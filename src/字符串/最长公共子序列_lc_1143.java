package 字符串;

/**
 * @author zhp
 * @date 2022-07-11 15:02
 * 请注意区分子串和子序列的不同，给定两个字符串str1和str2，求两个字符串
 * 的最长公共子序列
 * https://leetcode.cn/problems/longest-common-subsequence/
 */
public class 最长公共子序列_lc_1143 {
    /**
     * dp
     * dp[i][j]表示str1字符串0-i字符串以及str2字符0-j字符串，最长的公共子序列
     * 通过可能性分析，可得dp[i][j]取决于四种情况
     * 1.不以i，j结尾，与str1[i]以及str2[j]无关
     * dp[i][j] = dp[i-1][j-1]
     * 2.不以i结尾，以j结尾，与str1[i]无关，str2[j]有关
     * dp[i][j] = dp[i-1][j-1]
     * 3.不以j结尾，以i结尾，与str1[i]有关，str2[j]无关
     * dp[i][j] = dp[i-1][j-1]
     * 4.以i，j结尾，与str1[i]以及str2[j]有关
     * dp[i][j] = dp[i-1][j-1]+1
     * 取上述情况最大值
     */

    public static String lcse(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        //得到dp数组，获取最长公共子序列的长度
        int[][] dp = getdp(chs1, chs2);
        int m = chs1.length - 1;
        int n = chs2.length - 1;
        char[] res = new char[dp[m][n]];
        int index = res.length - 1;
        //推导出最长公共子序列
        while (index >= 0) {
            if (n > 0 && dp[m][n] == dp[m][n - 1]) {
                n--;
            } else if (m > 0 && dp[m][n] == dp[m - 1][n]) {
                m--;
            } else {
                res[index--] = chs1[m];
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }

    public static int[][] getdp(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < str1.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        for (int j = 1; j < str2.length; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }

    /**
     * f(i,j)表示以0-i结尾，0-j结尾的字符串的最长公共子序列长度
     *
     * if(nums1[i]==nums2[j])
     *      f(i,j) = f(i-1,j-1)+1
     * else
     *      f(i,j) = max(f(i-1,j),f(i,j-1))
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int dp[][] = new int[len1+1][len2+1];
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j] = Math.max(dp[i][j],dp[i-1][j-1]+1);
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }
    public static void main(String[] args) {
        String str1 = "A1BC2D3EFGH45I6JK7LMN";
        String str2 = "12OPQ3RST4U5V6W7XYZ";
        System.out.println(lcse(str1, str2));

    }
}
