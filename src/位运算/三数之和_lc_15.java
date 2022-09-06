package 位运算;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-24 0:22
 * https://leetcode.cn/problems/3sum/solution/pai-xu-shu
 */
public class 三数之和_lc_15 {
    /**
     * 双指针+排序
     * 由于要求不能包含重复的三元组，所以排序之后需要在出现相同数字之后进行跳过，避免元组的重复加入
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        for(int i=0;i<nums.length;i++){

            if(nums[i]>0) return ans;//首指针指向的元素大于0，后续不可能出现和为0的情况
            if(i>0&&nums[i]==nums[i-1]) continue;

            int L  = i+1;
            int R  = nums.length-1;
            while(L<R) {
                int cur = nums[i] + nums[L] + nums[R];
                if (cur == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    ans.add(list);
                    while(L<R&&nums[L+1]==nums[L]) L++;
                    while(L<R&&nums[R]==nums[R-1]) R++;
                    L++;
                     R--;
                }else if(cur<0){
                    L++;
                }else{
                    R--;
                }
            }
        }
        return ans;
    }
}
