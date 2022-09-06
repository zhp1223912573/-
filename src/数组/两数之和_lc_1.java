package 数组;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhp
 * @date 2022-07-13 23:51
 * https://leetcode.cn/problems/two-sum/solution/liang-shu-zhi-he-by-leetcode-solution/
 */
public class 两数之和_lc_1 {
    /**
     * 思路总结
     * 1.暴力 时间：O(n^2),空间O(1)
     * 2.hash 时间：O(n),空间O(n)
     */

    public static int[] twoSum(int[] nums, int target) {
        //数值值-下表的map
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

}
