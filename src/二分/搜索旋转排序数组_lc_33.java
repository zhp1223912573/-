package 二分;

/**
 * @author zhp
 * @date 2022-07-20 19:15
 *https://leetcode.cn/problems/find-minimum-in-rotated-zsorted-array/
 */
public class 搜索旋转排序数组_lc_33 {
    /**
     * 任选数组中的某一位值，则选中的值的一侧一定有序，另一侧一定非有序。
     * 我们始终选择有序的那侧，再判断有序侧的数值是否为包含targe值的区间，
     * 是的话，我们不断缩小该区间，直到找到与target一致的位置值，
     * 反之说明target不在该区间内，向另一侧区间收缩。
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int m = nums.length;
        if(nums==null||m==0){
            return -1;
        }

        if(m==1) return target==nums[0]?0:-1;

        int left = 0;
        int right = m-1;
        while(left<=right){
            int mid = (right-left)/2+left;
            if(nums[mid]==target){
                return mid;
            }
            //找到有序的一侧，进行二分查找
            //左侧有序
            if(nums[0]<=nums[mid]){
                //target在左侧区间内，则向左侧收缩
                if(nums[0]<=target&&target<nums[mid]){
                    right = mid-1;
                }
                //反之，说明在右侧
                else{
                    left = mid+1;
                }
            }
            //右侧有序
            else{
                //target在右侧区间内，则向右侧收缩
                if(nums[mid]<target&&target<=nums[right]){
                    left = mid+1;
                }
                //反之，说明在右侧
                else{
                    right = mid-1;
                }
            }
        }
        return -1;
    }

}
