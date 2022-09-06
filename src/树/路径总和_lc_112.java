package 树;

/**
 * @author zhp
 * @date 2021-11-27 11:55
 * https://leetcode-cn.com/problems/path-sum/
 * 路径总和
 */
public class 路径总和_lc_112 {


    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null) return false;

        if(root.left==null&&root.right==null) return targetSum==root.val;

        return hasPathSum(root.left,targetSum-root.val) || hasPathSum(root.right,targetSum-root.val);
    }


}