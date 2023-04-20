package 递归;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2023-03-31 16:08
 * https://leetcode.cn/problems/minimum-path-sum/submissions/
 */
public class 最小路径和_lc_64 {
    /**
     * 记搜
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        dp = new int[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(dp[i],-1);
        }
        return dfs(0,0,grid,m,n);
    }

    int dp[][];
    public int dfs(int x,int y,int [][]grid,int m,int n){
        if(x==m-1&&y==n-1) return grid[x][y];
        if(x==m||y==n) return 10000000;
        if(dp[x][y]!=-1) return dp[x][y];
        return dp[x][y] = Math.min(dfs(x+1,y,grid,m,n),dfs(x,y+1,grid,m,n))+grid[x][y];
    }

    /**
     * dp
     * @param m
     * @param n
     * @param grid
     * @return
     */
    public int process(int m,int n,int grid[][]){
        int dp[][] = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i=0;i<n;i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }

        for(int i=0;i<m;i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}
