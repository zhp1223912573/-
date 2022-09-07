package 递归;

/**
 * @author zhp
 * @date 2022-09-07 14:18
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * 多了一个冷冻期的状态。
 * 该状态只存在与昨天刚刚卖出了股票的情况。
 * 所以我们只需要列举出四种状态即可。
 * 《状态定义》
 * 0、持有股票状态
 *  不持有股票
 *      1.之前就不持有过，延续先前状态
 *      2.今天刚好卖出股票（明天就应该为冷冻期）
 * 3.冷冻期（昨天刚好卖出股票）
 *
 * 《状态推导》
 * 0：之前就持有股票则dp[i][0]=dp[i-1][0]
 *      或者，昨天是冷冻期，今天刚好买入股票dp[i][0] = dp[i-1][3]-prices[i]
 *      亦或者，昨天不是冷冻期，今天刚好买入股票dp[i][0] = dp[i-1][1]-prices[i]
 *      取三者最大值
 * 1：前一天就不持有股票dp[i][1] = dp[i-1][1]
 *    前一天是冷冻期dp[i][1] = dp[i-1][2]
 * 2: 昨天是买入股票状态dp[i][2] = dp[i-1][0]+prices[i]
 * 3.昨天卖出股票dp[i][3] = dp[i-1][2]
 *
 */
public class 最佳买卖股票时机含冷冻期_lc_309 {
    public int maxProfit(int[] prices) {
        if(prices==null||prices.length<=1) return 0;
        int len = prices.length;
        int dp[][] = new int[len][4];

        dp[0][0] = -prices[0];

        for(int i=1;i<len;i++){
            dp[i][0] = Math.max(dp[i-1][0],Math.max(dp[i-1][3]-prices[i],dp[i-1][1]-prices[i]));
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][2]);
            dp[i][2] =  dp[i-1][0]+prices[i];
            dp[i][3] = dp[i-1][2];
        }
        return Math.max(dp[len-1][3],Math.max(dp[len-1][2],dp[len-1][1]));
    }
}
