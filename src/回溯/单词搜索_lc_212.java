package 回溯;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhp
 * @date 2022-07-25 12:12
 * https://leetcode.cn/problems/word-search-ii/
 */
public class 单词搜索_lc_212 {

    class Trie{
        Trie[] children;
        boolean isEnd;
        String word;//加入该变量便于查询
        public Trie() {
            children = new Trie[26];
            word = new String();
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for(int i=0;i<word.length();i++){
                char ch = word.charAt(i);
                int index = ch-'a';
                if(node.children[index]==null){
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.word = word;
            node.isEnd = true;

        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
        boolean visited[][] = new boolean[m][n];
        Trie trie = new Trie();
        for(String string : words){
            trie.insert(string);
        }

        Set<String> set = new HashSet<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                   backtract(trie,board,visited,i,j,set);
            }
        }
        return new ArrayList<>(set);
    }

    private void backtract(Trie trie,char[][] board, boolean[][] visited,int i,int j,Set<String> set) {
        //如果字典树里压根就不存在该字符，那么就说明当前word不可能存在于该字符
            if(trie.children[board[i][j]-'a']==null){
                return ;
            }
        trie = trie.children[board[i][j]-'a'];
            if(trie.isEnd){
                    set.add(trie.word);
            }


        //设置当前位置字符以及访问，避免重复访问
        visited[i][j] = true;
        //方向数组
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        //从当前位置向上下左右四个位置进行查询，看看是否出现符合条件的相邻字符
        for(int [] dir:directions){
            int nexti = i+dir[0];
            int nextj = j+dir[1];
            if(nexti>=0&&nexti<board.length&&nextj>=0&&nextj<board[0].length){
                //保证没有被访问
                if(!visited[nexti][nextj]){
                    backtract(trie,board,visited,nexti,nextj,set);
                }
            }
        }
        //回溯，此步必不可少，必须要设置为未访问，这样后续的不同位置的字符才可能尝试当前位置的字符
        visited[i][j] = false;
    }

    public static void main(String[] args) {
       char [][]board ={{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words ={"oath","pea","eat","rain"};
        单词搜索_lc_212 t = new 单词搜索_lc_212();
        List<String> words1 = t.findWords(board, words);
        System.out.println(words1);

    }
}
