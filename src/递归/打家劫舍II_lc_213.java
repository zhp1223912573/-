package 递归;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2022-09-06 21:36
 * 与打家劫舍1一致，都是典型的dp问题，分析问题状态，得到状态方程即可。
 * 但唯一的不同点在于，该题中的房屋是成圆环状态的，第一家和最后一家是相邻的，不能同时偷两者。
 * 但，我们可以分析情况，既然第一家和最后一家不可以同时选择，那么，我们分别计算两种情况下的最大值，
 * 1.去除最后一家的情况，
 * 2.去除第一家的情况，
 * 上述两者既符合条件，分解后的情形也和打家劫舍1完全一致。
 */
public class 打家劫舍II_lc_213 {

    /**
     * 记搜
     *
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {

        if(nums.length==1) return nums[0];
        dp = new int[nums.length];
        Arrays.fill(dp,-1);
        int ans = dfs(0,nums.length-1,nums);
        Arrays.fill(dp,-1);
        int ans1 = dfs(1,nums.length,nums);
        return Math.max(ans,ans1);
    }
    int dp[];
    int dfs(int index,int end,int nums[]){
        if(index>=end) return 0;
        if(dp[index]!=-1) return dp[index];
        return dp[index] = Math.max(dfs(index+1,end,nums),dfs(index+2,end,nums)+nums[index]);
    }

    public int rob(int[] nums) {
        //特殊情况
        if(nums==null||nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        if(nums.length==2) return Math.max(nums[0],nums[1]);

        //分别获取上述两种情形最大值
        int noStealLast = rob(nums,0,nums.length-1);
        int noStealFirst = rob(nums,1,nums.length);

        return Math.max(noStealFirst,noStealLast);
    }

    /**
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    int rob(int []nums,int start,int end){
        //此处的x和y分别充当打劫第i家时，第i-2家的最大收益（x），第（i-1）家的最大收益（y)，
        //z表示决定偷还是不偷第i家后的最大值
        //通过上述变量取代了打家劫舍中的dp数组
        int x=0,y=0,z=0;
        for(int i=start;i<end;i++){
            y=z;
            z=Math.max(x+nums[i],y);
            x=y;
        }
        return z;
    }
}
