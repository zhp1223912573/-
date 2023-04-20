package 回溯;

/**
 * @author zhp
 * @date 2022-07-25 1:11
 *
 * https://leetcode.cn/problems/word-search/
 */
public class 单词搜索_lc_79 {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean visited[][] = new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                //从每个位置开始检测是否存在以当前位置字符开始的符合word的单词，如果存在返回true
                boolean check = backtract(board, word,visited,0,i,j);
                if(check) return true;
            }
        }
        return false;
    }

    private boolean backtract(char[][] board, String word, boolean[][] visited,int index,int i,int j) {
       //当前字符对不上，直接返回false
        if(board[i][j]!=word.charAt(index)){
            return false;

        }
        //到最后一个字符，说明前面的所有字符都与word一致，找到了同样的字符
        else if(index==word.length()-1){//这里要-1，应为index从0开始
            return true;
        }

        //设置当前位置字符以及访问，避免重复访问
        visited[i][j] = true;
        //方向数组
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        //从当前位置向上下左右四个位置进行查询，看看是否出现符合条件的相邻字符
        for(int [] dir:directions){
            int nexti = i+dir[0];
            int nextj = j+dir[1];
            if(nexti>=0&&nexti<board.length&&nextj>=0&&nextj<board[0].length){
                //保证没有被访问
                if(!visited[nexti][nextj]){
                    result = backtract(board,word,visited,index+1,nexti,nextj);
                    if(result){
                        //为真说明当前字符相邻位置存在符合条件的下一个字符
                        break;
                    }
                }

            }
        }
        //回溯，此步必不可少，必须要设置为未访问，这样后续的不同位置的字符才可能尝试当前位置的字符
        visited[i][j] = false;
        return result;
    }
}
