package 双指针;

/**
 * @author zhp
 * @date 2022-07-15 12:40
 * https://leetcode.cn/problems/remove-element/
 *
 * 移动零的扩展，只是移动的不是0，是指定的val值
 */
public class 移除元素_lc_27 {
    /**
     *双指针
     */
    public static int removeElement(int[] nums, int val) {
        if(nums==null){
            return 0;
        }

        int left = 0;
        int right = 0;
        int n = nums.length;
        while(right<n){
            if(nums[right]!=val){
                nums[left] = nums[right];
                left++;
            }
            right++;

        }
        return left;
    }

    public static void main(String[] args) {
        int nums[] = {1,1};
        System.out.println(removeElement(nums,1) );
    }
}
