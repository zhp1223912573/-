package 字符串;

/**
 * @author zhp
 * @date 2023-04-03 19:45
 * https://leetcode.cn/problems/ones-and-zeroes/
 */
public class 一和零_lc_474 {
    /**
     * 01背包
     * 将m，n看做背包容量，为了保证0的数量不大于m，且1的数量不大于n。
     * 可以视为一个背包问题。
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int dp[][] = new int[m+1][n+1];
        for(int i=0;i<len;i++){
            int zero = 0;
            int one  = 0;
            for(char ch : strs[i].toCharArray()){
                if(ch=='0') zero++;
                else one ++;
            }
            for(int j=m;j>=zero;j--){
                for(int k=n;k>=one;k--){
                    dp[j][k] = Math.max(dp[j][k],dp[j-zero][k-one]+1);
                }
            }
        }
        return dp[m][n];
    }
}
