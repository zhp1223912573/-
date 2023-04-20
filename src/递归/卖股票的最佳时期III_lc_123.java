package 递归;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2022-09-07 13:29
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/
 * 股票问题第三题
 * 添加交易限定次数。此问题和该系列第4题一致，都是询问在限定次数k下的股票交易最大收益值。
 *  先从该问题出发解决（解决两次的情况），再发散到k次的问题。
 *  首先，如果只限定了两次交易次数的话，我们同样可以使用有限的状态数组记录两次交易的各种情况。
 *《状态定义》
 *      在所有的交易中可能出现的情况有以下5种，
 *      1、没进行过交易
 *      2、第一次持有股票
 *      3、第一次不持有股票
 *      4、第二次持有股票
 *      5、第二次不持有股票
 *      定义二维数组dp[i][num]表示上述5种状态中的每天的最大收益
 *      并且最大收益一定是两次交易，并且不持有股票时的状态，也就是dp[len-1][4]
 *《状态推导》
 * 在第i天
 *    1.没进行交易
 *         则dp[i][0] = dp[i-1][0]
 *    2.第一次买入
 *          可能是今天刚好买入股票，昨天持有股票状态减去今天的股票价钱：dp[i][1] = dp[i-1][0]-prices[i]
 *          也可能是前面买入了股票，今天维持前面的状态：dp[i][1] = dp[i-1][1]
 *          取两者最大收益
 *    3.第一次卖出
 *           可能是今天刚好卖出股票，昨天持有股票的状态+今天卖出股票的价钱： dp[i][2] = dp[i-1][1] + prices[i]
 *           可能是维持了前面的第一次卖出股票的状态，dp[i][2] = dp[i-1][2]
 *           取两者最大收益
 *     4.第二次买入
 *             dp[i][3] = dp[i-1][3]
 *             dp[i][3] = dp[i-1][2]-prices[i]
 *     5.第二次卖出
 *              dp[i][4] = dp[i-1][4]
 *              dp[i][4] = dp[i-1][3]+prices[i]
 *《初始状态》
 * 不操作的状态从始至终都为0
 * 第一次买入dp[0][1] = -prices[0]
 * 第一次卖出dp[0][2] = 0
 * 第二次买入dp[0][3] = -prices[0]
 * 第二次卖出dp[0][4] = 0
 */
public class 卖股票的最佳时期III_lc_123 {

    /**
     * 记搜
     */
    public int maxProfit2(int[] prices) {
        dp = new int[prices.length][5];
        for(int i=0;i<prices.length;i++){
            Arrays.fill(dp[i],-1);
        }
        return dfs(prices,0,0);
    }
    int dp[][];
    public int dfs(int[] prices,int index,int status){
        if(index==prices.length || status==5) return 0;
        if(dp[index][status]!=-1) return dp[index][status];
        if(status%2==0){
            return dp[index][status]=Math.max(dfs(prices,index+1,status),dfs(prices,index+1,status+1)-prices[index]);
        }else{
            return dp[index][status]=Math.max(dfs(prices,index+1,status),dfs(prices,index+1,status+1)+prices[index]);
        }
    }

    /**dp
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices==null||prices.length<=1) return 0;
        int len = prices.length;
        int dp[][] = new int[len][5];
        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0];
        for(int i=1;i<len;i++){
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
            dp[i][2] = Math.max(dp[i-1][2],dp[i-1][1]+prices[i]);
            dp[i][3] = Math.max(dp[i-1][3],dp[i-1][2]-prices[i]);
            dp[i][4] = Math.max(dp[i-1][4],dp[i-1][3]+prices[i]);
        }
        return dp[len-1][4];
    }

    /**dp空间优化
     * 观察上述方法，发现状态转换只与前一天有关，我们只需要使用一个一维数组就可以记录前一天的状态，
     * 同时获取今天的状态。
     */
    public int maxProfitBetter(int[] prices) {
        if(prices==null||prices.length<=1) return 0;
        int len = prices.length;
        int dp[] = new int[5];
        dp[1] = -prices[0];
        dp[3] = -prices[0];
        for(int i=1;i<len;i++){
            dp[1] = Math.max(dp[1],dp[0]-prices[i]);
            dp[2] = Math.max(dp[2],dp[1]+prices[i]);
            dp[3] = Math.max(dp[3],dp[2]-prices[i]);
            dp[4] = Math.max(dp[4],dp[3]+prices[i]);
        }
        return dp[4];
    }
}
