package 数组;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2022-07-15 21:13
 * https://leetcode.cn/problems/find-pivot-index/solution/
 */
public class 寻找数组的中心索引_lc_724 {
    /**
     * 前缀和
     * 整个数组和为total，数组左边和为sum，当前遍历到的数组元素为sumi，右半边数组和为total-sumi-sum
     * 欲使左边和等于右边和：sum = total-sumi-sum ==> total = 2*sum+sumi
     * 按照上述推导公式编写判断条件即可
     */
    public int pivotIndex(int[] nums){
                int total = Arrays.stream(nums).sum();
                int sum = 0;//左半边数组和
                for(int i=0;i<nums.length;i++){
                    if(2*sum+nums[i]== total){
                        return i;
                    }
                    sum += nums[i];
                }
                return -1;

    }
}
