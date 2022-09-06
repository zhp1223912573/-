package 栈和队列;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhp
 * @date 2021-11-16 13:19
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 * 后缀表达式：计算机通过特点规则及数据结构实现的四则运算
 * 中缀表达： 人计算时使用的正常表达式
 *
 */
public class lc_150 {
    public int evalRPN(String[] tokens) {

        if(tokens==null||tokens.length==0) return -1;
        //存储数字
        Deque<Integer> stack = new LinkedList();
        for(String str : tokens){
            char c=str.charAt(0);
            if(!isOp(str)){
                //将字符转化为数字
                stack.push( Integer.valueOf(str));
            }  else if (c == '+') {
                stack.push(stack.pop() + stack.pop());
            } else if (c == '-') {
                stack.push(- stack.pop() + stack.pop());
            } else if (c == '*') {
                stack.push( stack.pop() * stack.pop());
            } else {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push( num2/num1);
            }
        }
        return stack.pop();
    }

    /**
     * 判断当前字符串是否为操作符
     * @param str
     * @return
     */
    private boolean isOp(String str) {
        if(str.length()==1&&str.charAt(0)<'0'||str.charAt(0)>'9')
            return true;
        return false;
    }
}
