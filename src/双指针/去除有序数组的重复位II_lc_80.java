package 双指针;

/**
 * @author zhp
 * @date 2022-07-15 13:58
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/
 */
public class 去除有序数组的重复位II_lc_80 {
    /**
     * 双指针的快慢指针
     * 保留所有数，出现超过两次的数只保留两次。
     * 我们需要辨别一个数出现的是否超过两次，
     * 则使用快慢指针，
     * 快指针指向位未归类的数组首项，慢指针指向已经归类的数组的尾项。
     * 我们只需要判断nums[slow-2]==nums[fast],因为满足这个条件的数一定出现了两次以上，从而无须保存。
     * 并且数组的最前面两项不需要判断。
     */
    public int removeDuplicates(int[] nums) {
        if(nums.length==2){
            return 2;
        }

        int slow = 2;
        int fast = 2;
        int n = nums.length;
        while(fast<n){
            if(nums[slow-2]!=nums[fast]){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        return slow;
    }
}

