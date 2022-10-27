package 每日任务;

/**
 * @author zhp
 * @date 2022-10-27 2:37
 * https://leetcode.cn/problems/stone-game/
 */
public class 石子游戏_lc_877 {
    /**
     * 区间dp问题
     * 定义 f[l][r] 为考虑区间 [l,r](l起始为1)，在双方都做最好选择的情况下，先手与后手的最大得分差值为多少。
     */
    public boolean stoneGame(int[] piles) {
        int n =piles.length;
        int f[][] = new int[n+2][n+2];
        for(int len=2;len<=n;len++){
            for(int l=1;l+len-1<=n;l++){
                int r = l+len-1;
                //只能选最左或者最右的石子堆
                int a = piles[l-1] - f[l+1][r];//只选最左
                int b = piles[r-1] - f[l][r-1];//只选最右
                f[l][r] = Math.max(a,b);
            }
        }
        return f[1][n]>0;
    }

    /**
     * 博弈论分析
     * https://mp.weixin.qq.com/s?__biz=MzU4NDE3MTEyMA==&mid=2247489400&idx=1&sn=0b629d3669329a6bf4f6ec71c2571ce7&chksm=fd9cbc67caeb357132fe0a1ca6240e2183748d94039100f539193d3eeb1dc223e0ddd4aa9584&token=2094656911&lang=zh_CN#rd
     *
     */
    public boolean stoneGame1(int[] piles) {
        return true;
    }
}
