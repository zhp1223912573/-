package 回溯;

/**
 * @author zhp
 * @date 2022-07-29 16:36
 * https://leetcode.cn/problems/surrounded-regions/
 */
public class 被围绕的区域_lc_130 {
    /**
     * 任何边界上的'o'都不会变成'x'，而所有与'o'直接或间接相连的'o'也都不会变成'x'，
     * 所以，转化问题为从边界上的'o'出发，找到所有与该起点相连的'o'，他们全部都不设置为'x'（实现方法中设置为A),
     * 最后剩下的'o'就应该设置为'x'
     * @param board
     */
    public void solve(char[][] board) {
            int m = board.length;
            int n = board[0].length;

            //从边框上的字符进行深搜，找到与边界上的O相连的所有其他O，在dfs中将这些O设置为A
            for(int i=0;i<m;i++){
                dfs(board,i,0);
                dfs(board,i,n-1);
            }
            
            for(int i=0;i<n;i++){
                dfs(board,0,i);
                dfs(board,m-1,i);
            }

            //搜索完成，将A字符还原为O，而剩下的O是被X包围的，直接设置为X
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(board[i][j]=='A'){
                        board[i][j] = 'O';
                    }else if(board[i][j]=='O'){
                        board[i][j]='X';
                    }
                }
            }


    }

    private void dfs(char[][] board, int x, int y) {
        if(x<0||x>=board.length||y<0||y>=board[0].length||board[x][y]=='A'||board[x][y]=='X'){
            return ;
        }
        board[x][y] = 'A';

        dfs(board,x+1,y);
        dfs(board,x,y+1);
        dfs(board,x-1,y);
        dfs(board,x,y-1);

    }

}
