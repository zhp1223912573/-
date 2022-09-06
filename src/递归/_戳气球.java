package 递归;

/**
 * @author zhp
 * @date 2022-07-12 21:07
 * KMP算法扩展题目二 给定一个数组 arr，代表一排有分数的气球。每打爆一个气球都能获得分数，假设打爆气
 * 球 的分数为 X，获得分数的规则如下:
 * 1)如果被打爆气球的左边有没被打爆的气球，找到离被打爆气球最近的气球，假设分数为
 * L;如果被打爆气球的右边有没被打爆的气球，找到离被打爆气球最近的气球，假设分数为
 * R。 获得分数为 L*X*R。
 * 2)如果被打爆气球的左边有没被打爆的气球，找到离被打爆气球最近的气球，假设分数为
 * L;如果被打爆气球的右边所有气球都已经被打爆。获得分数为 L*X。
 * 3)如果被打爆气球的左边所有的气球都已经被打爆;如果被打爆气球的右边有没被打爆的
 * 气球，找到离被打爆气球最近的气球，假设分数为 R;如果被打爆气球的右边所有气球都
 * 已经 被打爆。获得分数为 X*R。
 * 4)如果被打爆气球的左边和右边所有的气球都已经被打爆。获得分数为 X。
 * 目标是打爆所有气球，获得每次打爆的分数。通过选择打爆气球的顺序，可以得到不同
 * 的
 * 总分，请返回能获得的最大分数
 *
 * 【举例】
 * arr = {3,2,5}
 * 如果先打爆3，获得3*2；再打爆2，获得2*5；最后打爆5，获得5；最后总分21
 * 如果先打爆3，获得3*2;再打爆5，获得2*5；最后打爆2，获得2；最后总分18
 * 如果先打爆2，获得3*2*5；再打爆3，获得3*5；最后打爆5，获得5；最后总分50
 * 如果先打爆2，获得3*2*5；再打爆5，获得3*5；最后打爆3，获得3；最后总分48
 * 如果先打爆5，获得2*5；再打爆3，获得3*2；最后打爆2，获得2；最后总分18
 * 如果先打爆5，获得2*5;再打爆2，获得3*2；最后打爆3，获得3；最后总分19
 * 返回能获得的最大分数为50
 */
public class _戳气球 {
    /**递归
     * 设置函数f(L,R)，L-1和R+1的气球没被戳爆，f(L,R)返回戳爆L-R范围内气球的最大值。
     * 我们在f函数尝试着让L-R上的每个气球都最后戳爆，看看能够得到的最大值是多少。
     * 1.首先尝试戳爆L或者R位置的气球，则
     *    max = Math.max(arr[L-1]*arr[L]*arr[R+1]+f(L+1,R),arr[L-1]*arr[R]*arr[R+1]+f(L,R-1))
     * 2.其次戳爆L-R范围内的i气球，则
     *      max = Math.max(max, arr[L-1]*arr[i]*arr[R+1] + f(L~i-1)+f(i+1~R))
     *
     */
    public static int getMaxScore(int arr[]){
        if(arr==null || arr.length==0){
                return 0;
        }

        if(arr.length==1){
                return arr[0];
        }

        //在原数组的两侧添加1，方便边界的处理计算
        int help[] = new int[arr.length+2];
        help[0]=1;
        help[arr.length+1]=1;
        for(int i=1;i<arr.length+1;i++){
            help[i] = arr[i-1];
        }

        return process(help,1,arr.length);
    }

    /**
     * 返回区间L-R上的气球戳爆的最大值
     * 假设arr[L-1]和arr[R+1]一定没有被打爆
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int process(int[] arr, int L, int R) {
        //只剩下一个值,直接打爆
        if(L==R){
            return arr[L-1]*arr[L]*arr[R+1];
        }
        //分别尝试最后打爆L和R的气球，比较两者
        int max = Math.max(arr[L-1]*arr[L]*arr[R+1]+process(arr,L+1,R),
                arr[L-1]*arr[R]*arr[R+1]+process(arr,L,R-1));

        //尝试最后打爆中间位置的气球
        for(int i=L+1;i<R;i++){
            max = Math.max(max,arr[L-1]*arr[i]*arr[R+1]+process(arr,L,i-1)+process(arr,i+1,R));
        }

        return max;
    }

    /**
     * dp
     * 添加dp二维数组，dp[L][R]表示范围L-R上的最大分数，也就是上述递归函数的函数意义
     * 基本数按递归函数改写就行了。
     *
     */
    public static int getMaxScoreBydp(int arr[]){
        if(arr==null || arr.length==0){
            return 0;
        }

        if(arr.length==1){
            return arr[0];
        }

        int n = arr.length;
        //在原数组的两侧添加1，方便边界的处理计算
        int help[] = new int[n+2];
        help[0]=1;
        help[n+1]=1;
        for(int i=1;i<n+1;i++){
            help[i] = arr[i-1];
        }

        int dp[][] = new int[n+2][n+2];
        for(int i=1;i<=n;i++){
            /*
              //只剩下一个值,直接打爆
        if(L==R){
            return arr[L-1]*arr[L]*arr[R+1];
        }
             */
            dp[i][i]= help[i-1]*help[i]*help[i+1];
        }

        for (int L = n; L >= 1; L--) {
            for (int R = L + 1; R <= n; R++) {
                // 求解dp[L][R]，表示help[L..R]上打爆所有气球的最大分数
                // 最后打爆help[L]的方案
                int finalL = help[L - 1] * help[L] * help[R + 1] + dp[L + 1][R];
                // 最后打爆help[R]的方案
                int finalR = help[L - 1] * help[R] * help[R + 1] + dp[L][R - 1];
                // 最后打爆help[L]的方案，和最后打爆help[R]的方案，先比较一下
                dp[L][R] = Math.max(finalL, finalR);
                // 尝试中间位置的气球最后被打爆的每一种方案
                for (int i = L + 1; i < R; i++) {
                    dp[L][R] = Math.max(dp[L][R], help[L - 1] * help[i]
                            * help[R + 1] + dp[L][i - 1] + dp[i + 1][R]);
                }
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int arr[] ={3,2,5};
        System.out.println(getMaxScore(arr));
        System.out.println(getMaxScoreBydp(arr));
    }
    /**
     * 记搜
     */
//    int dp[][];
//    public int maxCoins(int[] nums) {
//
//        int arr[] = new int [nums.length+2];
//        for(int i=0;i<nums.length;i++){
//            arr[i+1] = nums[i];
//        }
//        arr[0] = 1;
//        arr[nums.length+1] = 1;
//        dp = new int[nums.length+2][nums.length+2];
//
//        return process(arr,1,nums.length);
//    }
//
//
//
//    public int process(int[] nums,int l,int r){
//        if(l==r){
//
//            dp[l][l]=nums[l-1]*nums[l]*nums[r+1];
//            return dp[l][l];
//        }
//
//        if(dp[l][r]!=0){
//            return dp[l][r];
//        }
//
//        //分别戳爆左右两侧气球
//        int max = Math.max(nums[l-1]*nums[l]*nums[r+1]+process(nums,l+1,r),
//                nums[l-1]*nums[r]*nums[r+1]+process(nums,l,r-1));
//
//        //尝试戳爆L+1--R-1上的所有气球
//        for(int i=l+1;i<r;i++){
//            max = Math.max(max,nums[l-1]*nums[i]*nums[r+1]+process(nums,l,i-1)+process(nums,i+1,r));
//        }
//
//        dp[l][r] = max;
//        return dp[l][r];
//    }



}
