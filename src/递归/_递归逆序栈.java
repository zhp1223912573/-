package 递归;


import java.util.Stack;

/**
 * @author zhp
 * @date 2022-06-13 17:03
 * 不借助任何其他数据结构，将传入的栈逆序
 * 该方法纯纯是在炫技，虽然不需要额外的数据结构，但是逆序过程中同样需要保存每个栈元素
 */
public class _递归逆序栈 {
    /**
     * 为了实现逆序，需要一个能将当前栈蒂元素出栈的函数
     */
    public static int popBottom(Stack<Integer> stack){
        //先获取当前栈顶元素并保存，这样后续才能弹出栈底元素
        int res = stack.pop();
        //为空就说明，我们已经在递归过程中到达了栈的底部，可以直接返回先前弹出的栈顶元素
        if(stack.isEmpty()){
            return res;
        }else{
            //不为空，则尚未到达栈底，需要继续向下递归，直到返回栈底元素
            int bottom = popBottom(stack);
            //得到栈底元素，需要将先前元素押回去，保证除了栈底元素，其他元素没有弹出
            stack.push(res);
            return bottom;
        }
    }

    public static void reveseStack(Stack<Integer> stack){
        if(stack==null || stack.isEmpty()){
            return ;
        }

        //得到栈底元素
        int bottom = popBottom(stack);
        //递归弹出所有栈底元素
        reveseStack(stack);
        //到这里说明所有栈元素都已经弹出并保持，可以在递归回收过程中进行压栈操作，达到逆序栈的效果
        stack.push(bottom);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        reveseStack(stack);
        System.out.println(stack);
    }
}
