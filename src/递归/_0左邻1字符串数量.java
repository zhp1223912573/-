package 递归;

/**
 * @author zhp
 * @date 2022-07-05 14:46
 *
 * 字符串只由'0'和'1'两种字符构成，
 * 当字符串长度为1时，所有可能的字符串为"0"、"1"；
 * 当字符串长度为2时，所有可能的字符串为"00"、"01"、"10"、"11"；
 * 当字符串长度为3时，所有可能的字符串为"000"、"001"、"010"、"011"、"100"、
 * "101"、"110"、"111"
 *
 * 如果某一个字符串中，只要是出现'0'的位置，左边就靠着'1'，这样的字符串叫作达
 * 标字符串。
 * 给定一个正数N，返回所有长度为N的字符串中，达标字符串的数量。
 * 比如，N=3，返回3，因为只有"101"、"110"、"111"达标
 */
public class _0左邻1字符串数量 {

    /**暴力法
     *
     */
    public static int process(int n){
        int res = 0;
        int num = (int)Math.pow(2.0,n)-1;
        while(num!=0){
            if(test(num,n)){
                res++;
            }
            num--;
        }
        return res;
    }

    /**
     * 检测数num的从右到左的n位里，0的左边是不是都不是1
     * @param num
     * @param n
     * @return
     */
    private static boolean test(int num, int n) {
        int index=1;
        //如果第一位为0直接返回false
        if((num&(index<<(n-1)))==0){
            return false;
        }
        while(n!=0){
            int next = index<<1;
            if((index&num)==0 && ((next&num)==0)){
                return false;
            }
            index<<=1;
            n--;
        }
        return true;
    }

    /**打表法
     * 通过暴力法打表
     * n为：1 达标数字总数：1
     * n为：2 达标数字总数：2
     * n为：3 达标数字总数：3
     * n为：4 达标数字总数：5
     * n为：5 达标数字总数：8
     * n为：6 达标数字总数：13
     * n为：7 达标数字总数：21
     * n为：8 达标数字总数：34
     * n为：9 达标数字总数：55
     * n为：10 达标数字总数：89
     * 。。。。。。。。。。。。。
     *
     * 发现规律
     * f（n） = f(n-1)+f(n-2) (n>=3)
     *发现这是一个类费波切纳问题，所以可以有两种解题方法。
     *
     *
     *
     */

    /**方法1：dp
     * 状态转化方程： f（n） = f(n-1)+f(n-2) (n>=3) f（n）
     * 上述公式就是状态转化方程，而数组对应的值就是对应小标位时符合规定的数的数量，所以时间,空间复杂度位O(n),
     */
    public static int process1(int n){
        if(n<3){
            if(n==1 || n==2){
                return n;
            }
            return -1;
        }

        int dp[] = new int[n];
        dp[0]=1;
        dp[1]=2;
        for(int i=2;i<n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n-1];
    }

    /**方法2：快速幂
     *使用费波切纳问题的套路解答，基于公式得到系数矩阵，再通过快速幂的思想得到目标值的答案，
     * 该方法的时间复杂度主要在矩阵的幂运算上，通过快速幂的思想，可以达到O(logn)
     */
    public static int process2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        //系数矩阵，通过方程，手算可得
        int[][] base = { { 1, 1 }, { 1, 0 } };
        //二阶
        int[][] res = matrixPower(base, n - 2);
        //fn =
        return 2 * res[0][0] + res[1][0];
    }
    //快速幂
    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }
    //矩阵乘法
    public static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    /**推导思路：
     * 上述的打表法得到的方程的含义是不明确的，只是我们在大量数据下的一种规律发现与总结。
     * 在这里，具体地对该方程的推导过程进行分析。
     *      首先，一个长为n的二进制字符串，他的第n-1位必须是1，否则该数就直接不合法。
     *      接着，确定了第一位后，就是后面的第i位。第i位有两种选择，放1还是0。
     *              如果放1，那么当前字符的数量就取决与下一个字符的数量，即第i-1位时的数量。
     *              而如果放0，那么当前字符的下一位第i-1就必须放1，否则就不合法，此时当前位的数量就取决与第i-2时的数量。
     *      综上，我们就可以得到方程 ：f（n）= f(n-1)+f(n-2) (n>=3)
     *
     * @param args
     */

    public static void main(String[] args) {
        for (int i =1; i < 20 ; i++) {
            System.out.println("n为："+i+" 达标数字总数："+process(i));
            System.out.println("n为："+i+" 达标数字总数："+process1(i));
            System.out.println("n为："+i+" 达标数字总数："+process2(i));
            System.out.println("************************************");
        }
    }
}
