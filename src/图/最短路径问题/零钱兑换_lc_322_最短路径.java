package 图.最短路径问题;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhp
 * @date 2023-04-20 10:29
 * https://leetcode.cn/problems/coin-change/
 */
public class 零钱兑换_lc_322_最短路径 {
    /**
     * 经典的dfs，多重背包问题，但是转换思路，既可以尝试使用最短路径算法来解答。
     * 将0作为初始金币数，每次可以尝试拿coins内的金币，将持有金币的数量视为图的点，
     * 每拿一个货币相当于走出一步，基于bfs得到最短步长到达指定位置的路长。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        Deque<int[]> deque = new LinkedList<>();
        Arrays.sort(coins);
        boolean visit[] = new boolean[amount + 1];
        //起步位置到状态加入 num[0]表示持有货币总额 num[1]表示持有货币总数
        deque.add(new int[]{0, 0});
        while (!deque.isEmpty()) {
            int[] node = deque.pollFirst();
            int have = node[0];
            int step = node[1];
            //到达指定货币总额，必然是最少货币数，直接返回
            if (have == amount) return step;
            for (int coin : coins) {
                if (coin > amount - have) break;
                if (visit[coin + have]) continue;
                deque.add(new int[]{have + coin, step + 1});
                visit[have + coin] = true;
            }
        }
        return -1;
    }
}
