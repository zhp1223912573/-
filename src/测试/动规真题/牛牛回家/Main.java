package 测试.动规真题.牛牛回家;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-04-08 22:12
 * 牛牛走台阶【百度】难度：4
 *
 * 牛牛回到家要走恰好n个台阶。
 * 由于牛牛步伐不太大，故单步只能跨最多m个台阶，最少跨一个台阶。
 * 牛牛有一个奇怪的习惯，他要求每步和之前两步走的台阶数目不能相同。
 * 牛牛想知道有多少种不同的走法，答案对109 + 7取模
 *
 * 一行输入两个整数n,m,表示台阶数目，单步跨越的最多台阶数目。
 * 对于30%的数据有m≤n≤5。
 * 对于60%的数据有n≤300。
 * 对于100%的数据有1≤n≤100000,2< m≤7。
 *
 * 输入
 * 7 3
 * 输出
 * 2
 * 说明
 * 合法的走法仅有: (1,2,3,1), (1,3,2,1)。
 * 比如(1,2,1,3)在第三步非法。
 */
public class Main {
    static int n;
    static int m;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
         n = scan.nextInt();
         m = scan.nextInt();
         //方法1
        System.out.println(dfs(0,0,0));

        //方法2
        dp = new int[n+1][m+1][m+1];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=m;j++){
                Arrays.fill(dp[i][j],-1);
            }
        }
        System.out.println( memorydfs(0,0,0));
    }

    /**dfs
     * 超时
     * @param prepre 上上次的步伐
     * @param pre 上次的步伐
     * @param cur 现在所在位置
     * @return
     */
    static int dfs(int prepre,int pre,int cur){
        if(cur>=n){
            if(cur==n){
                return 1;
            }
            return 0;
        }
        int ans = 0;
        for(int i=1;i<=m;i++){
            if(pre==i||prepre==i) continue;
            ans += dfs(pre,i,cur+i);
        }
        return ans;
    }

    /**
     * 记搜
     */
    static int dp[][][];
    static int memorydfs(int prepre,int pre,int cur){
        if(cur>=n){
            if(cur==n){
                return 1;
            }
            return 0;
        }

        if(dp[cur][pre][prepre ]!=-1) return dp[cur][pre][pre];
        int ans = 0;
        for(int i=1;i<=m;i++){
            if(pre==i||prepre==i) continue;
            ans += memorydfs(pre,i,cur+i);
        }
        return dp[cur][pre][pre] = ans;
    }

    /**
     * dp
     * 基于记搜编写dp数组即可
     */
    static int dp(int n,int m){
        int[][][] dp = new int[m + 1][m + 1][n + 1];
        //先完成前两步的初始化
        for (int step1 = 1; step1 <= m; step1++) {
            for (int step2 = 1; step2 <= m; step2++) {
                if (step1 != step2 && (step1 + step2) <= n)
                    dp[step1][step2][step1 + step2] = 1;
            }
        }

        int count = 0;
        //开始走第三步
        for (int steps = 1; steps <= n; steps++) {//当前所在台阶
            for (int last = 1; last <= m; last++) {//上一步
                for (int bflast = 1; bflast <= m; bflast++) {//上上步
                    if (last == bflast)
                        continue;
                    for (int lastbflast = 1; lastbflast <= m; lastbflast++) {//上上上步
                        if (steps < last || last == lastbflast || bflast == lastbflast)
                            continue;
                        dp[last][bflast][steps] += dp[bflast][lastbflast][steps - last];
                    }
                }
            }
        }
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= m; j++) {
                count += dp[i][j][n];
            }
        }
        return count;
    }

}
