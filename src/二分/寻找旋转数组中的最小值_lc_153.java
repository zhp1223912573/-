package 二分;

/**
 * @author zhp
 * @date 2022-07-16 22:41
 * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/submissions/
 */
public class 寻找旋转数组中的最小值_lc_153 {
    /**
     * 题目要求时间复杂度为O(logn),并且该数组为一个有序数组。
     * 显然而然要联系到二分查找，根据数组有序的特征，
     * 可以发现，若果二分查找的mid位置数值大于end位值，那么mid-end的数值一定是升序的，
     * 证明最小值只可能存在与start-mid中
     */
    public int findMin(int[] nums) {
        if(nums==null || nums.length==0){
            return -1;
        }

        int start = 0;
        int end = nums.length-1;

        while(start<end){
            int mid = (end-start)/2 +start;
            if(nums[mid]>nums[end]){
                start = mid+1;
            }else{
                end = mid;
            }
        }
        return nums[end];
    }
}
