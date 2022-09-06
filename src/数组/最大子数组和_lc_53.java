package 数组;

/**
 * @author zhp
 * @date 2022-07-14 16:38
 * https://leetcode.cn/problems/maximum-subarray/
 */
public class 最大子数组和_lc_53 {
    /**
     * 贪心：O(n)
     * 记录子数组和，一旦子数组和小于0那么这一部分的子数组无论如何都不可能为与最长子数组内，
     * 直接舍弃，我们直接将子数组和清零。
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if(nums==null){
            return -1;
        }

        int cur = 0;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            cur+=nums[i];
            max = Math.max(max,cur);
            if(cur<0){
                cur=0;
            }
        }
        return max;
    }

    /**
     * 分治法
     * 官方题解：
     * https://leetcode.cn/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
     */
}
