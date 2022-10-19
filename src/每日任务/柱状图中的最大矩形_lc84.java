package 每日任务;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author zhp
 * @date 2022-10-18 12:33
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/
 */
public class 柱状图中的最大矩形_lc84 {
    /**
     *经典单调栈运用题
     * 要得到最大矩形，该矩形必然是以某个height[i]为高，符合条件的底为长得到的乘积。
     * 因此问题转化为对每个height[i]找到其最长符合条件的底，也就是以i为高时，i左右两侧最近且大于当前height[i]的坐标。
     * 问题就转换成单调栈求解了。
     */
    public int largestRectangleArea(int[] heights) {
        int l[] = new int[heights.length];
        int r[] = new int[heights.length];
        //初始化
        Arrays.fill(l,-1);
        Arrays.fill(r,heights.length);
        Stack<Integer> stack = new Stack<>();
        //右边最近小于i的位置
        for(int i=0;i<heights.length;i++){
            while(!stack.isEmpty()&&heights[stack.peek()]>heights[i]){
                r[stack.pop()] = i;
            }
            stack.push(i);
        }
        stack.clear();
        //左边最近小于i的位置
        for(int i=heights.length-1;i>=0;i--){
            while(!stack.isEmpty()&&heights[stack.peek()]>heights[i]){
                l[stack.pop()] = i;
            }
            stack.push(i);
        }
        int max = Integer.MIN_VALUE;
        for(int i=0;i<heights.length;i++){
            int h = heights[i];
            int L = l[i];
            int R = r[i];
            max = Math.max(max,h*(R-L-1));
        }
        return max;
    }

    public static void main(String[] args) {
        try{
            try{
                throw new Exception("12");
            }finally {
                System.out.println("inside");
            }

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            System.out.println("outside");
        }
    }
}
