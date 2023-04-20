package 递归;

/**
 * @author zhp
 * @date 2022-07-26 1:55
 * https://leetcode.cn/problems/jump-game-ii/solution/tiao-yue-you-xi-ii-by-leetcode-solution/
 */
public class 跳跃游戏II_lc_45 {

    /**
     * 记搜
     * @param nums
     * @return
     */
    public int jump4(int[] nums) {
        dp = new int[nums.length];
        return dfs(0,nums);
    }
    int dp[] ;
    public int dfs(int index,int nums[]){
        if(index>=nums.length-1){
            return 0;
        }
        int ans = 1000010;
        if(dp[index]!=0) return dp[index];
        for(int i=1;i<=nums[index];i++){
            ans = Math.min(dfs(index+i,nums)+1,ans);
        }

        return dp[index] = ans;
    }
    /**
     * dp
     * 定义：
     *      dp[i]表示从i到达最后一个位置所需的最小步数
     * 初始情况：
     *      dp内全部距离为MAX_VALUE,方便取最小值。
     *可能性分析：
     *      为了得到起始位置到最后一个位置的最少步数，也就是求dp[0]，
     *      需要从后往前得到每个位置到达最后位置的解，最终才能得到dp[0]
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if(nums==null||nums.length==0){
            return -1;
        }
        if(nums.length==1){
            return 0;
        }
        int n = nums.length;
        int dp[] = new int[n];
        dp[n-1]=0;//自己到自己为0
        for(int i=n-1;i>=0;i--){
            //为什么设置成10000？数值最大为1000，如果设置为Integer.MAX_VALUE,会出现溢出的情况。
            dp[i] = 10000;
            if(i+nums[i]>=n-1){//当前位置可以一部到达最后的位置
                dp[i] = 1;
                continue;
            }
            //不能一步达到，那就要看看当前位置所能走到的后续位置的最小步数，从而得到最小步数
            for(int j=i+1;j<=i+nums[i];j++){
                dp[i] = Math.min(dp[i],dp[j]+1);//加上当前步
            }
        }
        return dp[0];
    }

    /**贪心
     * 官方解答：
     * https://leetcode.cn/problems/jump-game-ii/solution/tiao-yue-you-xi-ii-by-leetcode-solution/
     *
     * @param nums
     * @return
     */
    public int jump1(int[] nums){
        int step = 0;
        int pos = nums.length-1;
        while(pos>0){
            for(int i=0;i<pos;i++){
                if(i+nums[i]>=pos){
                        pos = i;
                        step++;
                        break;
                }
            }
        }
        return step;
    }

    /**
     * 遍历当前位置所能跳到的最远位置，当到达当前轮次的边界时，
     * 更新最远位置，知道最后一个位置前一位置。
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        int n = nums.length;
        int maxpostion = 0;
        int end = 0;
        int steps = 0;
        for(int i=0;i<n-1;i++){
            maxpostion = Math.max(maxpostion,i+nums[i]);
            if(end==i){
                end = maxpostion;
                steps++;
            }
        }

        return steps;
    }
}
