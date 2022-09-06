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

    public static void main(String[] args) {

        零钱兑换_lc_322 t = new 零钱兑换_lc_322();
        t.coinChange1(new int[]{1,2,5},10);
    }

}
