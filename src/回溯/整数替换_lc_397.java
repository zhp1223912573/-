package 回溯;

import shiyan.Queue.Queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhp
 * @date 2022-10-30 14:09
 * https://leetcode.cn/problems/integer-replacement/submissions/
 */
public class 整数替换_lc_397 {
    /**
     * 递归
     *
     * @param n
     * @return
     */
    public int integerReplacement(int n) {
        if(n==1){
            return 0;
        }else if((n%2)==0){
            return 1+integerReplacement(n/2);
        }else{
            return 2+ Math.min(integerReplacement(n/2+1),integerReplacement(n/2));
        }
    }

    /**
     * 贪心
     */
    public int integerReplacement1(int n) {
        int count =0;
        while(n>1){
            if((n&1)==0){
                n/=2;
            }else{
                if((n%4)==3&&n!=3){
                    //这里执行两部操作
                    n= n/2+1;
                    count++;
                }else{
                    n=n/2;
                }
            }
            count++;
        }
        return count;
    }

    /**
     * 经典的bfs
     */
    public int integerReplacement2(int n){
        if (n == 1) return 0;
        Map<Long, Integer> map = new HashMap<>();
        Deque<Long> d = new ArrayDeque<>();
        d.addLast(n * 1L);
        map.put(n * 1L, 0);
        while (!d.isEmpty()) {
            long t = d.pollFirst();
            int step = map.get(t);
            long[] ns = t % 2 == 0 ? new long[]{t / 2} : new long[]{t + 1, t - 1};
            for (long x : ns) {
                if (x == 1) return step + 1;
                if (!map.containsKey(x)) {
                    map.put(x, step + 1);
                    d.addLast(x);
                }
            }
        }
        return -1;
    }

}
