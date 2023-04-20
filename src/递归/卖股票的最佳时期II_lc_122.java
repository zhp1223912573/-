package 递归;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2022-09-07 13:09
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 * 股票系列第二题
 * 相比第一题，股票可以重复购买，但最多持有一笔交易，也就是找到多次交易下的最大收益。
 * 本质上和第一题相近，都是通过记录持有和不持有股票的动态数组来记录最大收益的状态，只是可以重复交易。
 *  《状态定义》
 * 使用dp[i][0]表示第i天持有股票，dp[i][1]表示不持有股票。
 *  《状态分析》
 * 若第i天持有股票:
 *      可能i-1天就持有股票，维持昨天的状态，dp[i-1][0]时状态。
 *      可能i-1天不持有股票，今天刚刚购入股票，则需要昨天不持有股票时状态dp[i-1][1]-今天买入股票的价钱prices[i]
 *      取上述情况较大值。
 * 若第i天不持有股票：
 *      可能i-1天持有股票，今天刚好卖出，则需要昨天持有股票时的最大收益dp[i-1][0]+卖出今天的股票prices[i]的和
 *      可能昨天就不持有股票，维持昨天的状态，取dp[i-1][1]
 *      《初始状态》
 *    第一天持有股票，则dp[0][0]=-prices[0],
 *    第一天不持有股票，则dp[0][1]=0
 */
public class 卖股票的最佳时期II_lc_122 {

    /**
     * 记搜
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        dp = new int[prices.length][2];
        for(int i=0;i<prices.length;i++){
            Arrays.fill(dp[i],-1);
        }
        return dfs(0,prices,0);
    }
    int dp[][];

    /**
     * stats表示当前状态
     * 0-没有买入
     * 1-第一次卖出
     * 2-终止
     * @param index
     * @param prices
     * @param status
     * @return
     */
    public int dfs(int index,int [] prices,int status){
        if(index==prices.length) return 0;
        if(dp[index][status]!=-1) return dp[index][status];
        if(status==0){
            return dp[index][status] = Math.max(dfs(index+1,prices,0),dfs(index+1,prices,1)-prices[index]);
        }
        return dp[index][status] =  Math.max(dfs(index+1,prices,1),dfs(index+1,prices,0)+prices[index]);

    }

    /**dp
     * 综上分析
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices==null||prices.length<=1) return 0;
        int len = prices.length;
        int dp[][] = new int[len][2];
        dp[0][0] = -prices[0];
        for(int i=1;i<len;i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]-prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]+prices[i]);
        }

        return dp[len-1][1];

    }

    /**
     * 贪心，只要第二天能收入，就买入前一天的
     * @param prices
     * @return
     */
    public int maxProfit4(int[] prices) {
        int ans = 0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]-prices[i-1]>0) ans+=prices[i]-prices[i-1];
        }
        return ans;
    }
}
