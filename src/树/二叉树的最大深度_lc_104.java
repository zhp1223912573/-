package 树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhp
 * @date 2021-11-22 11:04
 * 二叉树最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 *
 */
public class 二叉树的最大深度_lc_104 {
    public int maxDepth(TreeNode root) {
        return -1;
    }

    /**递归
     *
     * @param root
     * @return
     */
    public int getMaxdepth(TreeNode root){
        if(root==null) return 0;
        return Math.max(getMaxdepth(root.left)+1,getMaxdepth(root.right)+1);
    }

    public int getMaxdepth2(TreeNode root){
        if(root==null) return 0;
        int maxLevel=0;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {

            int len = queue.size();
            while (len > 0) {
                TreeNode node =queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                len--;
            }
            maxLevel++;
        }
        return maxLevel;
    }

}
