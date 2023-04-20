package 测试.动规真题.小美打音游;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-04-08 13:22
 * 音游【美团笔试】难度：3
 * 小美现在打音游。这个音游的玩法是这样的：
 * —— 共有n个房间。小美初始拥有一个指针，指在一号房间。
 * —— 游戏共持续m秒，每秒会有一个房间产生炸弹，小美的指针不能在这个房间中。
 * —— 每秒结束的瞬间，小美可以使用一次魔法，把指针切换到另一个房间中，该过程会消耗一个能量。
 * 你的任务是计算小美无伤通过音游所需要消耗的最小能量。
 * 保证第一秒的炸弹不发生在一号房间中。
 * n ＜ = 10, 1 <= m <= 10000
 * 输入描述：
 * 第一行两个正整数 n 和 m，表示房间有 n 个，游戏持续 m 秒。
 * 第二行 m 个正整数，每个正整数在1~n 的范围内，第 i 个正整数表示第 i 秒时炸弹在哪个房间生成。
 * 数字间有空格隔开
 * 样例输入
 * 2 4
 * 2 1 1 2
 * 样例输出
 * 2
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int nums[] = new int[m];
        for(int i=0;i<m;i++){
            nums[i] = scan.nextInt();
        }
//        System.out.println( dfs(0,nums,1,n,0));
        dp(n,m,nums);
    }
    static int dfs(int time,int nums[],int cur,int n,int count){
        if(time==nums.length){
            return count;
        }
        int ans = 10010;

        if(nums[time]==cur){//当前位置此刻要爆炸，需要转换位置，转换的位置不能是当前位置
            for(int i=1;i<=n;i++){
                if(cur==i) continue;
                ans = Math.min(dfs(time+1,nums,i,n,count+1),ans);
            }
        } else{//当前位置不会爆炸，那么无需转换位置
                ans = Math.min(dfs(time+1,nums,cur,n,count),ans);
        }

        return ans;
    }


    static void dp(int n,int m,int nums[]){
        int dp[][] = new int[m+1][n+1];
        for(int i =0;i<=m;i++){
            Arrays.fill(dp[i],100010);
        }
        dp[1][1] = 0;
        for(int i=2;i<=m;i++){
            for(int j=1;j<=n;j++){
                //如果此刻炸弹在j房间爆炸，不应保存在j房间
                if(nums[i-1]==j) continue;
                for(int k=1;k<=n;k++){
                    if(k==j){//此刻就在原位置，那么不进行跳转
                        dp[i][j] = Math.min(dp[i-1][k], dp[i][j]);
                    }else{
                        dp[i][j] = Math.min(dp[i-1][k]+1,dp[i][j]);
                    }
                }
            }
        }
        int ans = 100010;
        for(int i=1;i<=n;i++){
            ans = Math.min(dp[m][i], ans);
        }
        System.out.println(ans);
    }
}
