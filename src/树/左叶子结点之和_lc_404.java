package 树;

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
        if(root.left==null&&root.right==null){
            max+=root.left.val;
        }
        if(root.left!=null){
            sumOfLeftLeaves(root.left);
        }

        if(root.right!=null){
            sumOfLeftLeaves(root.right);
        }

        return max;

    }

    public int sum(TreeNode root){
        if(root==null) return 0;
        int mid=0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            mid = root.left.val;
        }
        return mid + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }


}
