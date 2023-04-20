package 迷宫;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhp
 * @date 2023-04-18 19:40
 * https://leetcode.cn/problems/pacific-atlantic-water-flow/
 */
public class 太平洋大西洋水流问题_lc_417 {

    /**
     * 如果尝试对每一个位置都继续判断是否能到达大西洋与太平洋，需要o（n^2）
     * 转换思路，尝试从边界进行处理，如果当前位置与边界直接或者间接连接，
     * 那么将该位置置为1（太平洋连接），如果与大西洋连接同时与太平洋连接，那么设置为2
     *  @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;
        n = heights[0].length;
        stats = new int[m][n];

        //从上边沿和左边沿开始深搜
        for(int i=0;i<n;i++) dfs(0,i,1,heights);
        for(int i=0;i<m;i++) dfs(i,0,1,heights);

        //从右边沿和下边沿开始深搜
        for(int i=0;i<m;i++) dfs(i,n-1,2,heights);
        for(int i=0;i<n;i++) dfs(m-1,i,2,heights);

        return res;
    }
    int m ,n;
    int stats[][];
    int dirs[][] = {{1,0},{-1,0},{0,1},{0,-1}};

    List<List<Integer>> res = new ArrayList<>();

    /**
     *
     * @param x
     * @param y
     * @param flag 1为与太平洋连接，2为与大西洋连接
     */
    private void dfs(int x, int y, int flag,int height[][]) {
        //当前位置已经与预期相连
        if(flag==stats[x][y]) return;
        //当前位置已经与太平相连，当前又要与大西洋相连，说明符合条件，加入答案
        if(flag==2&&stats[x][y]==1) res.add(Arrays.asList(x,y));

        stats[x][y] = flag;
        for(int dir[] :dirs){
            int nx = dir[0]+x;
            int ny = dir[1]+y;
            if(nx<0||ny<0||nx>=m||ny>=n||height[x][y]>height[nx][ny]) continue;;
            dfs(nx,ny,flag,height);
        }
    }


}
