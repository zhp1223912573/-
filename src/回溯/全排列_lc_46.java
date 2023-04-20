package 回溯;

import com.sun.org.apache.xpath.internal.operations.String;
import sun.text.resources.el.CollationData_el;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-25 0:07
 * https://leetcode.cn/problems/permutations/solution/
 */
public class 全排列_lc_46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        for(int num : nums){
            output.add(num);
        }
        if(nums==null&&nums.length ==0){
            return ans;
        }
        backtrack(nums.length,output,ans,0);
        return ans;
    }

    /**
     * 将output数组左右分离的数组，左边是已经组合好的，右边的是还未组合的，这样就省去了设置辅助数组表示
     * 数组元素以及被使用的冗余空间。
     * @param length
     * @param output
     * @param ans
     * @param first
     */
    private void backtrack(int length, List<Integer> output, List<List<Integer>> ans, int first) {
        if(length==first){
            //此处必须新建一个集合，避免对同一个集合的重复加入
            ans.add(new ArrayList<>(output));
            return ;
        }

        for(int i=first;i<length;i++){
            //交换对应元素
            Collections.swap(output,i,first);
            backtrack(length,output,ans,first+1);
            //交换后需要还原，才能保证同一位置的原本元素被交换
            Collections.swap(output,i,first);
        }
    }


    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums,new ArrayList<Integer>(),ans,new boolean[nums.length]);
        return ans;
    }

    void dfs(int []nums,List<Integer> path,List<List<Integer>> ans,boolean used[]){
        if(path.size()==nums.length){
            ans.add(new ArrayList(path));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(used[i]) continue;
            used[i] = true;
            path.add(nums[i]);
            dfs(nums,path,ans,used);
            used[i] = false;
            path.remove(path.size()-1);
        }
    }

}
