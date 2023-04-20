package 迷宫;

/**
 * @author zhp
 * @date 2023-04-18 19:55
 * https://leetcode.cn/problems/island-perimeter/
 */
public class 岛屿的周长_lc_463 {
    public int islandPerimeter(int[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        int dirs[][] = {{1,0},{-1,0},{0,1},{0,-1}};

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    for(int dir[]:dirs ){
                        int nx = i+dir[0];
                        int ny = j+dir[1];
                        if(nx<0||ny<0||nx>=m||ny>=n||grid[nx][ny]==0) res++;
                    }
                }
            }
        }
        return res;
    }
}
