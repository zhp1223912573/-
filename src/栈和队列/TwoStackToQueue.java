package 栈和队列;

import java.util.Stack;

/**
 * @author zhp
 * @date 2021-11-16 10:44
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/solution/yong-zhan-shi-xian-dui-lie-by-leetcode/
 * 两个栈实现队列
 */
public class TwoStackToQueue {

    private int front;//记录队首元素 peek时输出
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();


    /**
     * s1存储栈元素 s2作为辅助栈
     * 每push一个元素前 先将s1中元素pop到s2直到 s1为空
     * 再将s2元素pop会s1中 实现翻转
     * front用来记录队首元素 方便弹出
     * @param x
     */
//    public void push(int x){
//        if(s1.empty()) front=x;
//        while(!s1.empty()){
//            s2.push(s1.pop());
//        }
//        s2.push(x);
//        while(!s2.empty()){
//            s1.push(s2.pop());
//        }
//     }
//     public void pop(){
//        s1.pop();
//        //获取新队首元素
//        if(!s1.empty()){
//            front=s1.peek();
//        }
//     }
//     public boolean empty(){
//        return s1.empty();
//     }
//     public int peek(){
//        return front;
//     }

    /**
     * s1依旧存储栈元素 但每当pop时将s1中全部pop到s2中 从而得到一个队列
     */
    public void push1(int x) {
        if (s1.empty()) front = x;
        s1.push(x);
    }

    public void pop1() {
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        s2.pop();
    }

    public boolean empty1() {
        return s1.isEmpty() && s2.isEmpty();
    }

    public int peek1() {
        if (!s2.isEmpty()) {
            return s2.peek();
        }
        return front;
    }


}
