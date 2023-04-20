package 迷宫;

/**
 * @author zhp
 * @date 2023-04-18 19:31
 * https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/
 */
public class 矩阵中的最长递增路径_lc_329 {
    /**
     * 记忆化深搜
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        dp = new int[m][n];
        int res = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                res = Math.max(res,dfs(i,j,matrix));
            }
        }
        return res;
    }
    int m,n;
    int dirs[][] = {{1,0},{-1,0},{0,1},{0,-1}};
    int dp[][];
    int dfs(int x,int y,int [][]matrix){
        if(dp[x][y]!=0) return dp[x][y];
        int res = 1;
        for(int dir[]:dirs ){
            int nx = x+dir[0];
            int ny = y+dir[1];
            if(nx<0||ny<0||nx>=m||ny>=n||matrix[x][y]>=matrix[nx][ny]) continue;
            res = Math.max(dfs(nx,ny,matrix)+1,res);
        }
        return dp[x][y] = res;

    }
}
