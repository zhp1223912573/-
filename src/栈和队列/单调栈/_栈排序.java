package 栈和队列.单调栈;


import java.util.Stack;

/**
 * @author zhp
 * @date 2022-07-03 16:13
 *
 * 请编写一个程序，对一个栈里的整型数据，按升序进行排序（即排序前，栈里
 * 的数据是无序的，排序后最大元素位于栈顶），要求最多只能使用一个额外的
 * 栈存放临时数据，但不得将元素复制到别的数据结构中
 */
public class _栈排序 {
    /**单调栈技巧
     *要使原本的栈从栈底到栈顶是递增，那么我们添加的额外栈则刚好相反，需要从栈底到栈顶是递减的
     * 此时，我们就可以将额外栈中的元素弹出，实现目标。
     *
     * 具体步骤：从当前栈中弹出数字，如果额外栈的栈顶元素大于当前弹出元素，则将元素弹出到当前栈，
     *          不断重复，直到额外栈的栈顶元大于即将压入栈的元素。
     *          反复上述操作，直到当前栈为空
     */
    public static void stackSort(Stack<Integer> stack){
        Stack<Integer> help = new Stack<Integer>() ;
        while(!stack.empty()){
            int top = stack.pop();//弹出栈顶保存
            while(!help.empty() && top>help.peek()){
                stack.push(help.pop());//将辅助栈中的元素压回原始栈
            }
            help.push(top);
        }
        while(!help.empty()){
            stack.push(help.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(3);
        stack.push(1);
        stack.push(6);
        stack.push(2);
        stack.push(5);
        stack.push(4);
        stackSort(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
