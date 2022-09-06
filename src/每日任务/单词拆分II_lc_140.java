package 每日任务;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-30 23:52
 * https://leetcode.cn/problems/word-break-ii/
 */
public class 单词拆分II_lc_140 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ans = new ArrayList<>();
         splitWord(s,0,wordDict,ans,"");
         return ans;
    }

    /**
     *
     */
    private void splitWord(String s,int start, List<String> wordDict,List<String> ans,String path) {
        if(start==s.length()){
            ans.add(path);
            return ;
        }

        for(int i=start+1;i<=s.length();i++){
            if(wordDict.contains(s.substring(start,i))){
                splitWord(s,i,wordDict,ans,path+s.substring(start,i));
            }
        }
    }
}
