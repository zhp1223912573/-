package 二分;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2022-07-21 15:23
 * https://leetcode.cn/problems/find-k-th-smallest-pair-distance/solution/
 */
public class 找出第k小的数对距离_lc_710 {
    /**
     * 要找到第k小的数对距离，
     * 首先，数组内的所有数对的距离范围为[0,max(nums)-min(nums)]
     *      我们二分查找数对距离，
     *其次，在查找数对距离mid时，我们需要找到数组中小于mid的数对数量cnt，
     *         如果cnt>=k,说明数对距离应该更小，那么right = mid-1
     *         如果cnt<k,说明数对距离应该更大，使数对数量达到k，那么left = mid-1
     *并且，在计算小于mid的数对数量的过程中也可以使用二分查找，枚举所有的右端点j，
     *      二分查找大于等于nums[j]-mid的最小值的下标i，则右端点为j且距离小于等于mid的数对数目为j-i，计算数目之和。
     *
     *
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0;
        int right = nums[n-1]-nums[0];
        //找距离
        while(left<=right){
            int mid = (right-left)/2 +left;
            //找到大于等于mid的坐标i，右端点j-i就是我们要找的数对差小于mid的数量，统计数量之和，
            //根据数量之和再来缩放距离边界
            int cnt = 0;
            for(int j=0;j<n;j++){
                int i = binarySearch(nums,j,nums[j]-mid);
                cnt += j-i;
            }

            if(cnt<k){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }

        return left;
    }

    private int binarySearch(int[] nums, int end, int target) {
        int left = 0;
        int n = nums.length;
        int right = end;
        while(left<right){
            int mid = (right-left)/2 + left;
            if(nums[mid]<target){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return left;

    }


    /**数组.双指针+二分
     * 找到小于mid的距离对数量可以使用双指针取代
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair1(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0;
        int right = nums[n-1]-nums[0];
        //找距离
        while(left<=right){
            int mid = (right-left)/2 +left;
            //找到大于等于mid的坐标i，右端点j-i就是我们要找的数对差小于mid的数量，统计数量之和，
            //根据数量之和再来缩放距离边界
            int cnt = 0;
            for(int i=0,j=0;j<n;j++){
                //如果窗口内值大于mid，需要右移做指针缩小窗口
                while(nums[j]-nums[i]>mid){
                    i++;
                }
                cnt+= j-i;
            }

            if(cnt<k){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }

        return left;
    }

}
