package 递归.背包问题;

/**
 * @author zhp
 * @date 2022-07-09 16:17
 * 现有n1+n2种面值的硬币，其中前n1种为普通币，可以取任意枚，后n2种为纪念币，
 * 每种最多只能取一枚，每种硬币有一个面值，问能用多少种方法拼出m的面值
 */
public class _n1与n2货币 {
    /**
     * 经典的背包问题，只不过这题是01背包和完全背包的集合。
     * 要构成m的面值，我们可以如下进行：
     * 构成0元需要的普通币方案+构成m-0元时的纪念币方案
     * 构成1元需要的普通币方案+构成m-1元时的纪念币方案
     * ...........................................
     * 构成i元需要的普通币方案+构成m-i元时的纪念币方案
     *
     * 所以将整个问题分解成一个01背包和完全背包。
     */
    public static int moneyWays(int[] arbitrary, int[] onlyone, int money) {
        if (money < 0) {
            return 0;
        }
        if ((arbitrary == null || arbitrary.length == 0)
                && (onlyone == null || onlyone.length == 0)) {
            return money == 0 ? 1 : 0;
        }
        int[][] dparb = getDpArb(arbitrary, money);
        int[][] dpone = getDpOne(onlyone, money);
        if (dparb == null) {
            return dpone[dpone.length - 1][money];
        }
        if (dpone == null) {
            return dparb[dparb.length - 1][money];
        }
        int res = 0;
        for (int i = 0; i <= money; i++) {
            res += dparb[dparb.length - 1][i]
                    * dpone[dpone.length - 1][money - i];
        }
        return res;
    }

    public static int[][] getDpArb(int[] arr, int money) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] dp = new int[arr.length][money + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; arr[0] * j <= money; j++) {
            dp[0][arr[0] * j] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= money; j++) {

                dp[i][j] = dp[i - 1][j];
                //这里进行了斜率优化，画表观察可得。
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp;
    }

    /**
     * 标准的01背包模板
     * @param arr
     * @param money
     * @return
     */
    public static int[][] getDpOne(int[] arr, int money) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] dp = new int[arr.length][money + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        if (arr[0] <= money) {
            dp[0][arr[0]] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= money; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i - 1][j - arr[i]] : 0;
            }
        }
        return dp;
    }
}
