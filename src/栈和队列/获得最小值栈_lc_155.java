package 栈和队列;


import java.util.Stack;

/**
 * @author zhp
 * @date 2022-07-04 14:36
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素
 * 的操作。
 * 要求：1.pop、push、getMin操作的时间复杂度都是O(1)；2.设计的栈类型可以
 * 使用现成的栈结构
 */
public class 获得最小值栈_lc_155 {
    /**
     * 设置两个栈，一个正常装入数据，一个保存每压入一个数时，当前所有数的最小值
     *
     */
    public static class minStack{
        public Stack<Integer> dataStack;//一般数据栈
        public Stack<Integer> minStack;//最小值数据栈

        public minStack(){
            dataStack = new Stack<Integer>() ;
            minStack = new Stack<Integer>() ;
        }

        public int pop(){
            if(!dataStack.empty()){
                minStack.pop();
                return dataStack.pop();
            }
            throw new RuntimeException("当前栈为空！");
        }

        public void push(int value){

            if(minStack.empty()){
               minStack.push(value);
            }
            else if(value>minStack.peek()){
                minStack.push(value);
            }else{
                minStack.push(minStack.peek());
            }

            dataStack.push(value);

        }

        public int getMin(){
            if(minStack.empty()){
                throw new RuntimeException("栈为空！");
            }
            return minStack.peek();
        }
    }


}
