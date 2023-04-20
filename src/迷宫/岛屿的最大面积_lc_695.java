package 迷宫;

/**
 * @author zhp
 * @date 2023-04-18 19:59
 * https://leetcode.cn/problems/max-area-of-island/
 */
public class 岛屿的最大面积_lc_695 {
    /**
     * 经典深搜问题
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int res = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    res = Math.max(res,dfs(i,j,grid));
                }
            }
        }
        return res;
    }
    int m,n;
    int dirs[][] = {{1,0},{-1,0},{0,1},{0,-1}};
    private int dfs(int x, int y, int[][] grid) {
        grid[x][y] = 0;
        int res = 1;
        for(int dir[]:dirs ){
            int nx = x+dir[0];
            int ny = y+dir[1];
            if(nx<0||ny<0||nx>=m||ny>=n||grid[nx][ny]==0) continue;
            res += dfs(nx,ny,grid);
        }
        return res;
    }



}
