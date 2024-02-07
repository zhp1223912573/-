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
     *    作者：max-LFszNScOfE
     *         链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-array/solution/shuang-zhi-zhen-shan-chu-zhong-fu-xiang-dai-you-hu/
     *         来源：力扣（LeetCode）
     *         著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while(q < nums.length){
            if(nums[p] != nums[q]){
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;


    }

    // 快慢指针，快指针指向为排序的第一个数，慢指针指向排序后的最后一个数
    public int removeDuplicates1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
