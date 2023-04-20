package 测试.动规真题.便利蜂商品购买;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-04-09 14:54
 * 便利蜂商品购买问题【便利蜂】难度：4
 * 时间限制：3000MS
 * 内存限制：589824KB
 * 题目描述
 * 便利蜂的商店售卖着琳琅满目的商品，吸引着小蜂来到便利蜂商店一些商品解解馋。小蜂特别喜欢便利蜂的蜂质选产品、不眠海饮品和关东煮。
 * 小蜂想购买一款蜂质选产品、一杯不眠海饮品、一份关东煮，并且总花费不超过x元，小蜂想知道自己有多少种购买方案。
 *
 * 输入描述
 * 第一行输入n个数字，每两个数字用空格隔开，每个数字p代表蜂质选产品的价格。
 * 第二行输入m个数字，每两个数字用空格隔开，每个数字p代表不眠海饮品的价格。
 * 第三行输入k个数字，每两个数字用空格隔开，每个数字p代表关东煮的价格。
 * 第四行输入一个数字，计为x，表示小蜂口袋里的钱。
 * ● 数据范围
 * ● 1<=n,m,k<=10000
 * ● 1<=p<=10000
 * ● 1<=x<=3*10000
 * 输出描述
 * 输出一个数字，表示小蜂购买方案数
 * ● 注意：答案需要以 1e9+7(1000000007)为底进行取模，如：计算结果为1000000008，就输出1；如果没有一种方案可以同时购买3种商品，则输出-1。
 *
 * 样例输入
 * 1 2
 * 3 4
 * 5 6
 * 10
 * 样例输出
 * 4
 * 提示
 * 小蜂一共有4种购买方案
 * 方案1：1(蜂质选) + 3(不眠海) + 5(关东煮) = 9 < 10
 * 方案2：1(蜂质选) + 3(不眠海) + 6(关东煮) = 10 <= 10
 * 方案3：1(蜂质选) + 4(不眠海) + 5(关东煮) = 10 <= 10
 * 方案4：2(蜂质选) + 3(不眠海) + 5(关东煮) = 10 <= 10
 */
public class Main {
    /**
     *
     * @param args
     */
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        int[] p1 = getProduct();
        int[] p2 = getProduct();
        int[] p3= getProduct();
        int money = scan.nextInt();
        Arrays.sort(p1);
        Arrays.sort(p2);
        Arrays.sort(p3);

        System.out.println(dfs(1,money,p1,p2,p3));
        System.out.println(dp(money,p1,p2,p3));


    }


    static int dfs(int index,int money,int p1[],int p2[],int p3[]){
        if(index==4){
            return 1;
        }

        int product[] =  index==1?p1:(index==2?p2:p3);
        int ans = 0;
        for(int i=0;i<product.length;i++){
            if(money<product[i]) return ans;
            else ans += dfs(index+1,money-product[i],p1,p2,p3);
        }
        return ans;

    }

    /**抄的！
     * dp[i][j]
     * 可以选择第i组产品之前的所有，在剩下j元时可以购买的方案总数。
     * @param money
     * @param p1
     * @param p2
     * @param p3
     * @return
     */
    private static int dp(int money, int[] p1, int[] p2, int[] p3) {
        int dp[][] = new int[4][money+1];
        Arrays.fill(dp[0],1);
        for(int i=1;i<=3;i++){
            int product[] = i==1?p1:(i==2?p2:p3);
            //排序，后续某个商品价格大于当前持有金额直接退出
            Arrays.sort(product);
            for(int j=0;j<=money;j++){
                for(int k=0;k<product.length;k++){
                    if(j>=product[k]){//选择当前产品
                        dp[i][j] += dp[i-1][j-product[k]];
                    }else{
                        break;
                    }
                }
            }
        }
        return dp[3][money];
    }

    static int[] getProduct(){

        String line1 = scan.nextLine();
        String[] s = line1.split(" ");
        int p1 [] = new int[s.length];
        for(int i=0;i<s.length;i++){
            p1[i] = Integer.parseInt(s[i]);
        }
        return p1;
    }
}
