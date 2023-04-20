package 递归;

/**
 * @author
 * @date 2022-07-26 1:30
 * https://leetcode.cn/problems/jump-game/submissions/
 */
public class 跳跃游戏_lc_55 {

    public boolean canJump3(int[] nums) {
        dp = new int[nums.length];
        return dfs(0,nums);
    }
    int dp[];
    public boolean  dfs(int index,int nums[]){
        if(index>=nums.length-1) return true;
        if(dp[index]!=0) return dp[index]==1;
        for(int i=1;i<=nums[index];i++){

            if(dfs(index+i,nums)){
                dp[index] = 1;
                return true;
            }
        }
        dp[index]=-1;
        return false;
    }

    /**
     * dp
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int far [] = new int[n];
        for(int i=0;i<n;i++){
            far[i] = i+nums[i];//表示每个数能到达的最远距离
        }
        //dp[i]表示当前位置可以到达最后的位置与否
        boolean dp[] = new boolean[n];
        for(int i=n-1;i>=0;i--){
            if(far[i]>=n-1){
                dp[i] = true;
            }else{
                int num = i+1;
                while(num<=far[i]){
                    if(dp[num]) dp[i]=true;
                    num++;
                }
            }

        }
        return dp[0];

    }

    /**
     * 尝试
     * 限定最远能到达的位置，判断当前位置能否到达更远的位置，如果可以则更新最远距离，
     * 不能则继续往后移动（在小于最远距离的范围内移动），看看能不能更新最远距离，知道最远距离大于目标位置。
     */
    public boolean canJump1(int[] nums){
        if(nums==null){
            return false;
        }
        int k = 0;//当前最远能跳到的位置

        for(int i=0;i<=k;i++){
            int newk = i+nums[i];
            k = Math.max(k,newk);//看看能不能跳到更远的地方
            if(k>=nums.length-1){//最远能跳到最后一个
                return true;
            }
        }
        return false;
    }

}
