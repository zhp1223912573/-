package 数组;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2022-07-27 13:15
 * https://leetcode.cn/problems/majority-element/
 */
public class 多数元素_lc_169 {

    /**一个数出现次数超过一半，那么排序后的中位数一定就是该数。
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**投票法
     * 顺序遍历数组，使用candidate记录出现的数，count记录candidate的出现次数，
     * 如果count为0，则修改candidate为出现的数，
     * 如果candidate与出现的数不一致，则count--；
     * 如果candidate与出现的数一致， 则count++;
     * 到最后candidate一定就是我们要找的那个数
     */
    public int majorityElement1(int[] nums){
            int candidate = -1;
            int count = 0;
            for(int num : nums){
                if(count==0){
                    candidate = num;
                    count = 1;
                }else if(candidate!=num){
                    count--;
                }else if(candidate==num){
                    count++;
                }
            }
            return candidate;
    }

    /**分治
     * 要找的数出现次数超过数组长度一半，则该数在划分的子数组中出现的次数也一定最多
     * 则我们将整个数组递归划分为小数组，找到每个子树组的众数，再比较判断。
     */
    public int majorityElement2(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }
    private int majorityElementRec(int[] nums, int low, int high) {
        //只剩下一个元素，直接返回
        if(low==high){
            return nums[low];
        }

        //分裂数组，得到出现次数最多的数值
        int mid = (high-low)/2+low;
        int left = majorityElementRec(nums,low,mid);
        int right = majorityElementRec(nums,mid+1,high);

        //相等直接返回
        if(left==right) return left;

        //统计数值出现的次数
        int leftCount = countInRange(nums,left,low,high);
        int rightCount = countInRange(nums,right,low,high);

        return leftCount>rightCount?left:right;
    }
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }


}