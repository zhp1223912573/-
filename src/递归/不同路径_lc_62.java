package 递归;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2023-03-31 16:25
 * https://leetcode.cn/problems/unique-paths/submissions/
 */
public class 不同路径_lc_62 {
    /**
     * 记搜
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        dp = new int[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(dp[i],-1);
        }
        return dfs(0,0,m,n);
    }
    int dp[][];
    public int dfs(int x,int y,int m,int n){
        if(x==m-1&&y==n-1) return 1;
        if(x==m||y==n) return 0;
        if(dp[x][y]!=-1 ) return dp[x][y];
        return dp[x][y] = dfs(x+1,y,m,n)+dfs(x,y+1,m,n);
    }

    /**
     * dp
     */

}
