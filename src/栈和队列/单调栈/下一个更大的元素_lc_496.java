package 栈和队列.单调栈;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author zhp
 * @date 2023-04-15 14:40
 * https://leetcode.cn/problems/next-greater-element-i/submissions/
 */
public class 下一个更大的元素_lc_496 {
    /**
     * 单调栈模板
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<nums2.length;i++){
            while(!stack.isEmpty()&&nums2[stack.peek()]<nums2[i]){
                int top = stack.pop();
                map.put(nums2[top],nums2[i]);
            }
            stack.push(i);
        }

        int res [] = new int[nums1.length];
        for(int i=0;i<nums1.length;i++){
            res[i] = map.getOrDefault(nums1[i],-1);
        }
        return res;
    }
}
