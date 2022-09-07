package 递归;

/**
 * @author zhp
 * @date 2022-09-07 13:54
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/
 * 与第三题类是，都是限定交易次数下求解股票交易的最大收益值，同样可以按照第三题的思路完成。
 *
 *  状态分析：
 *      0.不做操作
 *      1.第一次买入
 *      2.第一次卖出
 *      3.第二次买入
 *      4.第二次卖出
 *      。。。。
 *      2k-1.第k次买入
 *      2k.第k次卖出
 *      综上，按照第三题的步骤分析出每种情况的推导。
 *      总结规律，奇数次是买入，偶数次是卖出（除了第0次）
 */
public class 卖股票的最佳时期IV_lc_188 {
    public int maxProfit(int k, int[] prices) {
        if(prices==null||prices.length<=1) return 0;
        int len = prices.length;
        int dp[][] = new int[len][2*k+1];

        //第一天的奇数次买入都是-prices[0]
        for(int j=1;j<=2*k;j+=2){
            dp[0][j] = -prices[0];
        }

        for(int i=1;i<len;i++){
            for (int j = 0; j < 2 * k - 1; j += 2) {
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);
            }
        }
        return dp[len-1][2*k];
    }
}
