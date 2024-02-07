package 树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhp
 * @date 2021-11-27 11:49
 * https://leetcode-cn.com/problems/sum-of-left-leaves/submissions/
 * 左叶子之和
 */
public class 左叶子结点之和_lc_404 {
    /**
     * 递归
     */
    int max;
    public int sumOfLeftLeaves(TreeNode root) {
        if(root==null) return 0;
        int sum = 0;

        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum = root.left.val;
        }
        if(root.left!=null){

            sum+=sumOfLeftLeaves(root.left);
        }

        if(root.right!=null){
            sum+=sumOfLeftLeaves(root.right);
        }

        return sum;


    }

    /**
     * 递归精简版本
      * @param root
     * @return
     * 一个节点的左子节点为叶子节点时，直接返回值，反之继续递归求解
     */
    public int sum(TreeNode root){
        if(root==null) return 0;
        int mid=0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            mid = root.left.val;
        }
        return mid + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    /**
     * 层序遍历
     * @param root
     * @return
     */
    public int sumOfLeftLeaves1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                //要加入的左节点为叶子结点时进行累加
                if (isLeafNode(node.left)) {
                    ans += node.left.val;
                } else {
                    queue.offer(node.left);
                }
            }
            if (node.right != null) {
                if (!isLeafNode(node.right)) {
                    queue.offer(node.right);
                }
            }
        }
        return ans;
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

}
