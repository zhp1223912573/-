package 位运算;

/**
 * @author zhp
 * @date 2022-07-18 13:51
 * https://leetcode.cn/problems/single-number/
 */
public class 只出现一次的数字I_lc_136 {
    /**
     * 利用出现两次的特点，结合异或运算，同一个数两次异或一定为0，所以最后剩下的一定是只出现一次的数
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for(int i=0;i<nums.length;i++){
            ans^=nums[i];
        }
        return ans;
    }
}
