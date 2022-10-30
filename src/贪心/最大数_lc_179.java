package 贪心;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zhp
 * @date 2022-07-31 15:59
 * https://leetcode.cn/problems/largest-number/
 */
public class 最大数_lc_179 {


    /**
     * 贪心
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        String strs [] = new String[nums.length];
        for(int i=0;i<nums.length;i++){
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (o1,o2)->{

            return (o2+o1).compareTo(o1+o2);
        });
        String ans = "";
        for(String string : strs){
            ans+=string;
        }
        int len = ans.length();
        int k = 0;
        while (k < len - 1 && ans.charAt(k) == '0') k++;
        return ans.substring(k);


    }
}
