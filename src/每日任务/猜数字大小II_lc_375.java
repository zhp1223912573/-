package 每日任务;

/**
 * @author zhp
 * @date 2022-10-27 1:32
 * https://leetcode.cn/problems/guess-number-higher-or-lower-ii/submissions/
 */
public class 猜数字大小II_lc_375 {
    /**
     * dfs（l,r)表示区间l-r内的猜数字最小开销
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        int table[][] = new int[n+1][n+1];

        return dfs(table,1,n);

    }
    //使用table充当缓存
    int dfs(int[][] table,int l,int r){
        if(l>=r) return 0 ;
        if(table[l][r]!=0) return table[l][r];
        int cur = Integer.MAX_VALUE;
        //在l-r返回内尝试各个数
        for(int x=l;x<=r;x++){
            //选择当当前数，我们无法根据反馈决定选择那一步最近下目标数的范围.
            //所以只能选择最坏情况
            int max = Math.max(dfs(table,l,x-1),dfs(table,x+1,r))+x;
            //得到所有最坏情况下的最小值
            cur = Math.min(cur,max);
        }
        table[l][r] = cur;
        return cur;
    }

    /**
     * 区间dp
     * 从上述递归可以看出，该问题是很明显的大区间依赖小区间的dp问题。
     * 我们直接设定区间范围，在设定左右边界，从小区间到大区间进行推导即可。
     */
    public int getMoneyAmount1(int n){
        int dp[][] = new int[n+10][n+10];
        for(int len=2;len<=n;len++){//设定区间
            //设置左边界
            for(int l=1;l+len-1<=n;l++){
                //设置右边界
                int r = l+len-1;
                dp[l][r] = Integer.MAX_VALUE;
                for(int x=l;x<=r;x++){
                    int cur = Math.max(dp[l][x-1],dp[x+1][r])+x;
                    dp[l][r] = Math.min(dp[l][r],cur);
                }
            }
        }
        return dp[1][n];
    }

}
