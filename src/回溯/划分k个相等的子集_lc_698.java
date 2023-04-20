package 回溯;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhp
 * @date 2023-03-29 21:10
 * https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/
 */
public class 划分k个相等的子集_lc_698 {
    int ave ;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int num : nums) sum+=num;
        if(sum%k!=0) return false;
        ave = sum/k;
        //优化
        //对数组排序，并且从数组末尾进行读取数值操作，这样保证每个子集都以较大的数值开始，避免其他状态的重复计算
        Arrays.sort(nums);
        return  dfs(nums,k,new int[k],nums.length-1);
    }

    boolean dfs(int nums[],int k,int sum[],int index){
        //到达数组末尾
        if(index==-1){
            //需要判断每个子集是否都符合条件
            for(int num:sum) if(num!=ave) return false;
            return true;
        }

        //set进行同层去重，避免重复计算
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<sum.length;i++){
            //当前子集加上最新值大于平均值，则重新计算 或者当前最新值已经在之前出现过了，重新计算
            if(sum[i]+nums[index]>ave || set.contains(sum[i])) continue;
            set.add(sum[i]);
            sum[i]+=nums[index];
            if(dfs(nums,k,sum,index-1)) return true;
            sum[i]-=nums[index];
        }

        return false;
    }

}
