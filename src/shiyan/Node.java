package shiyan;

/**
 * @author zhp
 * @date 2021-12-16 18:59
 * 节点
 */
public class Node<T> {
    public T val;
    public Node<T> next;

    public Node(T val, Node<T> next) {
        this.val = val;
        this.next = next;
    }

    public Node(T val) {
        this.val = val;
    }

    public Node() {
    }
}
