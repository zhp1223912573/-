package 迷宫;

/**
 * @author zhp
 * @date 2023-04-18 20:17
 * https://leetcode.cn/problems/number-of-enclaves/
 */
public class 飞地的数量_lc_1020 {
    public int numEnclaves(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        for(int i=0;i<m;i++) if(grid[i][0]==1) dfs(i,0,grid);
        for(int i=0;i<n;i++) if(grid[0][i]==1) dfs(0,i,grid);
        for(int i=0;i<m;i++) if(grid[i][n-1]==1) dfs(i,n-1,grid);
        for(int i=0;i<n;i++) if(grid[m-1][i]==1) dfs(m-1,i,grid);

        int res = 0;
        for(int i = 0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1) {
                    res++;
                }
            }
        }
        return res;
    }

    int m ,n;
    int dirs[][] = {{1,0},{-1,0},{0,1},{0,-1}};

    private void dfs(int x, int y, int[][] grid) {
        grid[x][y] = 0;
        for(int dir[]:dirs ){
            int nx = x+dir[0];
            int ny = y+dir[1];
            if(nx<0||ny<0||nx>=m||ny>=n||grid[nx][ny]==0) continue;
            dfs(nx,ny,grid);
        }
    }


}
