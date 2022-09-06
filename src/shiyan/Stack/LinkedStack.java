package shiyan.Stack;

import shiyan.Node;

import java.util.EmptyStackException;

/**
 * @author zhp
 * @date 2021-12-16 19:02
 * 链式栈的实现
 */
public class LinkedStack<T> implements Stack<T> {

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        //压入1，2，3
        stack.push(1);
        stack.push(2);
        stack.push(3);

        //弹出栈顶元素 显示剩余元素个数
        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.size());
        //再压入1 使用peek获取栈顶元素
        stack.push(1);
        System.out.println(stack.peek());
        //查看剩余元素个数
        System.out.println(stack.size());



    }
    //栈顶节点
    private Node<T> top;
    //栈元素数量
    private int count;

    public int size(){
        return count;
    }
    /**
     *  通过栈顶及其值判断是否为空
     */
    @Override
    public boolean isEmpty() {
        return top==null||top.val==null;
    }



    @Override
    public void push(T data) {
        if(data==null){
            throw new NullPointerException("data为null");
        }


        if(top==null){
            //top不存在 则创建一个新节点
                top=new Node<T>(data);
        }else if(this.top.val==null){
            //top存在 且其值为空 将data赋值给他
                top.val=data;
        }else{
            //top存在且值不为空
            Node<T> node = new Node<>(data, top);
            top=node;//更新头节点
        }
        count++;
    }

    /**
     * 获取栈顶元素
     * @return
     */
    @Override
    public T peek() {
       if(isEmpty()){
          throw new EmptyStackException();
       }
        return top.val;
    }

    /**
     * 弹出栈顶元素
     * @return
     */
    @Override
    public T pop() {
        if(isEmpty()){
           throw new EmptyStackException();
        }

        T data = top.val;
        top=top.next;
        count--;
        return data;
    }
}
