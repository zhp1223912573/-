package 数组;

/**
 * @author zhp
 * @date 2022-07-27 22:53
 * https://leetcode.cn/problems/first-missing-positive/solution/
 */
public class 缺失的第一个正数_lc_41 {
    /**
     * 看解答把，无话可说。
     * @param nums
     * @return
     *
     * 将在【1，n】范围内的数放在下标值与该数值num【i】一致的位置上，
     * 最后检查哪些位置上的数字不对，就是我们要返回的最小正整数
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
