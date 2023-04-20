package 回溯;

import shiyan.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-25 15:47
 * https://leetcode.cn/problems/combination-sum/
 */
public class 组合总和_lc_39 {
    /**回溯+剪枝
     *
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        backtract(ans,path,target,candidates,0);
        return ans;
    }

    private void backtract(List<List<Integer>> ans, List<Integer> path, int target,int []candidates,int start) {

        if(target==0){
            ans.add(new ArrayList<>(path));
            return ;
        }

        for(int i = start; i<candidates.length;i++){
            if(candidates[i]>target){
                return ;
            }
            path.add(candidates[i]);
            backtract(ans,path,target-candidates[i],candidates,i);
            path.remove(path.size()-1);

        }
    }


}
