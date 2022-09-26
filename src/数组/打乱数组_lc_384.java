package 数组;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zhp
 * @date 2022-07-14 19:28
 * https://leetcode.cn/problems/shuffle-an-array/
 */
public class 打乱数组_lc_384 {
    /**
     *
     * 暴力法
     * 直接保存原始数组和洗牌后的数组。
     * 利用随机函数生成
     */
    class Solution {
        int nums[];
        int origin[];

        public Solution(int[] nums) {
            this.nums = nums;
            this.origin = new int[nums.length];
            System.arraycopy(nums,0,origin,0,origin.length);
        }

        public int[] reset() {
            System.arraycopy(origin,0,nums,0,origin.length);
            return nums;
        }

        public int[] shuffle() {
            int shuffled [] = new int[origin.length];
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; ++i) {
                list.add(nums[i]);
            }
            Random random = new Random();
            for (int i = 0; i < nums.length; ++i) {
                int j = random.nextInt(list.size());
                shuffled[i] = list.remove(j);
            }
            System.arraycopy(shuffled, 0, nums, 0, nums.length);
            return nums;

        }
    }


}