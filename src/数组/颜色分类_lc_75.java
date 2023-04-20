package 数组;

/**
 * @author zhp
 * @date 2022-07-15 14:40
 * https://leetcode.cn/problems/sort-colors/
 */
public class 颜色分类_lc_75 {
    /**类桶排序
     * 使用color数组记录3种颜色的数量
     * 在将数组设置未color数组内设定数量
     */
    public void sortColors(int[] nums) {
        int colors[] = new int[3];

        for(int i=0;i<nums.length;i++){
            colors[nums[i]]++;
        }

        for(int i=0;i<nums.length;i++){
            for(int j=0;j<3;j++){
                if(colors[j]!=0){
                    nums[i] = j;
                    colors[j]--;
                }
            }
        }


    }

    /**
     * 单指针双遍历
     * 第一遍遍历将等于0的数放在数组头部
     * 第二遍遍历将等于1的数组放在所有0之后
     * 就实现了排序
     */
    public void sortColors1(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
        for (int i = ptr; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
    }


    /**
     * 数组.双指针
     * p指向首项，q指向尾项。
     * 我们把0放在p指向的位置，2放在q指向的位置，最后一定是数组中的0，1，2都排序成功。
     * 但是如果2交换出来的数不是1，那么该数就是0，一定对该数进行重新放置，所以要将i--。
     */
    public void sortColors2(int[] nums) {
        int n = nums.length;
        int p = 0;
        int q = nums.length-1;
        for(int i = 0;i<=q;i++){
            if(nums[i]==0){
                nums[i] = nums[p];
                nums[p] = 0;
                p++;
            }else if(nums[i]==2){
                nums[i] = nums[q];
                nums[q] = 2;
                q++;
                if(nums[i]!=1){
                    i--;
                }
            }
        }
    }


}
