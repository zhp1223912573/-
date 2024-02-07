package 栈和队列;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author zhp
 * @date 2022-10-26 11:03
 * https://leetcode.cn/problems/chou-shu-lcof/submissions/
 */
public class offer_49_丑数 {
    /**
     * 多路归并问题
     */

    /**
     * 利用小根堆的排序，添加丑数，直到目标数值出现
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int num[] = new int[]{2, 3, 5};
        Set<Long> set = new HashSet();
        PriorityQueue<Long> pq = new PriorityQueue();
        pq.offer(1L);

        for (int i = 1; i <= n; i++) {
            long x = pq.poll();
            if (i == n) return (int) x;

            for (int j = 0; j < 3; j++) {
                long add = x * num[j];
                if (!set.contains(add)) {
                    set.add(add);
                    pq.add(add);
                }
            }
        }
        return -1;
    }

    /**
     * 多指针替代小根堆
     * 利用小根堆的原因是为了保证数值的有序性，但我们可以利用三个指针
     * 指向虚拟的三个数组，每轮选出2，3，5三个指针指向的最小值，
     * 将该最小值加入数组
     * // i2、i3 和 i5 分别代表三个有序序列当前使用到哪一位「已有丑数」下标（起始都指向 1
     */
    public int nthUglyNumber1(int n) {
        int nums[] = new int[n + 1];
        nums[1] = 1;
        for (int i2 = 1, i3 = 1, i5 = 1, i = 2; i <= n; i++) {
            int a = nums[i2] * 2;
            int b = nums[i3] * 3;
            int c = nums[i5] * 5;

            int min = Math.min(a, Math.min(b, c));
            if (min == a) i2++;
            if (min == b) i3++;
            if (min == c) i5++;
            nums[i] = min;
        }
        return nums[n];
    }

}
