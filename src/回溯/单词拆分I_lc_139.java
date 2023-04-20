package 回溯;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-30 22:26
 * https://leetcode.cn/problems/word-break/
 */
public class 单词拆分I_lc_139 {
    /**
     *
     * @param s
     * @param wordDict
     * @return
     */

    /**
     * dfs+记忆化搜索
     * 深搜尝试字符是否可以由字典中的单词匹配，起初不使用记忆化搜索会有下述案例超时：
     *      通过23/36个用例，遇到下面这个用例超时：
     *      "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
     *      aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
     *      ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
     * 因为尾端的b不存在于wordDict中的任一单词内，但是前面一长串的a又与字典内单词匹配，
     * 所以，不加记忆搜索回导致频繁对同一结果重复计算，起始在第一轮搜索过程中，仅仅有一个单词"a",
     * 就可以匹配原始字符s到倒数第2个字符，但是因为最后一个匹配失败，又要从头开始，并从第二个单词"aa"进行匹配。
     * 其实在第一轮搜索中，根据'a'的匹配，我们可以使用数组visited记录下标为i的
     * 字符串已经可以有单词表中单词匹配，避免重复计算。
     * 所以这里使用一个整型visited数组，默认情况为0，表示当前位置没有被匹配过，而1表示成功匹配，
     * 2表示无法被匹配，此时应该回退到上一步，尝试别的单词。
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak1(String s, List<String> wordDict) {
        int visited[] = new int[s.length()];
        return splitWord(s,0,wordDict,visited);
    }

    /**
     *
     * @param s
     * @param start
     * @param wordDict
     * @param visited
     * @return
     */
    private boolean splitWord(String s,int start, List<String> wordDict,int[] visited) {


        if(start==s.length()){
            return true;
        }

        if(visited[start]!=0){
            return visited[start]==1?true:false;
        }


        for(int i=start+1;i<=s.length();i++){
            if(wordDict.contains(s.substring(start,i))){
                if(splitWord(s,i,wordDict,visited)){
                    visited[start] = 1;
                    return true;
                }
            }
        }

        visited[start] = 2;
        return  false;
    }

    /**
     * 记搜
     * dp[i]=0未设置
     * dp[i]=1可以生成
     * dp[i]=-1不能生成
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak3(String s, List<String> wordDict) {
        dp = new int[s.length()];
        return dfs(0,s,wordDict)==1;
    }
    int dp[] ;
    public int dfs(int index,String s,List<String> wordDict){
        if(index>=s.length()){
            return 1;
        }
        if(dp[index]!=0) return dp[index];
        for(int i=index+1;i<=s.length();i++){
            if(wordDict.contains(s.substring(index,i))&&dfs(i,s,wordDict)==1){
                return dp[index] = 1;
            }
        }
        return dp[index]=-1;
    }
    /**
     * 动规
     * 将字符串s分为0-j，j-i，i-length的三部分理解，
     * 要使字符串0-i的字符串可以构成，那么0-j-1的字符串必须可以有字典里的单词组成，
     * 而j-i的单词也存在与字典里，那么我们说0-i的字符串可由字典里的单词组成
     * 所有设置dp数组，dp[i]表示0-i-1的字符串可由字典里的单词以某种组合构成，
     * 所以dp[0]表示没有字符串，默认为true，则dp[length]就是0-length-1的字符串（也就是整个s是否可以由字典构成）
     */
    public boolean wordBreak(String s, List<String> wordDict){
        HashSet<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean dp[] = new boolean[n+1];
        dp[0] = true;
        for(int i=1;i<=n;i++){
            for(int j=0;j<i;j++){
                if(dp[j]&&set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        单词拆分I_lc_139 t = new 单词拆分I_lc_139();
        List<String> list = new ArrayList<>();
        list.add("leet");
        list.add("code");
//        list.add("sand");
//        list.add("and");
//        list.add("cat");
//
//        System.out.println(t.wordBreak("leetcode", list));
    }
}
