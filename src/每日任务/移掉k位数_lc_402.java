package 每日任务;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhp
 * @date 2022-10-27 21:05
 * https://leetcode.cn/problems/remove-k-digits/solution/
 */
public class 移掉k位数_lc_402 {
    /**暴力法
     * 移除k个数得到最小序列，从数值左侧到右侧遍历，当一个位上的数值大于其右侧的数，
     * 我们必须删除该数值，这样才能得到最小数。
     * 所以只需要进行k轮遍历，每轮找到一个上述情况的数位进行删除，如果找不到，
     * 直接删除最后一位，k轮后就可以删除k个数并得到最小数。
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        return "";
    }

    /**贪心+单调栈
     * 上述方法时间复杂度为O(kn),我们尝试将其优化到O（n)
     * 利用单调栈，依次入栈元素。
     * 即将入栈的元素若小于栈顶元素，则弹出栈顶元素，直到为空或者入栈元素小于等于栈顶元素。
     * 如果出栈的元素达到k个，就停止入栈操作，取出栈内元素并结合剩下的为入栈的元素。
     * 如果出栈元素未达到k个，就出栈k个元素即可。
     *
     * 只使用栈来实现的话，在最后得到目标字符串时还需要逆序，所以使用双端队列来完成。
     *
     */
    public String removeKdigits1(String num, int k) {
        Deque<Character> deque = new LinkedList<Character>();
        int length = num.length();
        for (int i = 0; i < length; ++i) {
            char digit = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }

        for (int i = 0; i < k; ++i) {
            deque.pollLast();
        }

        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            ret.append(digit);
        }
        return ret.length() == 0 ? "0" : ret.toString();

    }
}

