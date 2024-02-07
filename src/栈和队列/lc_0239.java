package 栈和队列;

import java.util.LinkedList;

/**
 * @author zhp
 * @date 2021-11-17 21:51
 * https://leetcode-cn.com/problems/sliding-window-maximum/solution/shuang-xiang-dui-lie-jie-jue-hua-dong-chuang-kou-2/
 * 滑动数组求最大值
 */

/**
 * 使用双端队列可以保证队列中的队首始终为最大
 */
public class lc_0239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 2) return nums;
        //使用双向队列 若当前数大于队列最后一个则 将则最后一个数剔除
        //保证队列队首始终为当前最大值
        LinkedList<Integer> deque = new LinkedList<>();
        //结果数组
        int result[] = new int[nums.length - k + 1];
        //存储在deque中的是数字在nums的下标 方便寻找数值
        for (int i = 0; i < nums.length; i++) {
            //deque不为空直且最后一个数小于要填入的数 直接剔除最后一个数 保证队首最大
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            //加入当前数 （加入的是坐标值）
            deque.add(i);
            //判断当前队首是否依旧在滑动窗口内
            if (deque.peek() <= i - k) {
                deque.poll();
            }

            //只要遍历的数多于k个 就可以向result填充数据
            if (i >= k) {
                result[i + 1 - k] = nums[deque.peek()];
            }
        }


        return result;

    }
}
