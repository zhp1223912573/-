package 每日任务;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author zhp
 * @date 2022-10-25 14:09
 * https://leetcode.cn/problems/water-and-jug-problem/solution/shui-hu-wen-ti-by-leetcode-solution/
 */
public class 水壶问题_lc_365 {
    /**
     * 深搜
     * 使用栈模拟递归过程
     */
    public boolean canMeasureWater(int x, int y, int z) {
        Deque<int []> stack = new LinkedList<>();
        stack.push(new int[]{0,0});
        //避免相同状态重复加入
        Set<Long> seen = new HashSet<>();

        while(!stack.isEmpty()){
            //去除重复状态
            if(seen.contains(hash(stack.peek()))){
                stack.pop();
                continue;
            }

            seen.add(hash(stack.peek()));
            int state[] = stack.pop();
            if(state[0]==z||state[1]==z||state[0]+state[1]==z){
                return true;
            }
            int remainX = state[0];
            int remainY = state[1];

            //倒满x
            stack.push(new int[]{x,remainY});
            //倒满y
            stack.push(new int[]{remainX,y});
            //倒空x
            stack.push(new int[]{0,remainY});
            //倒空y
            stack.push(new int[]{remainX,0});
            //把x的水导入y，直到满或者空
            stack.push(new int[]{remainX-Math.min(y-remainY,remainX),remainY+Math.min(remainX,y-remainY)});
            //把y的水导入x，直到满或者空
            stack.push(new int[]{remainX+Math.min(x-remainX,remainY),remainY+Math.min(remainY,x-remainX)});
        }
        return false;
    }

    public long hash(int []arr){
        return arr[0]*100001+arr[1];
    }

    class Solution {
        public boolean canMeasureWater(int x, int y, int z) {
            if (x + y < z) {
                return false;
            }
            if (x == 0 || y == 0) {
                return z == 0 || x + y == z;
            }
            return z % gcd(x, y) == 0;
        }

        public int gcd(int x, int y) {
            int remainder = x % y;
            while (remainder != 0) {
                x = y;
                y = remainder;
                remainder = x % y;
            }
            return y;
        }
    }

}
