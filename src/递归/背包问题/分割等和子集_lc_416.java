package 递归.背包问题;

/**
 * @author zhp
 * @date 2023-04-03 16:59
 * https://leetcode.cn/problems/partition-equal-subset-sum/submissions/
 */
public class 分割等和子集_lc_416 {

    /**
     * 分割等和，也就是获得数组和一半的数值组合。
     * 先计算数组和，再转化为01背包即可
     * @param nums
     * @return
     */
    public boolean canPartition1(int[] nums) {
        int sum = 0;
        for(int num:nums) sum+=num;
        if(sum%2!=0) return false;
        int target = sum/2;
        int n = nums.length;
        boolean dp[][] = new  boolean [n+1][target+1];
        dp[0][0] = true;
        for(int i=1;i<=n;i++){
            for(int j=0;j<=target;j++){
                if(j>=nums[i-1]) dp[i][j] = dp[i-1][j]||dp[i-1][j-nums[i-1]];
                else dp[i][j] = dp[i-1][j];
            }
        }
        return dp[n][target];
    }

    /**
     *空间优化
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num:nums) sum+=num;
        if(sum%2!=0) return false;
        int target = sum/2;
        int n = nums.length;
        boolean dp[] = new  boolean [target+1];
        dp[0] = true;
        for(int i=1;i<=n;i++){
            for(int j=target;j>=nums[i-1];j--){
                dp[j] = dp[j-nums[i-1]]||dp[j];

            }
        }
        return dp[target];
    }
}
