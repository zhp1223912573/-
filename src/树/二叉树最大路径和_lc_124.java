package 树;

/**
 * @author zhp
 * @date 2022-07-10 21:39
 * 给定一棵二叉树的头节点head，可以从树中的任何一点出发，如果走的话只能向下，
 * 也可以选择随时停止，所形成的轨迹叫做一条路径，路径上所有值的累加和叫作路径
 * 和。求这棵树上的最大路径和
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/
 */
public class 二叉树最大路径和_lc_124 {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode root) {
        if (root == null) return 0;

        //去除为负数的贡献值
        int left = Math.max(maxGain(root.left), 0);
        int right = Math.max(maxGain(root.right), 0);

        int sum = left + right + root.val;
        maxSum = Math.max(sum, maxSum);

        return (left < right ? right : left) + root.val;
    }

}
