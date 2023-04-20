package 数组.双指针;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhp
 * @date 2022-07-03 12:13
 *
 * 给你一个整数数组 nums 和一个整数 k，请你在数组中找出 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
 *
 * k-diff 数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：
 *
 * 0 <= i, j < nums.length
 * i != j
 * nums[i] - nums[j] == k
 * 注意，|val| 表示 val 的绝对值。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/k-diff-pairs-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _不重复数字对_lc_532 {
    /**
     * hashset保存符合条件的数值，最后计算hashset内的数量即可
     * @param nums
     * @param k
     * @return
     */
    public int findPairsBySet(int[] nums, int k) {
        Set<Integer> visited = new HashSet<Integer>();
        Set<Integer> res = new HashSet<Integer>();
        for (int num : nums) {
            if (visited.contains(num - k)) {
                res.add(num - k);
            }
            if (visited.contains(num + k)) {
                res.add(num);
            }
            visited.add(num);
        }
        return res.size();
    }

    /**
     * 排序+数组.双指针
     * @param nums
     * @param k
     * @return
     */
    public int findPairs(int[] nums, int k) {
        int n = nums.length, res = 0;
        Arrays.sort(nums);
        for (int i = 0, j = 0; i < n - 1 && j < n; i++) {
            //重复的不计算，如果相同nums[i-1]已经计算过了
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //需要j > i，有可能去重时导致i >= j
            while (j <= i) {
                j++;
            }
            //找到target 和方法二一样，只找nums[i] + k
            while (j < n && (nums[j] < nums[i] + k)) {
                j++;
            }
            //找到目标值
            if (j < n && nums[j] == nums[i] + k) {
                res++;
            }
        }
        return res;
    }

    /**
     * 排序+二分
     * @param nums
     * @param k
     * @return
     */
    public int findPairs1(int[] nums, int k) {
        int n = nums.length, res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n - 1; i++) {
            //重复的不计算，如果相同nums[i-1]已经计算过了
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = nums[i] + k;
            //在[i+1,n-1]中找target
            int left = i + 1, right = n - 1;
            int ans = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] >= target) {
                    ans = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            //判断是否找到
            if (ans != -1 && nums[ans] == target) {
                res++;
            }
        }
        return res;
    }


}
