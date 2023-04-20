package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhp
 * @date 2023-03-29 16:53
 * https://leetcode.cn/problems/combination-sum-iii/submissions/
 */
public class 组合总和III_216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(1,k,n,0,new ArrayList(),ans);
        return ans;
    }

    public void dfs(int index,int k ,int n,int sum,List<Integer> list, List<List<Integer>> ans){
        if(list.size()==k){
            if(sum==n) ans.add(new ArrayList(list));
            return;
        }

        for(int i=index;i<=9;i++){
            if(sum+i>n) return;
            list.add(i);
            dfs(i+1,k,n,sum+i,list,ans);
            list.remove(list.size()-1);
        }
    }
}
