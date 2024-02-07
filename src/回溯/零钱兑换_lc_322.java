package 回溯;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2022-07-26 19:55
 * https://leetcode.cn/problems/coin-change/
 */
public class 零钱兑换_lc_322 {

    /**
     * 记忆搜索
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int nums[] = new int[coins.length];
        return  backtarct(coins,amount,nums);

    }
    public int backtarct(int coins[],int amount,int nums[]){

        if(amount<0){
            return -1;
        }

        if(amount==0){
            return 0;
        }

        if(nums[amount-1]!=0){
            return nums[amount-1];
        }
        int min = Integer.MAX_VALUE;
        for(int i=0;i<coins.length;i++){
           int res = backtarct(coins,amount-coins[i],nums);
           if(res>=0&&res<min){
               min = res+1;
           }
        }
        nums[amount-1] = (min == Integer.MAX_VALUE ? -1 : min);
        return nums[amount-1];

    }

    /**
     * dp
     */
    public int coinChange1(int[] coins, int amount){
        if(coins.length==0){
            return -1;
        }

        int dp[] = new int[coins.length+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1;i<=amount;i++){
            for(int j=0;j<coins.length;j++){
                if(i-coins[j]>=0 ){
                    dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                }
            }

        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /**
     * 记搜
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange6(int[] coins, int amount) {
        dp = new int[amount];
        Arrays.fill(dp,-1);
        int ans =  dfs(coins,amount,0) ;
        return ans==10010?-1:ans;
    }

    int dp[];
    public int dfs(int []coins,int amount,int count){
        if(amount<=count) {
            return amount==count?0:10010;
        }

        int ans = 10010;
        if(dp[count]!=-1) return dp[count];
        for(int i=0;i<coins.length;i++){
            if(count<=amount-coins[i]){
                ans = Math.min(ans,dfs(coins,amount,count+coins[i])+1);
            }

        }
        return dp[count] = ans;
    }

    public int coinChange5(int[] coins, int amount) {
        int n = coins.length;
        int dp[][] = new int[n+1][amount+1];
        for(int i=0;i<n;i++)  Arrays.fill(dp[0],10010);
        dp[0][0] = 0;
        for(int i=1;i<=n;i++){
            for(int j=0;j<=amount;j++){
                for(int k=0;k<=j/coins[i-1];k++){
                    dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-coins[i-1]*k]+1);
                }
            }
        }
        return dp[n][amount]==10010?-1:dp[n][amount];
    }


    /**
     * 完全背包
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange3(int[] coins, int amount) {
        int n = coins.length;
        int dp[][] = new int[n+1][amount+1];
        for(int i=0;i<n;i++)  Arrays.fill(dp[i],10010);
        dp[0][0] = 0;
        for(int i=1;i<=n;i++){
            for(int j=0;j<=amount;j++){
                if(j>=coins[i-1])
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][j-coins[i-1]]+1);
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
        return dp[n][amount]==10010?-1:dp[n][amount];
    }

    /**
     * 完全背包优化
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange4(int[] coins, int amount) {
        int n = coins.length;
        int dp[] = new int[amount+1];
        Arrays.fill(dp,10010);
        dp[0] = 0;
        for(int i=1;i<=n;i++){
            for(int j=coins[i-1];j<=amount;j++){
                dp[j] = Math.min(dp[j],dp[j-coins[i-1]]+1);
            }
        }
        return dp[amount]==10010?-1:dp[amount];
    }

    public static void main(String[] args) {

        零钱兑换_lc_322 t = new 零钱兑换_lc_322();
        t.coinChange1(new int[]{1,2,5},10);
    }

}
