package 栈和队列;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhp
 * @date 2021-11-16 13:09
 * 用队列实现栈
 * 1.用两个栈
 * 2.一个栈
 * 本质上使用一个即可 原理相通
 */
public class QueueToStack {

    /**
     * 每压入一个数字，都将整个队列中原本的数字循环一遍，这样就实现类似栈的结构
     * 例子：
     * 压入1  队列：1    循环：1
     * 压入2  队列：1 2    循环：2 1
     * 压入3  队列：2 1 3    循环：3 2 1
     * 弹出3 队列：2 1
     * 压入3 队列：2 1 3     循环：3 2 1
     *
     */
    public static class MyStack{
        Queue<Integer> queue = new LinkedList<>() ;

        public void push(int x){
            int n=queue.size();
            queue.offer(x);
            for (int i = 0; i <n ; i++) {
                queue.offer(queue.poll()); //将除最新放入的元素全部依次放到队列尾部  实现栈
            }

        }

        public  int pop(){
            if(queue.size()<1) throw new RuntimeException();

            return queue.poll();
        }

    }
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        for(int i=0;i<5;i++){
            System.out.println(stack.pop());
        }
    }
}
