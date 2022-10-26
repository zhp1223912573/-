package 每日任务;

/**
 * @author zhp
 * @date 2022-10-26 7:20
 * https://leetcode.cn/problems/super-pow/
 */
public class 超级次方_lc_357 {
    /**
     * 快速幂+数学定理
     * (a*b)%c = ((a%c)*(b%c))%c
     */
    static final int MOD = 1337;

    public int superPow(int a, int[] b) {
        int ans = 1;
        for (int i = b.length - 1; i >= 0; --i) {
            ans = (int) ((long) ans * pow(a, b[i]) % MOD);
            a = pow(a, 10);
        }
        return ans;
    }

    public int pow(int x, int n) {
        int res = 1;
        while (n != 0) {
            if (n % 2 != 0) {
                res = (int) ((long) res * x % MOD);
            }
            x = (int) ((long) x * x % MOD);
            n /= 2;
        }
        return res;
    }
}
