package 二分;

/**
 * @author zhp
 * @date 2022-07-20 19:58
 * https://leetcode.cn/problems/find-peak-element/
 */
public class 寻找峰值_lc_162 {
    /**
     * 数值非有序数值，但是为了实现O(logn),我们仍然使用二分法.
     * 当 num[i-1]<num[i]>num[i+1],则i位置就是我们要找的数组中的峰值点
     * 当 num[i-1]<num[i]<num[i+1],则i位置的右侧必然存在我们需要的峰值点，向右侧收缩
     * 当 num[i-1]>num[i]>num[i+1],则i位置的左侧必然存在我们需要的峰值点，向左侧收缩
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int m = nums.length;
        int left = 0;
        int right = m-1;
        while(left<right){
            int mid = (right-left)/2+left;
            if(nums[mid]<nums[mid+1]){
                 left = mid+1;
            }else{
                right = mid;
            }
        }
        return left;
    }





}
