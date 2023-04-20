package 测试.动规真题.游游逛超市;

import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-04-09 10:33
 * 游游购物【携程】难度：4
 * 游游正在逛超市，有n个商品摆成一排，第i个商品的价格为 ai ,，游游对它的喜爱度为bi。所有商品的价格都是偶数。
 * 超市开展了一个活动，当游游花费原价买了一件商品时，她可以用半价买下一件右边相邻的商品(也可以用原价购买，这样该商品右边的商品就有一次享受半价的机会)。但如果游游半价购买了一件商品,那么下一件右边相邻的商品只能原价购买。
 * 换言之，如果游游想要半价买某一件商品,必须先用原价买下它相邻的左边的那个商品。
 * 游游初初始的钱为x，她想要买的商品的喜爱度总和尽可能大，但总价格不能超过x。你能帮帮她计算最大的喜爱度总和吗?
 * 输入描述
 * 第一行输入两个正整数n和x，分别代表商品的数量，以及游游初始的金额数。
 * 第二行输入n个正整数ai，分别代表每个商品的价格。
 * 第三行输入n个正整数bi，分别代表每个商品可以给游游带来的喜爱度。
 * 1<=n, x, ai <= 1000
 * 1 <= bi <= 10^9
 * 保证所有的ai都是偶数。
 * 输出描述
 * 一个整数，代表最终喜爱度之和的最大值。
 *
 * 示例1
 * 输入
 * 4 7
 * 2 2 6 2
 * 3 4 5 1
 * 输出
 * 12
 * 说明
 * 第一个使用原价卖，第二个物品使用原价买，第三个物品使用半价买，不买第四个物品，这样是最优的。
 * 请注意，如果第二个物品使用了半价，那么第三个物品则不能使用半价
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int x = scan.nextInt();
        int a[] = new int[n];
        int b[] = new int[n];
        for(int i=0;i<n;i++){
            a[i] = scan.nextInt();
        }
        for(int i=0;i<n;i++){
            b[i] = scan.nextInt();
        }

        System.out.println(dp(n,x,a,b));
    }

    /**01背包
     * dp[i][j][l]
     * 前i个物品可选，金额为j时，l等于0表示全额购买，等于1表示半折买入。
     * 保存数值为最大喜爱值。
     *
     * @param n
     * @param x
     * @param a
     * @param b
     * @return
     */
    static int dp(int n,int x,int a[],int b[]){
        int dp[][][] = new int[n+1][x+1][2];
        int ans = Integer.MIN_VALUE;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=x;j++){
                for(int l=0;l<2;l++){
                    if(l==0){
                        //剩余金额可以全额购买当前商品，尝试全额买与不买，买的情况下还可以考虑前一次是全额还是半额
                        if(j>=a[i-1]) dp[i][j][l] = Math.max(dp[i-1][j][l],Math.max(dp[i-1][j-a[i-1]][l],dp[i-1][j-a[i-1]][1-l])+b[i-1]);
                    }else{
                        //第一件不能半价买
                        if(i==1) continue;
                        //半折买入，先前只能是全额买入
                        if(j>=a[i-1]/2) dp[i][j][l] = Math.max(dp[i-1][j][l],dp[i-1][j-a[i-1]/2][1-l]+b[i-1]);
                    }
                    ans = Math.max(ans,dp[i][j][l]);
                }
            }
        }

        return ans;
    }
}
