package shiyan.Queue;

/**
 * @author zhp
 * @date 2021-12-16 19:34
 */

public interface Queue<T> {

    /**
     * 返回队列长度
     * @return
     */
    int size();

    /**
     * 判断队列是否为空
     * @return
     */
    boolean isEmpty();


    boolean add(T data);



    T peek();



    T poll();




    void clearQueue();

}