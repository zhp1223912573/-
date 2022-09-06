package 树;


import java.util.Stack;

/**
 * @author zhp
 * @date 2021-11-21 11:03
 * 反转二叉树 镜像二叉树
 *https://leetcode.cn/problems/invert-binary-tree/submissions/
 */
public class 反转二叉树_lc_226 {
    /**
     *
     * @param root
     * @return
     */
    public TreeNode invertTree1(TreeNode root) {
        if(root==null) return null;

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = invertTree1(right);
        root.right = invertTree1(left);

        return root;
    }



    public TreeNode invertTree(TreeNode root) {
        //递归
//        invertTreeNode(root);
//        return root;

        //先序
        return xianxu(root);
    }

    /**先序递归
     *
     * @param root
     * @return 返回一颗新树的头节点
     * 利用镜像树的节点恰好是该树的先序遍历 先开始右节点的现象
     *
     */
    public TreeNode xianxu(TreeNode root){
        if(root==null) return root;

        TreeNode newRoot=new TreeNode(root.val);
        newRoot.left=xianxu(root.right);
        newRoot.right=xianxu(root.left);
        return newRoot;
    }

    /**
     * 递归反转
     * @param root
     */
    public void invertTreeNode(TreeNode root){
        if(root==null) return;

        TreeNode left=root.left;
        TreeNode right=root.right;
        root.left=right;
        root.right=left;

        invertTreeNode(root.left);
        invertTreeNode(root.right);
    }

    /*以下是非递归写法*/

    /**先序迭代
     * 使用先序遍历的迭代写法交换节点的左右子节点
     * @param root
     */
    public void preOrder(TreeNode root){
        if(root==null) return ;
        Stack<TreeNode> stack=new Stack<>();

        stack.push(root);
        while(!stack.empty()){
            TreeNode pop = stack.pop();
            //swap(root.right,root.left);
            if(root.right!=null) stack.push(root.right);   // 右
            if(root.left!=null) stack.push(root.left);     // 左
        }
    }

    /**广度优先
     *
     */



}
