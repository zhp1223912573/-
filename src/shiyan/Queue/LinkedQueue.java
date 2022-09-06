package shiyan.Queue;

import shiyan.Node;

/**
 * @author zhp
 * @date 2021-12-16 19:35
 * 链式队列的实现
 */
public class LinkedQueue<T> implements Queue<T> {

    public static void main(String[] args) {
         LinkedQueue<Integer> lq= new LinkedQueue<>();
         //添加元素
        lq.add(1);
        //获取队首元素
        System.out.println(lq.poll());
        //判断是否为空
        System.out.println(lq.isEmpty());
        //再次添加元素
        lq.add(2);
        lq.add(3);
        lq.add(4);
        //查看元素个数
        System.out.println(lq.size);
        //取出队首元素
        System.out.println(lq.peek());
        //查看剩余元素个数
        System.out.println(lq.size);
    }
    //参数
    int size;//元素个数
    private Node<T> pre,post;

    public LinkedQueue(){
        pre=post=null;
        size=0;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        //当前后节点都为空时 队列为空
        return pre==null&&  post==null;
    }

    /**
     * data 入队,添加成功返回true,否则返回false,可扩容
     * @param data
     * @return
     */
    @Override
    public boolean add(T data) {
        if(data!=null){
            Node<T> node = new Node<>(data,null);
            //队首节点为空
            if(this.pre==null){
                pre=node;
            }else {
                //队首不为空 则队尾节点的下一个节点制位
                this.post.next=node;
            }
            //移动队尾节点到最后
            this.post=node;
            //修改个数
            size++;
            return true;
        }
        return false;
    }



    /**
     * 返回队头元素,不执行删除操作,若队列为空,返回null
     * @return
     */
    @Override
    public T peek() {
        if(pre!=null){
            return pre.val;
        }
        return null;
    }

    /**
     * 出队,执行删除操作,返回队头元素,若队列为空,返回null
     * @return
     */
    @Override
    public T poll() {
        //队不为空
        if(!isEmpty()){
            T val=pre.val;
            pre=pre.next;
            size--;
            //若队首节点为空 则将队尾节点也设置为空
            if(this.pre==null){
                post=null;
            }
            return val;
        }

        return null;
    }


    /**
     * 清空队列
     */
    @Override
    public void clearQueue() {
        pre=post=null;
    }
}
