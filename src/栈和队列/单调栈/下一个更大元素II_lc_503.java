package 栈和队列.单调栈;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author zhp
 * @date 2023-03-26 22:22
 * 单调栈应用
 */
public class 下一个更大元素II_lc_503 {
    /**单调栈
     * 将整个数组读入两次，这样就可以把元素前的元素也加入栈中进行考虑
     * 但是不对第二次读入的同一个数进行压栈操作
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int res [] = new int[n];
        Arrays.fill(res,-1);
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<2*n;i++){
            int num = nums[i%n];
            while(!stack.isEmpty()&&num>nums[stack.peek()]){
                res[stack.pop()] = num;
            }
            if(i<n) stack.push(i);
        }
        return res;
    }
}
