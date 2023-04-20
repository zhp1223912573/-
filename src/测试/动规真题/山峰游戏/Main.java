package 测试.动规真题.山峰游戏;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-04-09 11:11
 * 山峰游戏【网易】难度：4
 * 小易养了N头猪，平时小易会跟猪猪们一起玩游戏。
 * 猪猪们总是很开心地参与小易的游戏，但今天，小易却想要玩一个新的游戏——“山峰游戏”。
 * 所谓的“山峰游戏”需要从N头猪中挑选出若干头猪，使得剩下的猪按照原来的顺序，身高排成“山峰”的形状。
 * 具体而言，最后形成的序列中，存在一个点为分割点，假设这个点为K，那么从序列的开头到达K，身高是严格上升的；
 * 而从K到达数量的最后一位，身高是严格下降的。小易觉得这个游戏非常有趣，但是他不知道最少需要挑选出多少头猪才能玩这个游戏。
 * 为了帮助小易，我们需要先确定“山峰游戏”的规则，然后思考如何计算最小的挑选头数。
 * 输入描述
 * 第一行输入一个正整数N。 （2≤N≤1000）
 * 第二行输入N个整数，表示每一头猪的身高
 * 输出描述
 * 输出一行正整数表示最小的挑选猪的数量。
 * 样例1
 * 输入
 * 8
 * 86 87 50 100 60 35 96 110
 * 输出
 * 3
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int pig [] = new int[n];
        for(int i=0;i<n;i++){
            pig[i]  = scan.nextInt();
        }
        System.out.println(dp(n,pig));
    }

    /**
     * 正向遍历数组求出以每一个数作为结尾时的最长递增子序列，
     * 逆向遍历数组求出每一个数作为开始时的最长递减子序列。
     * 最后遍历上述得到的数组，找到某个数为顶峰时的长山峰即可。
     * @param n
     * @param pig
     * @return
     */
    static int dp(int n,int pig[]){
        int dp1[] = new int[n];
        int dp2[] = new int[n];

        //最长递增or递减子序列都是他自己
        Arrays.fill(dp1,1);
        Arrays.fill(dp2,1);

        System.out.println("\n递增子序列");
        System.out.print(dp1[0]+" ");
        for(int i=1;i<n;i++){
            for(int j=i-1;j>=0;j--){
                if(pig[j]<pig[i])
                    dp1[i] = Math.max(dp1[i],dp1[j]+1);
            }
            System.out.print(dp1[i]+" ");
        }

        System.out.println("\n递减子序列");
        for(int i=n-2;i>=0;i--){
            for(int j=i+1;j<n;j++){
                if(pig[j]<pig[i])
                    dp2[i] = Math.max(dp2[i],dp2[j]+1);
            }
            System.out.print(dp2[i]+" ");
        }
        System.out.println(dp2[n-1]+" ");

        int len = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            len = Math.max(len,dp1[i]+dp2[i]-1);
        }

        return n-len;


    }
}
