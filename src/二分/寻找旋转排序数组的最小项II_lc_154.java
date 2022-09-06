package 二分;

/**
 * @author zhp
 * @date 2022-07-21 13:30
 * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/
 */
public class 寻找旋转排序数组的最小项II_lc_154 {
    /**
     * 本题与同名题I基本类似，只是数组中出现了重复数值。
     * 结合官方题解的解析进行总结:
     * 使用二分法来缩小查找范围直到找到最小值。
     * 如果中间值mid小于范围右侧值right，说明最小值一定位于mid左侧，
     * 如果中间值mid大于范围右侧值right，说明最小值一定位于mid右侧，
     * 如果中间值mid等范围右侧值right，最小值既有可能出现在mid左侧也可能出现在右侧，
     *      但我们知道不论right数值是否为最小值，该right值一定存在多个，那么我们左移右指针，
     *      缩小范围，直到出现上述第一二种情况
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n-1;
        while(left<right){
            int mid = (right-left)/2+left;
            if(nums[mid]>nums[right]){
                left = mid + 1;
            }else if(nums[mid]<nums[right]){
                right = mid;
            }else{
                right--;
            }
        }
        return nums[left];
    }
}
