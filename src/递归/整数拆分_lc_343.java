package 递归;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2022-10-14 11:42
 * https://leetcode.cn/problems/integer-break/submissions/
 */
public class 整数拆分_lc_343 {

    /**
     * 记搜
     * @param n
     * @return
     */
    public int integerBreak2(int n) {
        dp = new int[n+1];
        Arrays.fill(dp,-1);
        return dfs(n);
    }
    int dp[];
    public int dfs(int n){
        if(n==2) return 1;
        int ans = -1;
        if(dp[n]!=-1) return dp[n];
        for(int i=1;i<=n-2;i++){
            ans = Math.max(ans,Math.max(dfs(n-i)*i,i*(n-i)));
        }
        return dp[n] = ans;
    }

    /**dp
     * dp[i]表示整数i至少拆分为两个数值后的最大乘积
     *
     * 链接：https://leetcode.cn/problems/integer-break/solution/zheng-shu-chai-fen-by-leetcode-solution/
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        int dp[] = new int[n+1];
        for(int i=2;i<=n;i++){
            int max = 0;
            for(int j=1;j<i;j++){
                max = Math.max(max,Math.max((i-j)*j,j*dp[i-j]));
            }
            dp[i] = max;
        }
        return dp[n];
    }

    /**归纳证明
     * 推导出最优的拆分数值为3
     * https://leetcode.cn/problems/integer-break/solution/zheng-shu-chai-fen-by-leetcode-solution/
     *
     * 这里粘贴一下美版 StefanPochmann 老哥的 idea（一句话就直观理解数学方法）
     *
     * You're making it pretty complicated.
     *
     * If an optimal product contains a factor f >= 4,
     * then you can replace it with factors 2 and f-2 without losing optimality, as 2*(f-2) = 2f-4 >= f.
     * So you never need a factor greater than or equal to 4,
     * meaning you only need factors 1, 2 and 3
     * (and 1 is of course wasteful and you'd only use it for n=2 and n=3, where it's needed).
     *
     * For the rest I agree, 3*3 is simply better than 2*2*2, so you'd never use 2 more than twice.
     */
    public int integerBreak1(int n){
        if(n<=3) return n-1;
        int a =n/3;
        int b =n%3;
        if(b==0) return (int) Math.pow(3, a);
        if(b==1) return (int) Math.pow(3,a-1)*4;
        return (int) Math.pow(3,a)*2;
    }

}
