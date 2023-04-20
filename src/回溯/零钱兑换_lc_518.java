package 回溯;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2022-07-26 20:42
 * https://leetcode.cn/problems/coin-change-ii/comments/
 */
public class 零钱兑换_lc_518 {
    /**
     * 计搜
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int n =coins.length;
        dp = new int[n][amount+1];
        for(int i=0;i<n;i++) Arrays.fill(dp[i],-1);
        return dfs(0,0,amount,coins);
    }
    int dp[][];
    public int dfs(int index,int sum,int amount,int []coins){
        if(sum>=amount){
            return sum==amount?1:0;
        }

        if(dp[index][sum]!=-1) return dp[index][sum];
        int ans = 0;
        for(int i=index;i<coins.length;i++){
            ans+=dfs(i,sum+coins[i],amount,coins);
        }
        return dp[index][sum] = ans;
    }
    /**
     * 完全背包
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change2(int amount, int[] coins) {
        int n = coins.length;
        int dp[][] = new int[n+1][amount+1];
        dp[0][0] = 1;
        for(int i=1;i<=n;i++){
            for(int j=0;j<=amount;j++){
                if(j>=coins[i-1]){
                    dp[i][j] = dp[i-1][j] +dp[i][j-coins[i-1]];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][amount];
    }

    public int change3(int amount, int[] coins) {
        int n = coins.length;
        int dp[] = new int[amount+1];
        dp[0] = 1;
        for(int i=1;i<=n;i++){
            for(int j=coins[i-1];j<=amount;j++){
                dp[j] +=dp[j-coins[i-1]];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        零钱兑换_lc_518 a = new 零钱兑换_lc_518();

    }
}
