package 测试.动规真题.商店购物;

import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-04-09 11:53
 * 商店购物【美团】难度：4
 * 题目描述
 * 现在商店里有N个物品，每个物品有原价和折扣价。
 * 小美想要购买商品。小美拥有X元，一共Y张折扣券。
 * 小美需要最大化购买商品的数量，并在所购商品数量尽量多的前提下，尽量减少花费。
 * 你的任务是帮助小美求出最优情况下的商品购买数量和花费的钱数。
 * 输入描述
 * 第一行三个整数，以空格分开，分别表示N,X,Y。
 * 接下来N行，每行两个整数，以空格分开，表示一个的原价和折扣价。
 * 1≤N≤100, 1≤X≤5000, 1≤Y≤50，每个商品原价和折扣价均介于[1,50]之间。
 * 输出描述
 * 一行，两个整数，以空格分开。第一个数字表示最多买几个商品，
 * 第二个数字表示在满足商品尽量多的前提下所花费的最少的钱数。
 * 示例1
 * 输入
 * 3 5 1
 * 4 3
 * 3 1
 * 6 5
 * 输出
 * 2 5
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int x = scan.nextInt();
        int y = scan.nextInt();
        int cost [][] = new int[n][2];
        for(int i=0;i<n;i++){
            cost[i][0]  = scan.nextInt();
            cost[i][1]  = scan.nextInt();
        }
        int[] ans = dp(n, x, y, cost);
        System.out.println(ans[0]+" "+ans[1]);
    }

    /**
     * 01背包
     * 选择使用或者不使用消费券，得到最大的购物数量，在遍历所有商品可选时，最小的开销。
     * @param n
     * @param x
     * @param y
     * @param cost
     * @return
     */
    static int[] dp(int n,int x,int y,int cost[][]){
        int dp[][][] = new int[n+1][x+1][y+1];

        for(int i=1;i<=n;i++){
            for(int j=0;j<=x;j++){
                for(int k=0;k<=y;k++){
                    //全额买或不买
                    if(j>=cost[i-1][0]){
                        dp[i][j][k] = Math.max(dp[i-1][j][k],dp[i-1][j-cost[i-1][0]][k]+1);
                    }
                    //打折买
                     if(k>0&&j>=cost[i-1][1]){
                        //减去一张消费券购买或者不买
                        dp[i][j][k] = Math.max(dp[i-1][j][k],dp[i-1][j-cost[i-1][1]][k-1]+1);
                    }
                }
            }
        }

        //综上得到最大购物数，得到最大购物数情况下的最少花费金额
        //遍历可以购买所有商品时的每种消费和消费券数量下的最大购物量，同时更新最小消费金额
        int count = -1;
        int mincost = Integer.MAX_VALUE;
        for(int j=0;j<=x;j++){
            for(int k=0;k<=y;k++){
                if(count<dp[n][j][k]){
                    count = dp[n][j][k];
                    mincost = j;
                }
                else if(count==dp[n][j][k]){
                    mincost = Math.min(mincost,j);
                }
            }
        }

        return new int[]{count,mincost};
    }
}
