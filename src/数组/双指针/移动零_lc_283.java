package 数组.双指针;

/**
 * @author zhp
 * @date 2022-07-15 12:26
 * https://leetcode.cn/problems/move-zeroes/solution/yi-dong-ling-by-leetcode-solution/
 *
 *
 */
public class 移动零_lc_283 {
    /**
     * 数组.双指针
     * 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
     * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
     *
     * 注意到以下性质：
     *
     * 1.左指针左边均为非零数；
     *
     * 2.右指针左边直到左指针处均为零。
     *
     * 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
     */
    public void moveZero(int []nums){
        int n=nums.length;
        int left=0;
        int right=0;
        while(right<n){
            if(nums[right]!=0){
                swap(nums,left,right);
                left++;
            }
            right++;
        }
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


    /**
     * 直接位0的值覆盖，最后在数组尾巴加上总共的0的数量即可，
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int indexNow = 0;
        int indexNum = 0;
        int m = nums.length;

        while(indexNum<m){
            if(nums[indexNum] != 0) {
                nums[indexNow++] = nums[indexNum];
            }
            ++indexNum;
        }

        for(int i = indexNow; i < m; i++){
            nums[i] = 0;
        }
    }
}
