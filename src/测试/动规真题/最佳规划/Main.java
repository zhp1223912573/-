package 测试.动规真题.最佳规划;

import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-04-09 14:17
 * 最佳规划【美团】难度：3
 * 时间限制:3000MS
 * 内存限制:589824KB
 * 题目描述:
 * 小团在一个n *m的网格地图上探索。网格地图上第i行第j列的格子用坐标(i,j)简记。
 * 初始时,小团的位置在地图的左上角。即坐标(1,1),地图上的每一个格子上都有一定的金币，
 * 特别地,小团位于的初始位置(1,1)上的金币为0。
 * 小团在进行探索移动时,可以选择向右移动一格(即从(x,y)到达(x,y+1))或向下移动一格(即从(x,y)到达(x+1,y))。
 * 地图上的每个格子都有一个颜色，红色或蓝色。
 * 如果小团一次移动前后的两个格子颜色不同，那么他需要支付k个金币才能够完成这一次移动;
 * 如果移动前后的两个格子颜色相同,则不需要支付金币。
 * 小团可以在任意格子选择结束探索。
 * 现在给你网格地图上每个格子的颜色与金币数量,假设小团初始时的金币数量为0，
 * 请你帮助小团计算出最优规划，使他能获得最多的金币,输出能获得的最多金币数量即可。
 * 注意:要求保证小团任意时刻金币数量不小于零。
 * 输入描述
 * 第一行是三个用空格隔开的整数n,m和k，表示网格地图的行数为n，列数为m，
 * 在不同颜色的两个格子间移动需要支付k个金币。
 * 接下来n行，每行是一个长度为m的字符串，字符串仅包含字符'R'或'B'。
 * 第i行字符串的第j个字符表示地图上第i行第j列的格子颜色。
 * 如果字符为'R'，则表示格子颜色为红色，为'B'表示格子颜色为蓝色。
 * 接下来是一个n行m列的非负整数矩阵，第i行第j列的数字表示地图上第i行第j列的格子上的金币数量。
 * 保证所有数据中数字大小都是介于[0,10]的整数。
 * 1<=n, m <= 200, 1 <= k <= 5。
 * 输出描述
 * 一个正整数，表示能获得最多金币的数量。
 *
 * 示例1
 * 输入
 * 3 3 2
 * BBB
 * RBR
 * RBR
 * 0 2 3
 * 2 4 1
 * 3 2 2
 * 输出
 * 8
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int k = scan.nextInt();
        char color[][] = new char[n][m];
        int coins[][] = new int[n][m];
        for(int i=0;i<n;i++){
            String next = scan.next();
            color[i] = next.toCharArray();
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                coins[i][j] = scan.nextInt();
            }
        }

        System.out.println(dp(n,m,k,color,coins));
    }

    static int dp(int n,int m,int k,char color[][],int coins[][]){
        int ans = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0&&j==0){
                    continue;
                }
                if(i==0){
                    coins[0][j] += (coins[0][j-1] + (color[0][j-1]==color[0][j]?0:-k));
                }
                else if(j==0){
                    coins[i][j] += (coins[i-1][0] + (color[i-1][0]==color[i][0]?0:-k));
                }
                else{
                    coins[i][j] += Math.max(coins[i-1][j] + (color[i-1][j]==color[i][j]?0:-k) ,
                            coins[i][j-1] + (color[i][j-1]==color[i][j]?0:-k)  );
                }
                ans = Math.max(ans,coins[i][j]);
            }
        }
        return ans;
    }
}
