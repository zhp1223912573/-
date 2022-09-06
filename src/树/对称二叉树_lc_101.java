package 树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhp
 * @date 2021-11-22 10:44
 * 对称二叉树
 * https://leetcode-cn.com/problems/symmetric-tree/submissions/
 */
public class 对称二叉树_lc_101 {
    public boolean isSymmetric(TreeNode root) {
        return false;

    }

    /*这题的关键点在于对对应位置节点的处理 */

    /**迭代
     * 使用一个队列存储相对应的节点
     * @param root
     * @return
     */
    public boolean diedai(TreeNode root){
        if(root==null) return false;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while(!queue.isEmpty()){
             TreeNode right=queue.poll();
             TreeNode left=queue.poll();

             if(right==null&&left==null) continue;
             if(right==null||left==null) return false;
             if(right.val!=left.val) return false;

             queue.offer(left.left);
             queue.offer(right.right);
             queue.offer(left.right);
             queue.offer(right.left);
        }

        return true;
    }

    /**递归
     *
     * @param left
     * @param right
     * @return
     */
    public boolean compareTreeNode(TreeNode left,TreeNode right){
        if(left==null) return right==null;
        if(right==null) return false;
        if(left.val!=right.val) return false;

        return compareTreeNode(left.left,right.right) && compareTreeNode(left.right,right.left);
    }
}
