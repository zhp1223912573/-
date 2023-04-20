package 测试.百度面试.和为偶数的子序列;

import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-03-27 22:19
 * 牛牛有一个长度为n的数组a，牛牛想知道在所有的长度为k的子序列中，子序列和为偶数的序列个数有多少个呢。长度为k的子序列的含义为存在一组数1≤ i1 < i2 <…< ik ≤n，所构成的数组ai1, ai2, ..., aik
 * 输入描述:
 * 第一行为t，表示有t组数据。
 * 接下来有2×t行,其中第一行为n,k，表示数组长度和子序列长度。第二行有n个元素，表示数组元素ai。
 * 1≤t≤100,1≤n≤ 10^3,1≤k≤n,1≤ai≤10.
 * 输出描述:
 * 输出为t行,表示答案,其可能很大请对10^9+7取模。
 * 示例1输入输出示例仅供调试,后台判题数据一般不包含示例
 * 输入
 * 3
 * 5 2
 * 1 2 3 4 5
 * 3 1
 * 1 2 3
 * 4 3
 * 1 4 2 2
 * 输出
 * 4
 * 1
 * 1
 */
public class Main {
    public static void main(String[] args) {


    }

    /**
     * dp
     */
    public void process(){
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T-- > 0) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = scanner.nextInt();
            }
            int[][][] dp = new int[n + 1][k + 1][2];
            dp[0][0][0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= k; j++) {
                    for (int l = 0; l < 2; l++) {
                        if (j == 0) {
                            dp[i][j][l] = dp[i - 1][j][l];
                        } else {
                            dp[i][j][l] = dp[i - 1][j][l] + dp[i - 1][j - 1][(l + nums[i - 1]) % 2];
                        }
                    }
                }
            }
            System.out.println(dp[n][k][0]);
        }
    }

    /**
     * 1.先统计奇偶数值个数，
     * 2.分类讨论
            全为偶数
     *      全为奇数 只有当k为偶数时才存在改可能
     *      有奇有偶 -保证奇数个数为偶数个，剩下的全为偶数
     *
     */
    public void process1(){

    }
}

