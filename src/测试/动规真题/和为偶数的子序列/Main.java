package 测试.动规真题.和为偶数的子序列;

import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-04-08 21:38
 * 和为偶数的子序列【百度】难度：3
 * 牛牛有一个长度为n的数组a，牛牛想知道在所有的长度为k的子序列中，子序列和为偶数的序列个数有多少个呢。
 * 长度为k的子序列的含义为存在一组数1≤ i1 < i2 <…< ik ≤n，所构成的数组ai1, ai2, ..., aik
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
    /**
     * 这道题之前做过
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for(int i=0;i<t;i++){
            int n = scan.nextInt();
            int k = scan.nextInt();
            int nums[] = new int[n];
            for(int j=0;j<n;j++){
                nums[j] = scan.nextInt();
            }
            int dp[][][] = new int[n+1][k+1][2];
            dp[0][0][0] = 1;
            for(int x=1;x<=n;x++){
                for(int y=0;y<=k;y++){
                    for(int z=0;z<2;z++){
                        if(y==0){
                            dp[x][y][z] = dp[x-1][y][z];
                        }else{
                            //选与不选当前位置到数值
                            dp[x][y][z] = dp[x-1][y][z]+dp[x-1][y-1][(z+nums[x-1])%2];
                        }
                    }
                }
            }
            System.out.println(dp[n][k][0]);
        }

    }
}
