package 递归;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author zhp
 * @date 2022-09-06 22:15
 * 股票系列第一题
 *https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 */
public class 卖股票的最佳时期I_lc_121 {
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
        if(index==prices.length||status==2) return 0;
        if(dp[index][status]!=-1) return dp[index][status];
        if(status==0){
            return dp[index][status] = Math.max(dfs(index+1,prices,0),dfs(index+1,prices,1)-prices[index]);
        }
        return dp[index][status] =  Math.max(dfs(index+1,prices,1),dfs(index+1,prices,2)+prices[index]);

    }

    /**贪心
     * 顺序变量，找到最小值和每个数值的差，从而得到最大收益
     *
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices==null||prices.length<=1) return 0;
        int min = prices[0];
        int max = Integer.MIN_VALUE;
        for(int i=1;i<prices.length;i++){
                max = Math.max(max,prices[i]-min);
                min = Math.min(min,prices[i]);
        }
        return max;
    }

    /**dp
     * 设置二维dp数组，dp[i][0]表示持有股票时的最大收益，dp[i][1]表示不持有股票时的最大收益，
     * 因为最多只能购入股票一次，所以持有股票时收益为负数，不持有股票时收益最大，
     * 所以应该反回dp[len][1]
     *
     * 状态推导：
     * dp[i][0]持有股票，可能昨天i-1时就持有股票，可能i-1时不持有股票，今天才买入
     * dp[i][1]不持有股票，可能昨天i-1时就不持有股票，可能i-1时持有股票，今天抛出。
     * 综上可得状态推导方程。
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int dp[][] = new int[prices.length][2];
        //第一天就持有股票，那么一定是购入第一天的股票
        dp[0][0] = -prices[0];

        for(int i=1;i<prices.length;i++){
            dp[i][0] = Math.max(dp[i-1][0],-prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],prices[i]+dp[i-1][0] );
        }

        return dp[prices.length-1][1];

    }

    /**单调栈
     * 维护以一个单调递增的栈，每个元素入栈前和栈顶元素比较大小，如果大于栈顶则更新最大值，
     * 反之入栈。
     */
    public int maxProfit2(int[] prices) {
        if(prices==null||prices.length<=1) return 0;
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(prices[0]);
        for(int i=1;i<prices.length;i++){
            if(stack.peek()>prices[i]){
                stack.push(prices[i]);
            }else{
                max = Math.max(max,prices[i]-stack.peek());
            }
        }
        return max;


    }

}
