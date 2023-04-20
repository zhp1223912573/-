package 数学;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhp
 * @date 2022-07-18 14:37
 * https://leetcode.cn/problems/happy-number
 */
public class 快乐数_lc_202 {

    public int getNext(int n){
        int total = 0;
        while(n>0){
            int num = n%10;
            total += num*num;
            n/= 10;
        }
        return total;
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while(n!=1 && !set.contains(n)){
            set.add(n);
            n = getNext(n);
        }
        return n==1;
    }

    /**
     * 可以将该数值理解为一个隐式链表，通过getNext函数得到下一节点值
     * 使用快慢指针，如果快指针会遇到慢指针，说明链表里包含环，
     * 反之，如果快指针等于1，说明不存在环。
     */
    public boolean isHappy1(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }
    /**
     * 方法三：数学
     * 前两种方法是你在面试中应该想到的。第三种方法不是你在面试中会写的，而是针对对数学好奇的人，因为它很有趣。
     *
     * 下一个值可能比自己大的最大数字是什么？根据我们之前的分析，我们知道它必须低于 243。因此，我们知道任何循环都必须包含小于 243 的数字，用这么小的数字，编写一个能找到所有周期的强力程序并不困难。
     *
     * 如果这样做，您会发现只有一个循环：4 \rightarrow 16 \rightarrow 37 \rightarrow 58 \rightarrow 89 \rightarrow 145 \rightarrow 42 \rightarrow 20 \rightarrow 44→16→37→58→89→145→42→20→4。所有其他数字都在进入这个循环的链上，或者在进入 11 的链上。
     *
     * 因此，我们可以硬编码一个包含这些数字的散列集，如果我们达到其中一个数字，那么我们就知道在循环中。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/happy-number/solution/kuai-le-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
