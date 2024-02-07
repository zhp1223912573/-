package 树;

/**
 * @author zhp
 * @date 2021-11-28 12:20
 * 二叉搜索树中val值的查找
 * https://leetcode-cn.com/problems/search-in-a-binary-search-tree/submissions/
 */
public class 二叉搜索树中val值的查找_lc_700 {
    //递归
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;

        if (root.val > val) return searchBST(root.left, val);
        return searchBST(root.right, val);
    }

    //迭代
    public TreeNode searchBSTdiedai(TreeNode root, int val) {


        while (root != null) {
            if (root.val == val) return root;
            if (root.val > val) root = root.left;
            else if (root.val < val) root = root.right;
        }

        return null;
    }
}
