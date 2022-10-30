package 递归;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhp
 * @date 2022-10-26 7:03
 * https://leetcode.cn/problems/largest-divisible-subset/solution/zui-da-zheng-chu-zi-ji-by-leetcode-solut-t4pz/
 */
public class 最大整除子集_lc_368 {
    /**
     * 定义dp[i]为必须选择num[i]时，最大的符合条件子集。
     * 则遍历到num[i]，枚举num[0...i-1]的数值，找到符合条件的最大的子集长度
     * 同时记录，最大长度和最大值。
     * 最后，反向遍历dp数组，得到最大子集合。
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        int dp[] = new int[n];
        Arrays.sort(nums);
        Arrays.fill(dp,1);
        int maxvalue = 1;
        int maxlength = 1;
        for(int i=1;i<n;i++){
            for(int j =0;j<i;j++){
                if(nums[i]%nums[j]==0){
                    dp[i] = Math.max(dp[j]+1,dp[i]);
                }
            }
            if(dp[i]>maxlength){
                maxlength = dp[i];
                maxvalue = nums[i];
            }
        }


        List<Integer> res = new ArrayList<>();

        if(maxlength==1){
            res.add(nums[0]);
            return res;
        }
        for(int i=n-1;i>=0&&maxlength>0;i--){
            if(maxlength==dp[i]&&maxvalue%nums[i]==0){
                res.add(nums[i]);
                maxlength--;
                maxvalue=nums[i];
            }
        }
        return res;
    }
}
