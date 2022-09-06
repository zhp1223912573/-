package 回溯;

/**
 * @author zhp
 * @date 2022-07-26 20:42
 * https://leetcode.cn/problems/coin-change/
 */
public class 零钱兑换_lc_518 {
        public int change(int amount, int[] coins) {
            int[] dp = new int[amount + 1];
            dp[0] = 1;
            for (int coin : coins) {
                for (int i = coin; i <= amount; i++) {
                    dp[i] += dp[i - coin];
                }
            }
            return dp[amount];
        }




}
