package 数组.双指针;

/**
 * @author zhp
 * @date 2022-07-15 13:27
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 *
 */
public class 去除有序数组的重复位_lc_26 {
    /**
     * 双指针的快慢指针
     * 要想去除重复的位，只保留一次某个数，
     * 那么我们需要区别一个数是第一次出现还是非第一次出现，
     * 只需要fast指向的将当前数和前一个数进行比较，
     * 如果一致，说明不是第一次出现，
     * 如果不一致，表示当数是第一次出现，
     * 再套用经典的快慢指针解法解答即可。
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if(nums==null){
            return 0;
        }

        if(nums.length==1){
            return 1;
        }

        int left = 0;
        int right = 0;
        int n = nums.length;
        while(right<n){
            if(nums[right]!=nums[right-1]){//该数字第一次出现
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        return left+1;
    }
}
