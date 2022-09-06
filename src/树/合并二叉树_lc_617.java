package 树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhp
 * @date 2021-11-28 11:52
 * 合并二叉树
 * https://leetcode-cn.com/problems/merge-two-binary-trees/
 */
public class 合并二叉树_lc_617 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return merge(root1,root2);
    }

    /**
     * 改变递归的顺序不影响结果
     * 本质就是对两棵树的先序后序中序的应用
     *
     * 同理，既可以返回一棵新树的节点 也可以以root1或者root2作为返回节点
     * @param root1
     * @param root2
     * @return
     */
    private TreeNode merge(TreeNode root1, TreeNode root2) {
        if(root1==null) return root2;
        if(root2==null) return root1;

        TreeNode newroot=new TreeNode(root1.val+root2.val);
        newroot.left=merge(root1.left,root2.left);
        newroot.right=merge(root1.right,root2.right);

        return newroot;


    }

    /**
     * 队列
     */
    public TreeNode merge1(TreeNode root1,TreeNode root2){
        if(root1==null) return root2;
        if(root2==null) return root1;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root1);
        queue.offer(root2);

        while(!queue.isEmpty()){
            TreeNode node1 = queue.poll();
            TreeNode node2=queue.poll();
            node1.val+=node2.val;
            if(node1.left!=null && node2.right!=null){
                queue.offer(node1.left);
                queue.offer(node2.left);
            }
            if(node1.right!=null && node2.right!=null){
                queue.offer(node1.right);
                queue.offer(node2.right);
            }

            if(node1.left==null&& node2.left!=null){
                node1.left=node2.left;
            }

            if(node2.right==null&& node2.right!=null){
                node1.right=node2.right;
            }

        }
        return root1;
    }


}
