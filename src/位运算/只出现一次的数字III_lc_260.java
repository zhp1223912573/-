package 位运算;

/**
 * @author zhp
 * @date 2022-07-18 14:13
 * https://leetcode.cn/problems/single-number-iii/
 */
public class 只出现一次的数字III_lc_260 {
    /**
     * 位运算
     *整个数组异或得到num，num位a^b；
     * 找到num的最低位位1的位置，通过该位置，将数值分为两部分，
     * 再次将num与其中一组进行异或，一定会与a或者b相异或，那么就可以得到b或a，
     * 最后再通过以及得到的a或b得到另一值。
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        int num = 0;
        for(int i:nums){
            num ^=i;
        }
        //得到num的最低位1的位置
        int n1 = num;
        int index = num&(-num);
        for(int i:nums){
            if((i&index)!=0){
                n1^=i;
            }
        }
        int n2 = num^n1;
        return new int[]{n1,n2};
    }

}
