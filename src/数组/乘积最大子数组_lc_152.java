package 数组;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @author zhp
 * @date 2022-07-30 20:34
 * https://leetcode.cn/problems/maximum-product-subarray/
 */
public class 乘积最大子数组_lc_152 {
    /**
     * 动规
     * 本题和最大子数组和类型，都是记录局部最优解得到最优解。
     * 但是本题是求出最大的子数组乘积，并非和，数组元素可能为负数，并且只使用一个遍历记录局部最优无法推导到全局最优解。
     * 所以我们使用两个遍历来记录，
     * 一个是max，一个是min，max记录遍历到当前元素时最大的乘积，而min记录遍历当前元素时最小的乘积。
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int max = nums[0];
        int min = nums[0];
        int ans = nums[0];
        for(int i=1;i<n;i++){
            int mx = max ;
            int mn = min;
            max = Math.max(Math.max(nums[i],mn*nums[i]),nums[i]*mx);
            min = Math.min(Math.min(nums[i],mn*nums[i]),nums[i]*mx);
            ans = Math.max(max,ans);
        }
        return ans;

    }
}
