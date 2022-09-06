package shiyan.Tree;

/**
 * @author zhp
 * @date 2021-12-17 21:55
 * 树节点
 */
public class TreeNode<T extends Comparable> {
    public T val;
    public TreeNode<T> left,right;

    public TreeNode(T val) {
        this.val = val;
    }

    public TreeNode(T val, TreeNode<T> left, TreeNode<T> right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode() {
    }
}
