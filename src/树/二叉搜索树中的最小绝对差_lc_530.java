package 树;

/**
 * @author zhp
 * @date 2021-11-29 10:54
 * 二叉搜索树中的最小绝对差
 * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 */
public class 二叉搜索树中的最小绝对差_lc_530 {
    //相对更简介的写法
    class Solution {
        int min = Integer.MAX_VALUE;
        int pre = Integer.MAX_VALUE;

        public int getMinimumDifference(TreeNode root) {
            inorder(root);
            return min;
        }

        public void inorder(TreeNode root) {
            if (root == null) {
                return;
            }

            inorder(root.left);
            min = Math.min(min, Math.abs(pre - root.val));
            pre = root.val;
            inorder(root.right);
        }
    }

    /*
        中序遍历即可
     */
    int pre;//代表前一个节点的值
    int min;

    public int getMinimumDifference(TreeNode root) {
        pre = -1;
        min = Integer.MAX_VALUE;
        dfs(root);
        return min;
    }

    public void dfs(TreeNode root) {
        if (root == null) return;

        if (root.left != null) dfs(root.left);


//        if(pre==-1){
//            pre=root.val;
//        }else{
//            min=Math.min(min,root.val-pre);
//        }
//        pre = root.val;
        if (pre != -1) min = Math.min( min, root.val - pre);
        pre = root.val;
        if (root.right != null) dfs(root.right);
    }
}
