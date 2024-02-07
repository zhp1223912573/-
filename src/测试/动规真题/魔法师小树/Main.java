package 测试.动规真题.魔法师小树;

import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-04-08 22:44
 * 魔法师【百度】难度：4
 * 的问限制:3000MS
 * 内存限制:589824KB
 * 题目描述:
 * 魔法师小树有n个魔法元素，他把它们排成一行，从左到右第i个魔法元素的能量值是一个非零整
 * 数ai。小树每次施展魔法的方式是挑选一段连续非空的魔法元素,将它们的能量值乘起来，得到
 * 值就是这次魔法的总能量。如果总能量大于零即为白风法,否则为黑魔法。
 * 现在小树想知道施展—个白魔法或黑魔法的方案数分别有多少。两个方案不同是指挑选的连续区间不同。
 *
 * 输入描述
 * 第一行有一个整数n(1<= n <= 2 * 10^5),表示魔法元素的个数。
 * 第二行有n个整数a1,a2,....,an (-10^9 <= ai <= 10^9; ai != 0)代表魔法元素的能量值。
 *
 * 输出描述
 * 输出两个整数，分别表示施展一个黑魔法和施展一个白魔法的方案数
 *
 * 测试样例
 * 输入
 * 5
 * 5 -3 3 -1 1
 * 输出
 * 8 7
 */
public class Main {
    /**
     * 这题也做过。。。
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int nums[] = new int[n];
        for (int j = 0; j < n; j++) {
            nums[j] = scan.nextInt();
        }

        int dp1[] = new int[n];
        int dp2[] = new int[n];

        dp1[0] = nums[0]>0?1:0;//以第i个元素结尾，乘积为正的子串数量
        dp2[0] = nums[0]<0?1:0;//以第i个元素结尾，乘积为负的子串数量

        int w = dp1[0];
        int b = dp2[0];
        for(int i=1;i<n;i++){
            if(nums[i]>0){
                dp1[i] = dp1[i-1]+1;
                dp2[i] = dp2[i-1];
            }else{
                dp1[i] = dp2[i-1];
                dp2[i] = dp1[i-1]+1;
            }
            w+=dp1[i];
            b+=dp2[i];
        }
        System.out.println(w+" "+b);
    }

}
