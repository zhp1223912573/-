package 树;

/**
 * @author zhp
 * @date 2021-11-23 11:16
 * 平衡二叉树
 */
public class    平衡二叉树_lc_110 {
    /**自顶向下
     * 按照惯例，拆分问题，一棵树是否为平衡二叉树，取决于他的左右子树是否为平衡二叉树，
     * 如果左右子树都为平衡二叉树，那么以当前root为根节点时，左子树的最大深度与右子树的最大深度之差就是
     * 次数是否为平衡二叉树的衡量标准。
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }

        return (isBalanced(root.left)&&isBalanced(root.right)&&!(Math.abs(getMaxLevel(root.left)-getMaxLevel(root.right))>1));

    }

    public boolean isBalanced2(TreeNode root) {
        if(root==null){
            return true;
        }

        int left = getMaxLevel(root.left);
        int right = getMaxLevel(root.right);
        if(Math.abs(right-left)>1) return false;

        return isBalanced(root.left)&&isBalanced(root.right);

    }
    public int getMaxLevel(TreeNode root){
        if(root==null){
            return 0;
        }

        return Math.max(getMaxLevel(root.left),getMaxLevel(root.right))+1;
    }

    /**自顶向下
     * 上面的自底向上，是从下层状态到上层状态一层层的推导，导致了许多重复状态的计算，
     * 我们使用递归的思路。从上往下退，如果出现了不符合的情况，直接中断递归，向上返回结果。
     *
     */
    public boolean isBalanced1(TreeNode root){
        return getMaxLevel1(root)!=-1;
    }

    public int getMaxLevel1(TreeNode root){
        if(root==null) return 0;
        int left = getMaxLevel1(root.left);
        if(left==-1) return -1;//提前阻断
        int right = getMaxLevel1(root.right);
        if(right==-1) return -1;

        //如果左右子树的差值小于2，也就是符合平衡二叉树规定，则返回最深子树深度，反之返回-1表示不可能存在平衡子树。
        return Math.abs(right-left)<2?Math.max(left,right)+1:-1;
    }
}
