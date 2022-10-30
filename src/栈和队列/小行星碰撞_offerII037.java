package 栈和队列;


import java.util.Stack;

/**
 * @author zhp
 * @date 2022-10-18 12:02
 * https://leetcode.cn/problems/XagZNi/
 */
public class 小行星碰撞_offerII037 {

    /**
     * 利用栈特性完成，基本上就是模拟题，设置好各种遍历条件即可
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack();
        for(int i=0;i<asteroids.length;i++){
            //当前小行星
            int aster = asteroids[i];
            //判断当前小行星是否存活
            boolean isalive = true;
            while(isalive&&!stack.isEmpty()&&stack.peek()>0&&aster<0){
                //如果当前小行星小于反方向的行星，当前行星不可能存活
                isalive = stack.peek()<(-aster);

                if(stack.peek()<=(-aster)){
                    stack.pop();
                }
            }
            if(isalive){
                stack.push(aster);
            }
        }
        int res[] = new int[stack.size()];
        for(int i=stack.size()-1;i>=0;i--){
            res[i] = stack.pop();
        }

        return res;
    }

}
