package 栈和队列;

import java.util.PriorityQueue;

/**
 * @author zhp
 * @date 2022-10-26 12:5
 * https://leetcode.cn/problems/super-ugly-number/
 */
public class 超级丑数_lc_313 {
    /**
     * 超级丑数是丑数的扩展，是在除了2，3，5外，多加了数值时的丑数集合求解。
     * 题解：
     * https://leetcode.cn/problems/super-ugly-number/solution/gong-shui-san-xie-yi-ti-shuang-jie-you-x-jyow/
     * 我们可以构造一个存储三元组的小根堆，三元组信息为 (val, i, idx)(val,i,idx)：
     *
     * val ：为当前列表指针指向具体值；
     * i ：代表这是由 primes[i]primes[i] 构造出来的有序序列；
     * idx：代表丑数下标，存在关系 val = ans[idx] * primes[i]
     *
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        int ans [] = new int[n];
        ans[0] =1;
        for(int i=0;i<primes.length;i++){
            pq.add(new int[]{primes[i],i,0});
        }

        for(int j=1;j<n;){
            int[] poll = pq.poll();
            int val = poll[0];
            int i = poll[1];
            int idx = poll[2];
            if(ans[j-1]!=val) ans[j++] = val;
            pq.add(new int[]{primes[i]*ans[idx+1],i,idx+1});
        }
        return ans[n-1];
    }
}
