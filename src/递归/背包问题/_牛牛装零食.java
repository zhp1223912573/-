package 递归.背包问题;

/**
 * @author zhp
 * @date 2022-07-05 19:23
 * 牛牛准备参加学校组织的春游, 出发前牛牛准备往背包里装入一些零食, 牛牛的背包容
 * 量为w。
 * 牛牛家里一共有n袋零食, 第i袋零食体积为v[i]。
 * 牛牛想知道在总体积不超过背包容量的情况下，他一共有多少种零食放法(总体积为0也
 * 算一种放法)。
 */
public class _牛牛装零食 {
    /**
     * 经典01背包问题
     *
     * dp[i][j] = (dp[i-1][j]+dp[i-1][j-arr[i])
     */
    public static int process(int arr[],int w){
        if(arr==null || arr.length<1){
            return 0;
        }

        int dp[][] = new int[arr.length][w+1] ;
        //对状态数组初始化
        for(int i=0;i<arr.length;i++){
            dp[i][0]=1;//当前背包容量为0时，什么都不装，也是一种方法
        }

        //初始化第一行
        for(int i=1;i<w+1;i++){
            dp[0][i] = arr[0]<i ? 2:1;//如果第一中零食体积小于容量大小，则更新为两种方法
        }

        //开始状态推导
        for(int i=1;i<arr.length;i++){
            for(int j=1;j<w+1;j++){
                //当前行的容量内的存放数量等于上一行同等容量的总数
                dp[i][j] = dp[i-1][j];
                //如果，当前行的物品可以加入，那么就加上他
                if(j-arr[i]>=0){
                    dp[i][j] += dp[i-1][j-arr[i]];
                }
            }
        }



        return dp[arr.length-1][w];
    }
}
