package 递归;

import sun.util.resources.nl.CalendarData_nl;

/**
 * @author zhp
 * @date 2022-07-19 22:09
 * https://leetcode.cn/problems/target-sum/
 */
public class 目标和_lc_494 {
    /**
     * 深搜
     * 枚举所有可能
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays1(int[] nums, int target) {
        dfs(nums,target,0,0);
        return count;
    }
    int count = 0 ;
    public void dfs(int[] nums,int target,int index,int sum){
        if(index==nums.length){
            if(sum==target){
                count++;
            }
        }else{
            dfs(nums, target,index+1,sum+nums[index]);
            dfs(nums, target,index+1,sum-nums[index]);
        }
    }

    /**dp
     * 将该问题理解为01背包问题，一个数有两种状态，取正数或者负数时。
     * 定义：
     *      设置dp[i][j]表示i之前的数值都可以使用时，构成目标值为j的方法有几种。
     * 状态转移方程：
     *      因为每个数可以取正负两种状态，，所以当前取数的方法，取决于上一层取数方法时相同数值+-当前新加的数的数的方法之和
     *      dp[i][j] = dp[i-1][j-nums[i]]+dp[i-1][j+nums[i]]
     * 初始状态：
     *      当i为0时，只有数值0可以取，所以dp[0][0]=1;表示一种方法
     *
     * j既然表示所有中间结果，假定全为+号时，数组和为s，反之全为负号时，数组和为-s
     * 则j的表示范围为[-s,s]为了在dp上正确表示，需要将数组右移s位。
     *
     * 最终我们要求的结果是dp[n][t+s],t表示目标值
     */
    public int findTargetSumWays(int[] nums, int t) {
        //先求出数组的正整数和
        int s = 0;
        int n = nums.length;
        for(int num:nums){
            s+=Math.abs(num);
        }
        //特殊情况，目标值大于数组最大和
        if(Math.abs(t)>s){
            return 0;
        }
        int dp[][] = new int[n+1][2*s+1];
        //dp[0][0] = 1
        dp[0][s] = 1;
        for(int i=1;i<=n;i++){
            int num = nums[i-1];//取出此轮可以使用的数值
            for(int j=-s;j<=s;j++){
                //因为整个数组进行了移动，所以在传入当前的数值之前要保证加入的数不会超出数组边界
                if((j-num)+s>=0){
                    dp[i][j+s]+=dp[i-1][(j-num)+s];
                }
                if((j+num)+s<=2*s){
                    dp[i][j+s]+=dp[i-1][(j+num)+s];
                }
            }
        }
        return dp[n][t+s];
    }

    /**
     * 动态规律优化
     * 讲解得非常好，可以去原帖仔细阅读。
     * https://leetcode.cn/problems/target-sum/solution/gong-shui-san-xie-yi-ti-si-jie-dfs-ji-yi-et5b/
     */
    public int findTargetSumWays2(int[] nums, int t){
        int n = nums.length;
        int s =0;

        for(int num:nums){
            s+=Math.abs(num);
        }

        if(t>s || (s-t)%2!=0){
            return 0;
        }


        int m = (s-t)/2;
        int dp[][] = new int[n+1][m+1];
        dp[0][0] =1;
        for(int i=1;i<=n;i++){
            int x = nums[i-1];
            for(int j=0;j<=m;j++){
                dp[i][j] += dp[i-1][j] ;
                if(j-x>=0){
                    dp[i][j] += dp[i-1][j-x];
                }
            }
        }
        return dp[n][m];

    }

}
