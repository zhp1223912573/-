package 树;

/**
 * @author zhp
 * @date 2021-11-20 12:28
 */
public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, 树.TreeNode left, 树.TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

}
