package 递归;

/**
 * @author zhp
 * @date 2022-06-16 21:20
 * 给定一个数组，包含不重复的数值，每个数值表示一种货币的面值。
 * 现在给定一个目标数值aim，每种货币可以选0次或无数次，请问构成数值rest的方法有多少种？
 *
 * 典型的完全背包问题，
 * 这里通过编写分并析递归---》一般dp----》精简dp的函数分析过程来理解一个典型的动态规划问题。
 */
public class _选取纸币 {

    /**方法1 经典递归
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int way1(int[]arr,int aim){
        return process(arr,0,aim);
    }

    /**经典递归
     *思路：
     * 遍历每种货币，并枚举每种货币在限定范围内的选择情况，递归求解所有货币的选择情况，
     * 当递归终止时，判断该递归过程的货币选择方法是否合法，合法返回1，不合法返回0。
     * @param arr
     * @param index
     * @param aim
     * @return
     */
    private static int process(int[] arr, int index, int aim) {
        if(index == arr.length){//遍历到数末尾，可以判断当前的方法是否符合要求
            return aim == 0 ? 1:0;//等于0说明递归过程选择的货币正好等于目标值
        }

        int res = 0;//方法总数
        for(int zhang=0;zhang*arr[index]<=aim;zhang++){//枚举当前货币的所有选择情况
            res += process(arr,index+1,aim-zhang*arr[index]);
        }

        return res;
    }

    /**方法2 一般dp
     *
     * 通过上述递归函数的编写，我们可以发现，在递归过程中存在变量-->当前货币的数目，以及每种价值（0-aim）的选择方法总数。
     * 我们可以使用一个二维数值来表示该递归过程。
     * (aim为10，货币种类为4，{2,3,7,4}）
     * 例子：
     * 横坐标为当前货币的坐标，纵坐标为当前货币总数
     * 只有四种货币，我们将横坐标4，也就是第5行设定为什么都不选，那么可以初始化为为如下情况
     * 我们需要从行3开始变量，对只能选择当前货币及其以下的货币所构成的总数进行枚举计算。
     *
     *    0  1 2 3 4 5 6 7 8 9 10
     * 0 1  0 1 1 2 1 3 3 4 6 6
     * 1 1  0 0 1 1 0 1 2 1 2 2
     * 2 1  0 0 0 1 0 0 1 1 0 0
     * 3 1  0 0 0 1 0 0 0 1 0 0
     * 4 1  0 0 0 0 0 0 0 0 0 0
     *
     * 每遍历一种货币，都需要对其所有可能出现的情况进行枚举，然后计算出总的次数。
     *
     *
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int way2(int arr[],int aim){
        if(arr == null || arr.length <1){
            return 0;
        }
        int N = arr.length;
        int [][]dp = new int[N+1][aim+1];
        dp[N][0] =1;

        for(int index=N-1;index >= 0;index--){
            for(int rest =0;rest<=aim;rest++){
                int ways = 0;
                for(int zhang =0;arr[index]*zhang <=rest;zhang++){
                    ways += dp[index+1][rest-arr[index]*zhang];//根据下面的情况推导当前的情况
                }
                dp[index][rest] = ways;
            }
        }

        for(int i=0;i<=N;i++){
            for(int j=0;j<=aim;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        return dp[0][aim];
    }

    /**方法3 优化dp
     * 上述方法2中的时间复杂度为O(N*aim^2)
     * 结合上述的图标发现，使时间复杂度增加的原因在于对应每种货币的所有情况都需要枚举。
     * 我们再次分析数组，可以发现，其实不必要取枚举所有情况，
     * 因为当前行中 列-arr[列]位置上的值已经计算了所有的枚举情况，我们只需要在前者的基础上加当前列的上一行情况即可。
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int way3(int []arr,int aim){
        if(arr == null || arr.length <1){
            return 0;
        }
        int N = arr.length;
        int [][]dp = new int[N+1][aim+1];
        dp[N][0] =1;

        for(int index=N-1;index >= 0;index--){
            for(int rest =0;rest<=aim;rest++){
                dp[index][rest] = dp[index+1][rest];
                if(rest-arr[index]>=0){//防止前面的情况不存在，导致数组越界
                    dp[index][rest] += dp[index][rest-arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int arr [] = {2,3,7,4};
        int aim =11;
        System.out.println(way1(arr,aim)==way2(arr,aim));
        System.out.println(way1(arr,aim)==way3(arr,aim));
    }

}
