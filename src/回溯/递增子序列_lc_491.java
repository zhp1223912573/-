package 回溯;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhp
 * @date 2023-03-29 20:20
 * https://leetcode.cn/problems/non-decreasing-subsequences/
 */
public class 递增子序列_lc_491 {

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(0,ans,new ArrayList<Integer>(),nums);
        return ans;
    }

    /**
     * 大于2的集合则存入答案，通过set实现同层去重
     * @param index
     * @param ans
     * @param path
     * @param nums
     */
    void dfs(int index, List<List<Integer>> ans, List<Integer> path, int nums[]){
        if(path.size()>=2){
            ans.add(new ArrayList(path));
        }

        Set<Integer> set = new HashSet<>();
        for(int i=index;i<nums.length;i++){
            if(set.contains(nums[i]) || path.size()>0&&nums[i]<path.get(path.size()-1)) continue;
            set.add(nums[i]);
            path.add(nums[i]);
            dfs(i+1,ans,path,nums);
            path.remove(path.size()-1);
        }
    }
}
