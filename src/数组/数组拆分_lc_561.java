package 数组;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2022-07-16 20:40
 * https://leetcode.cn/problems/array-partition/
 */
public class 数组拆分_lc_561 {
    /**
     * 排序，取奇数位置求和
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for(int i=0;i<nums.length;i+=2){
            ans += nums[i];
        }
        return ans;
    }

}
