package 栈和队列;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhp
 * @date 2022-07-20 14:45
 *https://leetcode.cn/problems/01-matrix/submissions/
 */
public class 零一矩阵_lc_542 {

    /**
     * 基于广度优先
     *
     * 两种解题思路：
     * 1.以矩阵中的1为主体，广度优先搜索距离最近的0，这样的搜索会超时
     * 48 / 50 个通过测试用例
     * 状态：超出时间限制
     * 提交时间：几秒前
     * 最后执行的输入：
     * [[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[0,0,0]]
     *
     * 2.以矩阵中的0为主体，我们将所有0视为一个超级零，整体，将他们读入队列中，
     *   从这些0出发，将所有里他们最近的1进行标记，这里1离最近的0的距离就是最先走到当前1的上一个数到0的距离+1
     *
     * @param mat
     * @return
     */
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int ans[][] = new int[m][n];
        //四个运动方向

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(mat[i][j]==0){
                    continue;
                }
                ans[i][j] = bfs(mat,i,j);
            }
        }
        return ans;
    }
    public int bfs(int[][] mat,int i,int j){
        int dir[][]={{-1,0},{1,0},{0,-1},{0,1}};
        int m = mat.length;
        int n = mat[0].length;
        boolean visited[][] = new boolean[m][n];
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i,j});//将起始坐标放入
        visited[i][j]=true;
        int level = 0;//表示需要经过几层才能找到距离最近的0
        while(!queue.isEmpty()){
            int size = queue.size();
            level++;
            for(int x=0;x<size;x++){
                int top[] = queue.remove();
                for(int p=0;p<4;p++){
                    int nextrow = top[0] +dir[p][0];
                    int nextcol = top[1] +dir[p][1];
                    if(nextrow>=0&&nextrow<m&&nextcol>=0&&nextcol<n&&!visited[nextrow][nextcol]){
                        if(mat[nextrow][nextcol]==0){
                            return level;
                        }
                        queue.add(new int[]{nextrow,nextcol});
                    }
                }
            }
        }
        return -1;
    }

    public int[][]bfs(int[][] mat){
        int m = mat.length;
        int n = mat[0].length;
        int dir[][]={{-1,0},{1,0},{0,1},{0,-1}};
        int ans[][] = new int[m][n];
        //标记已经访问过的数
        boolean visited [][] = new boolean[m][n];
        //保存坐标的队列
        Queue<int[]> queue = new LinkedList<>();
        //开始收集0
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(mat[i][j]==0){
                    visited[i][j]=true;
                    queue.add(new int[]{i,j});
                }
            }
        }

        //收集完成，开始从所有的0出发，找到最近的数值
        while(!queue.isEmpty()){
            int poll[] = queue.poll();
            for(int i=0;i<4;i++){
                int nextrow = poll[0]+dir[i][0];
                int nextcol = poll[1]+dir[i][1];
                if(nextcol>=0&&nextcol<n&&nextrow>=0&&nextrow<m&&!visited[nextrow][nextcol]){
                    visited[nextrow][nextcol] = true;
                    ans[nextrow][nextcol] = ans[poll[0]][poll[1]]+1;
                    queue.add(new int[]{nextrow,nextcol});
                }

            }
        }
        return ans;
    }

    /**dp
     * 定义：
     *      dp[i][j]表示距离最近的0距离
     * 初始：
     *      当前mat[i][j]==0,dp[i][j]=0
     * 状态转移：
     *      距离mat[i][j]位置最近的0，取决于四种情况，以当前位置为圆心，画xy图，
     *      四个象限上的点都可能存在是dp[i][j]尽可能小的0
     *      取其中一个象限进行分析，
     *      dp[i][j] = min(dp[i-1][j],dp[i][j-1])表示水平向上移动，垂直向左移动的最小值
     */
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] updateMatrix1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // 初始化动态规划的数组，所有的距离值都设置为一个很大的数
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
        }
        // 如果 (i, j) 的元素为 0，那么距离为 0
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    dist[i][j] = 0;
                }
            }
        }
        // 只有 水平向左移动 和 竖直向上移动，注意动态规划的计算顺序
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i - 1 >= 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
                }
            }
        }
        // 只有 水平向左移动 和 竖直向下移动，注意动态规划的计算顺序
        for (int i = m - 1; i >= 0; --i) {
            for (int j = 0; j < n; ++j) {
                if (i + 1 < m) {
                    dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
                }
            }
        }
        // 只有 水平向右移动 和 竖直向上移动，注意动态规划的计算顺序
        for (int i = 0; i < m; ++i) {
            for (int j = n - 1; j >= 0; --j) {
                if (i - 1 >= 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                }
                if (j + 1 < n) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
                }
            }
        }
        // 只有 水平向右移动 和 竖直向下移动，注意动态规划的计算顺序
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (i + 1 < m) {
                    dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                }
                if (j + 1 < n) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
                }
            }
        }
        return dist;
    }
}
