package 测试.动规真题.山洞探险;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-04-08 14:15
 * 山洞探险【蔚来汽车】难度：3
 * 牛牛和牛妹一起去爬山,途中,有一个名为《山洞探险》的游乐项目，本来已经精疲力竭的他们瞬间焕发出了活力。
 * 《山洞探险》项目介绍:
 * 首先,游玩者可以任意规定山门的颜色。
 * 由山门进入山洞,该山洞共分为n层,每一层有两扇带有颜色的门,这两扇门都可以通往下一层,但游玩者只能从这两扇门中选择一扇打开.
 * 如果游玩者当前选择打开的门与上一层打开的门的颜色相同,则不需要花费任何代价就可以打开这扇门;
 * 而如果颜色不相同，则游玩者需要花费定时间进行破译,破译完成后才能打开门进入下一层。
 * (特殊的,如果当前是第一层，则上一层打开的门的颜色由山门的颜色代替)
 * 当游玩者打开n扇门走出山洞之后,该游乐项目负责人会根据该游玩者所花费的破译时间进行颁发奖品,时间越短的奖品越好。
 * 为了方便计算,游乐项目负责人首先会将山门可能出现的m种颜色从1～m编号，
 * 而破译时间则定义为:上一层打开的门的颜色编号(若当前是第一层，则这里指山门的颜色编号)×当前这一层想要打开的门的颜色编号。
 * 牛牛和牛妹十分想要山洞后的奖品,所以他们请你事先计算一下游玩该项目最少所需要花费的破译时间是多少，以此来决定自己是否参加。
 * 输入描述:
 * 第一行输入两个正整数n, m(1<n≤10^6, 1<m ≤100)，代表山洞的层数以及颜色种数。
 * 接下去n行,每行两个正整数a, b(1≤a, b<m),代表每一层的两扇门的颜色编号。
 * 输出描述:
 * 输出仅一行一个整数，表示游玩该项目所需要花费的最少破译时间。
 * 示例1输入输出示例仅供调试，后台判题数据般不包含示例
 * 输入
 * 3 3
 * 1 3
 * 2 1
 * 2 3
 * 输出
 * 2
 * 说明
 * 将山门规定为1号颜色,第1层和那2层走颜色为1的门，都不需要花费破译时间,第3层走想色为2的门，需要花费1×2=2的破译时间。
 * 最短破译时间的走法不唯一，但是其它走法所花费的破译时间绝对不会比上述方法更优
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int door [][] = new int[n][2];
        for(int i=0;i<n;i++){
            door[i][0] = scan.nextInt();
            door[i][1] = scan.nextInt();
        }
        //方法1. 尝试以各种颜色开始,dfs
        int ans = Integer.MAX_VALUE;
        for(int i=1;i<=m;i++){
            ans = Math.min(dfs(i,0,0,door),ans);
        }
        System.out.println(ans);

        //方法2 dp
        System.out.println(dp(n,m,door));
    }

    /**
     * dfs 每一层的两道门都尝试选与不选即可
     * 超时
     * @param last
     * @param index
     * @param cost
     * @param door
     * @return
     */
    static int dfs(int last,int index,int cost,int door[][]){
        if(index==door.length){
            return cost;
        }

        int ans = 100010;

        ans = Math.min(dfs(door[index][0],index+1,cost+ (last==door[index][0]?0:door[index][0]*last),door),ans);
        ans = Math.min(dfs(door[index][1],index+1,cost+ (last==door[index][1]?0:door[index][1]*last),door),ans);

        return ans;
    }


    static int dp(int n,int m,int door[][]){
        int dp[][] = new int[n+1][2];
        int ans = Integer.MAX_VALUE;
        for(int init=1;init<=m;init++) {
            for (int i = 0; i <= n; i++) {
                Arrays.fill(dp[i], 100010);
            }
            //对第一层门进行特殊处理，如果当前门的颜色等于初始编号，那么直接设置为0，反之，取当前门的编号*初始编号的值
            for (int j = 0; j < 2; j++) {
                if (door[0][j] == init) {
                    dp[1][j] = 0;
                } else {
                    dp[1][j] = Math.min(dp[1][j], dp[1][j] + init*door[0][j]);
                }
            }

            //从第二层门开始遍历，当前门可以从上一层的0,1门到达，我们都进行尝试，得到最小值。
            for (int i = 2; i <= n; i++) {
                for (int j = 0; j < 2; j++) {//当前层的门遍历
                    for (int k = 0; k < 2; k++) {//当前层的上一层门遍历
                        if (door[i - 1][j] == door[i - 2][k]) {//如果当前门和上一层门颜色一直，直接取最小值
                            dp[i][j] = Math.min(dp[i - 1][k], dp[i][j]);
                        } else {//不一致则获得乘积，再去最小值
                            dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + door[i - 1][j] * door[i - 2][k]);
                        }
                    }
                }
            }
            ans = Math.min(ans,Math.min(dp[n][0],dp[n][1]));
        }
        return ans;
    }
}
