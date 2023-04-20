package 递归;

import com.oracle.net.Sdp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhp
 * @date 2022-09-06 21:24
 * https://leetcode.cn/problems/house-robber/
 */
public class 打家劫舍_lc_198 {

    /**
     * 记搜
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
        dp = new int[nums.length];
        Arrays.fill(dp,-1);
        return dfs(0,nums);
    }

    int dp[] ;
    public int dfs(int index,int nums[]){
        if(index>=nums.length) return 0;
        if(dp[index]!=-1) return dp[index];
        return dp[index] = Math.max(dfs(index+1,nums),dfs(index+2,nums)+nums[index]);
    }

    /**经典的dp问题
     * 设定偷到第i家时的最大收益为dp[i]
     * 分析状态，每家被了偷之后，该家前面的家庭都不能被偷，所以需要选择偷与不偷当前家庭，
     * 则需要判断偷和不偷情况下的最大值，从而得到最终的最大值。
     * 综上可以得到递推式：
     * dp[i] = Max(dp[i-1],dp[i-2]+nums[i])
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if(nums==null||nums.length==0) return 0 ;
        if(nums.length==1) return nums[0];

        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);

        for(int i=2;i<nums.length;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[dp.length-1];
    }


}
