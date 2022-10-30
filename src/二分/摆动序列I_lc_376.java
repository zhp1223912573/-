package 二分;

/**
 * @author zhp
 * @date 2022-07-30 14:29
 * https://leetcode.cn/problems/wiggle-subsequence/
 */
public class 摆动序列I_lc_376 {
    /**贪心
     * 顺序扫描数组，如果num[i]-nums[i-1]的结果和上一轮的结果不一致（指一个为正数一个为负数）则当前位
     * 符合摆动序列。
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int prevdiff = nums[1] - nums[0];
        int ret = prevdiff != 0 ? 2 : 1;
        for (int i = 2; i < n; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
                ret++;
                prevdiff = diff;
            }
        }
        return ret;

    }

    /**动规
     * down记录当前遍历得到的以某个元素结尾的最长下降摆动子序列的长度
     * up记录当前遍历得到的以某个元素结尾的最长上升摆动子序列长度
     *
     *https://leetcode.cn/problems/wiggle-subsequence/solution/bai-dong-xu-lie-by-leetcode-solution-yh2m/
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength1(int[] nums) {
        int n = nums.length;
        if(n<2){
            return n;
        }

        int down =1,up =1;
        for(int i=1;i<n;i++){
            if(nums[i]-nums[i-1]>0){
                up = Math.max(up,down+1);
            }else if(nums[i]-nums[i-1]<0){
                down = Math.max(up+1, down);
            }
        }
        return Math.max(up,down);
    }
}
