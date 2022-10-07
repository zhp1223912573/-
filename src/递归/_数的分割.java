package 递归;

/**
 * @author zhp
 * @date 2022-07-12 11:57
 *
 * 给定一个正数1，裂开的方法有一种，(1)
 * 给定一个正数2，裂开的方法有两种，(1、1)、(2)
 * 给定一个正数3，裂开的方法有三种，(1、1、1)、(1、2)、(3)
 * 给定一个正数4，裂开的方法有五种，(1、1、1、1)、(1、1、2)、(1、3)、（2、2）、（4）
 * 给定一个正数n，求裂开的方法数
 */
public class _数的分割 {
    /**
     * 递归
     * 分割该数，可以分为上一次分割，和此次剩余值。
     * 给定一个数num，假定num是由num+1通过分割1得到的，我们直接设定初始函数为f(1,num).
     * 按照题目的含义，数的分割数必须是非递减的，所以一旦出现了pre>rest时，说明不存在新的分割方法，直接返回0，
     * 反之，如果rest为0，说明当前数分割完成，需要返回1，表示一种分割方案。
     */
    public static int way1(int num){
        if(num<0){
            return 0;
        }

        return process(1,num);
    }

    /**
     *
     * @param pre 上一次分割的部分
     * @param rest 剩下要分割的数值
     * @return
     */
    private static int process(int pre, int rest) {
        if(rest == 0 ){
            return 1;
        }

        if(pre >rest){
            return 0;
        }

        int ans = 0;
        for(int i=pre;i<=rest;i++){
            ans+=process(i,rest-i);
        }
        return ans;
    }


    /**一般dp
     * 通过上述递归过程可以转化为dp。
     * 设定dp二维数组，dp[pre][rest],数组含义为pre代表的上一部分和rest代表的剩余部分能分割的数量。
     * 则dp[1][rest]就是我们要求的目标值。
     * 初始情况：
     *      当rest为1时，说明分裂完成，代表一种分割方案，则dp[i][0]全为1
     *      而pre>rests时，由于题目要求分割方案必须为递增的，所以dp[pre][rest]为0
     * 可能性分析：
     *      实例分析：
     *      dp[7,7],上一部分为7，此次分割不能小于7，所以只能分割为{7，0}，查看dp[7][0]=1,则dp[7][7]=1
     *      dp[2,6],上一部分为2，此次分割不能小于2，有下述分割情况
     *      {2，4}，{3，3}，{4，2}，{5，1}，{6，1}，查表累加即可
     *算法复杂度为O(n^3)
     *
     */
    public static int way2(int num){
        if(num<0){
            return 0;
        }

        int dp[][] = new int[num+1][num+1];
        for(int pre=1;pre<=num;pre++){
            dp[pre][0] = 1;
        }

        //画表分析可得前两循环
        for(int pre=num;pre>0;pre--){
            for(int rest=pre;rest<=num;rest++){
                /*
                   与递归方法中的逻辑一致，求解出各种分解方案
                 *  for(int i=pre;i<=rest;i++){
                 *             ans+=process(i,rest-i);
                 *         }
                 */

                for(int i=pre;i<=rest;i++){
                    dp[pre][rest] += dp[i][rest-i];
                }
            }
        }
        return dp[1][num];
    }

    /**斜率优化dp
     *上述一般dp的过程需要枚举数的分解的所有可能，增加了一个级别的时间开开销，那么有没有时间可以优化？
     * 有的，通过画表分析多个相邻数值值的推导过程，发小下述方程：
     * dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
     * 具体可以画图证明
     *
     */
    public static int way3(int num){
        if(num<0){
            return 0;
        }

        int dp[][] = new int[num+1][num+1];
        for(int pre=1;pre<=num;pre++){
            dp[pre][0] = 1;
        }

        for (int pre = 1; pre < dp.length; pre++) {
            dp[pre][pre] = 1;
        }
        //画表分析可得前两循环
        for(int pre=num-1;pre>0;pre--){
            for(int rest=pre+1;rest<=num;rest++){
              dp[pre][rest] += dp[pre+1][rest]+dp[pre][rest-pre];
            }
        }
        return dp[1][num];
    }

    public static void main(String[] args) {
        for(int i=1;i<10;i++){
            System.out.println(way1(i));
            System.out.println(way2(i));
            System.out.println(way3(i));
        }
    }
}
