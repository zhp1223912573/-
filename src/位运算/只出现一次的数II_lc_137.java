package 位运算;

/**
 * @author zhp
 * @date 2022-07-18 13:53
 * https://leetcode.cn/problems/single-number-ii/
 */
public class 只出现一次的数II_lc_137 {
    /**
     * hashmap保存出现次数
     */


    /**位运算
     * 只有一个数字出现一次，其他的都出现了3次，异或运算无效。
     * 所有数字都是整型32位，我们计算所有数字的32位的出现次数，
     * 因为只有一个数字出现了一次，那么其他所有数字的每个位出现的次数一定是3的倍数，
     * 我们只要找出不是3的倍数的位即可。
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for(int i=0;i<32;i++){
            int total = 0;
            for(int num:nums){
                total += (num>>i)&1;
            }

            if(total%3!=0){
                ans |= 1<<i;
            }
        }
        return ans;
    }

    /**
     * 进阶：数电分析
     * https://leetcode.cn/problems/single-number-ii/solution/zhi-chu-xian-yi-ci-de-shu-zi-ii-by-leetc-23t6/
     */
}
