package 数组;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2022-07-15 20:04
 * https://leetcode.cn/problems/minimum-size-subarray-sum/solution/
 */
public class 长度最小的子数组_lc_207 {
    /**
     * 暴力法
     * O（n^2)
     */

    /**前缀和+二分查找
     * 额外数组记录前缀和，二分查找，每个数结尾时，前缀和符合要求的最小数组长度
     *
     */
    public static int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;

    }

    /**
     * 滑动窗口
     * 前后指针指向的内容区间为一个动态的滑动的窗口
     * 若当前窗口内值大于等target，则记录最短长度，之后右移左值指针，缩小窗口。
     * 若当前窗口内值小于target，则右移右指针，扩大窗口。
     */
    public static int minSubArrayLen1(int target, int[] nums) {
        int left = 0;
        int right = 0;

        int sum = 0;
        int ans = Integer.MAX_VALUE;
        int n = nums.length;

        while(right<n){
            sum += nums[right];
            while(sum>=target){
                ans = Math.min(ans,right-left+1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return ans == Integer.MAX_VALUE? 0 :ans;
    }


    public static void main(String[] args) {
        int nums [] = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(7, nums));
    }
}
