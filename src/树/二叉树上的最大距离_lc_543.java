package 树;

/**
 * @author zhp
 * @date 2022-06-15 13:49
 * https://leetcode.cn/problems/diameter-of-binary-tree/
 *
 * 递归，树形dp问题
 */
public class 二叉树上的最大距离_lc_543 {


    /**
     * 典型树形dp问题，要得到树的最大直径，显然需要得到以每个节点为根节点的树中的最长路径以及当前树高度。
     * 根据当前节点的左右节点的信息，判断是否需要加上当前节点才能构成最长路径。
     */
    //以当前节点为树的最大距离以及树的高度
    class info{
        int maxDistance;
        int height;

        public info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    /**
     * 分类讨论：
     * 1.当前根节点不在路径中，那么最大距离是左子树中或者右子树中最大距离的更大者
     * 2.当前根节点在路径中，那么最大距离是左右子树的深度之和+1
     * 取上述两个条件中的最大值，就是该树的最大距离
     * @param root
     * @return
     */
    public info proceess(TreeNode root){
        if(root==null){
            return new info(0,0);
        }

        info leftinfo = proceess(root.left);
        info rightinfo = proceess(root.right);
        int maxDistance = Math.max(leftinfo.height+rightinfo.height+1,
                Math.max(leftinfo.maxDistance,rightinfo.maxDistance));
        int height = Math.max(leftinfo.height,rightinfo.height)+1;
        return new info(maxDistance,height);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        return proceess(root).maxDistance;
    }
}
