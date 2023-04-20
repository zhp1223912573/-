package 递归;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2022-09-07 14:40
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * 相对于动态规划：122.买卖股票的最佳时机II，本题只需要在计算卖出操作的时候减去手续费就可以了，代码几乎是一样的。
 */
public class 买卖股票的最佳时机含手续费_lc_714 {
    /**
     * 记搜
     */
    public int maxProfit2(int[] prices, int fee) {
        dp = new int[prices.length][3];

        sub = fee;
        for(int i=0;i<prices.length;i++){
            Arrays.fill(dp[i],-1);
        }
        return dfs(prices,0,0);

    }
    int dp[][];
    int sub;
    public int dfs(int[] prices,int index,int status){
        if(index==prices.length ) return 0;
        if(dp[index][status]!=-1) return dp[index][status];
        if(status==0){
            return dp[index][status]=Math.max(dfs(prices,index+1,0),dfs(prices,index+1,1)-prices[index]);
        }else{
            return dp[index][status]=Math.max(dfs(prices,index+1,1),dfs(prices,index+1,0)+prices[index]-sub);
        }
    }

    /**
     * dp
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices,int fee) {
        if(prices==null||prices.length<=1) return 0;
        int len = prices.length;
        int dp[][] = new int[len][2];
        dp[0][0] = -prices[0];
        for(int i=1;i<len;i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]-prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]+prices[i]-fee);
        }

        return Math.max(dp[len - 1][0], dp[len - 1][1]);

    }
}
