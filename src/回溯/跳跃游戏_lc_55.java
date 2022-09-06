package 回溯;

/**
 * @author
 * @date 2022-07-26 1:30
 * https://leetcode.cn/problems/jump-game/submissions/
 */
public class 跳跃游戏_lc_55 {

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
                return false;
            }
        }
        return false;
    }

}
