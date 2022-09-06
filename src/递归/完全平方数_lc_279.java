package 递归;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhp
 * @date 2022-07-19 16:13
 * https://leetcode.cn/problems/perfect-squares/
 */
public class 完全平方数_lc_279 {
    /**
     * bfs
     * 分析7的推导过程，为了使一个数尽可能的有较少的平方数构成，则构成他的平方数要尽可能的大
     *          7
     *       6     3
     *    5 2     2  1
     *  1 4  1          1
     *可能等效的理解为一个层序遍历
     */
    public int numSquares(int n) {
        Queue<Integer> queue=new LinkedList<>();
        HashSet<Integer> visited=new HashSet<>();
        queue.offer(n);
        visited.add(n);
        int level=0;

        while(!queue.isEmpty()){
            level++;
            int len=queue.size();

            for(int i=0;i<len;i++){
                int cur=queue.poll();

                for(int j=1;j*j<=cur;j++){
                    int tmp=cur-j*j;
                    if(tmp==0)
                        return level;
                    if(!visited.contains(tmp))
                        queue.offer(tmp);
                    visited.add(tmp);
                }
            }
        }
        return level;

    }

    /**
     * dp
     * 定义：
     *      设定dp[i]为数字i的最少平方和数值构成数量。
     * 初始情况:
     *      所有i*i的数的最少平方和都为1
     * 可能性分析:
     *      非i*i的数值，构成其最少平方和的数量等于前前面所有可能性之和的最小值。
     *      dp[i] = Math.min(dp[i-j*j],min)
     *
     * 时间复杂度:O(n*sqrt(n))
     * 空间复杂度：O(n)
     */
    public int numSquares1(int n) {
        int dp[] = new int[n+1] ;
        for(int i=1;i<=n;i++){
            int min = Integer.MAX_VALUE;
            for(int j=1;j*j<=i;j++){
                min = Math.min(min,dp[i-j*j]);
            }
            dp[i] = min+1;
        }
        return dp[n];
    }

    /**
     * 数学
     * 四平方和
     * https://leetcode.cn/problems/perfect-squares/solution/wan-quan-ping-fang-shu-by-leetcode-solut-t99c/
     */


}
