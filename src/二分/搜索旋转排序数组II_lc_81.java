package 二分;

/**
 * @author zhp
 * @date 2022-10-25 12:17
 * https://leetcode.cn/problems/search-in-rotated-sorted-array-ii/
 */
public class 搜索旋转排序数组II_lc_81 {
    /**
     * 本题是搜索旋转排序数组I的扩展，基本条件一致，只是数组中可能存在重复元素。
     * 解法和I中一样，数组始终分为有序和无序两种状态，我们二分查找target位置，
     * 缩小范围。但是存在重复数值，我们需要在左右指针出现重复时，移动指针，
     * 直到不重复。
     */
    public boolean search(int[] nums, int target) {
        int l = 0,n=nums.length;
        int r = n-1;
        while(l<=r){
            //去除重复项，才能得到有效的有序区间
            while(l<r&&nums[l]==nums[l+1]) l++;
            while(l<r&&nums[r]==nums[r-1]) r--;
            int mid = (r-l)/2+l;
            if(nums[mid]==target) return true;
            //前半部分有序
            if(nums[mid]>=nums[0]){
                if(nums[0]<=target&&nums[mid]>target){
                    r=mid-1;
                }else{
                    l=mid+1;
                }
            }else{//后半部分有序
                if(nums[mid]<target&&target<=nums[n-1]){
                    l=mid+1;
                }else{
                    r = mid-1;
                }
            }
        }
        return false;
    }
}
