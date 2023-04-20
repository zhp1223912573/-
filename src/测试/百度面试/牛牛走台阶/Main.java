package 测试.百度面试.牛牛走台阶;

import com.oracle.net.Sdp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-03-28 10:06
 * 牛牛回到家要走恰好n个台阶。由于牛牛步伐不太大，故单步只能跨最多m个台阶，最少跨一个台阶。牛牛有一个奇怪的习惯，他要求每步和之前两步走的台阶数目不能相同。牛牛想知道有多少种不同的走法，答案对109 + 7取模。
 * 输入描述
 * 1
 * 2
 * 3
 * 4
 * 
 * 一行输入两个整数n,m,表示台阶数目，单步跨越的最多台阶数目。
 * 对于30%的数据有m≤n≤5。
 * 对于60%的数据有n≤300。
 * 对于100%的数据有1≤n≤100000,2< m≤7。
 * 
 * 输出描述
 * 1
 * 一行一个整数，表示答案。
 * 
 * 示例

 * 1
 * 2
 * 3
 * 4
 * 5
 * 6
 * 7
 * 输入
 * 7 3
 * 输出
 * 2
 * 说明
 * 合法的走法仅有: (1,2,3,1), (1,3,2,1)。
 * 比如(1,2,1,3)在第三步非法。
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        System.out.println( process1(n,m));
    }

    /**
     *
     * @param n
     * @param m
     * @return
     */
    private static int process(int n, int m) {
        return dfs(0,0,0,n,m);
    }

    /**
     * 深搜（超时）
     * 记录上两次的步骤数量，尝试1-m的步骤进行跨越，如果与前两次冲突，则尝试别的数值。
     * @param last
     * @param lastlast
     * @param curstep
     * @param n
     * @param m
     * @return
     */
    private static int dfs(int last, int lastlast, int curstep, int n, int m) {
        if(curstep>n) return 0;
        if(curstep==n) return 1;

        int count = 0;
        for(int i=1;i<=m;i++){
            if(i==last||i==lastlast){
                continue;
            }
            count+= dfs(i,last,curstep+i,n,m);
        }
        return count;

    }


    private static int process1(int n, int m) {

        int dp[][][] = new int[n+1][m+1][m+1];
        //填充为-1
        for(int i=0;i<=n;i++){
            for(int j=0;j<=m;j++){
                Arrays.fill(dp[i][j],-1);
            }

        }
        return  memoryDfs(0,0,0,n,m,dp);

    }

    /**
     * 记忆深搜索
     * dp数组保存状态，避免重复计算
     * dp[i][j][k] i表示所在阶梯，j上一步的跨越台阶数，k表示上上步的台阶数
     * @param last
     * @param lastlast
     * @param curstep
     * @param n
     * @param m
     * @param dp
     * @return
     */
    private static int memoryDfs(int last,int lastlast,int curstep,int n,int m,int dp[][][]){
        if(curstep>n) return 0;
        if(curstep==n) return 1;
        if(dp[curstep][last][lastlast]!=-1) return dp[curstep][last][lastlast];

        int count = 0;
        for(int i=1;i<=m;i++){
            if(i==last||i==lastlast){
                continue;
            }
            count+= dfs(i,last,curstep+i,n,m);
        }
        return dp[curstep][last][lastlast]=count;
    }

    /**
     * 标准dp
     */
    public int solution(int n, int m) {
        int[][][] dp = new int[m + 1][m + 1][n + 1];
        for (int step1 = 1; step1 <= m; step1++) {
            for (int step2 = 1; step2 <= m; step2++) {
                if (step1 != step2 && (step1 + step2) <= n)
                    dp[step1][step2][step1 + step2] = 1;
            }
        }
        int count = 0;
        for (int steps = 1; steps <= n; steps++) {
            for (int last = 1; last <= m; last++) {
                for (int bflast = 1; bflast <= m; bflast++) {
                    if (last == bflast)
                        continue;
                    for (int lastbflast = 1; lastbflast <= m; lastbflast++) {
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
