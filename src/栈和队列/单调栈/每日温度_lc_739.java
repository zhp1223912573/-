package 栈和队列.单调栈;


import java.util.Stack;

/**
 * @author zhp
 * @date 2022-07-19 21:35
 * https://leetcode.cn/problems/daily-temperatures/
 */

public class 每日温度_lc_739 {
    /**
     * 单调栈
     * 典型的单调栈问题，维护一个单调递减的栈，要压入的元素大于当前栈顶元素，就弹出栈顶元素，直到栈顶元素小于即将压入的元素
     * 弹出的元素的下一个最近更大值就是压入的栈顶元素
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack   = new Stack<>();
        int m =    temperatures.length;
        int ans [] = new int[m];
        stack.push(0);
        for(int i=1;i<m;i++){
            //当前栈非空且栈顶元素小于即将压入的元素
            while(!stack.isEmpty()&&temperatures[i]>temperatures[stack.peek()]){
                Integer pop = stack.pop();
                ans[pop] = i-pop;//弹出的元素的下一个比他大的温度，就是当前温度
            }
            //压入当前元素
            stack.push(i);
        }

        //如果还有元素存在，说明剩下的元素有都不存在比他大的元素,应该使用0来替代，所以不做任何操作
        return ans;
    }
}
