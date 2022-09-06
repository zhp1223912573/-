package 树;

/**
 * @author zhp
 * @date 2022-07-06 11:58
 * 找到一棵二叉树中，最大的搜索二叉子树，返回最大搜索二叉子树的节点个数。
 */
public class _最大二叉搜索子树 {
    /**
     * 二叉树问题套路
     * <p>
     * 套路：
     * 分析递归过程中需要的信息，封装成一个类向上传递。
     * <p>
     * 题目分析：
     * 要得到最大二叉搜索子树，可以对当前根节点进行分析，总共有两种情况。
     * 1.与根节点无无关
     * 1.1左子树存在最大二叉搜索子树
     * 1.2右子树存在最大二叉搜索子树
     * 判断上述两种情况中哪个二叉树更大，返回该值
     * 2.与当前根节点有关
     * 左二叉搜索子树中的最大二叉搜索子树的头节点是当前根节点的左孩子
     * 右二叉搜索子树中的最大二叉搜索子树的头节点是当前根节点的右孩子
     * 并且，左树的最大值小于当前节点的值，右树的最小值大于当前节点的值
     * 综上：
     * 我们需要得到最大二叉搜索子树的头节点，最大值，最小值以及二叉搜索树的节点数量
     */
    static class Info {
        public TreeNode maxBSTHead;
        public int maxBSTsize;
        public int max;
        public int min;

        public Info(TreeNode maxBSTHead, int maxBSTsize, int max, int min) {
            this.maxBSTHead = maxBSTHead;
            this.maxBSTsize = maxBSTsize;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(TreeNode root){
        //空节点需要设置信息的最大值最小值为整型最小值与最大值，避免其干扰节点判断
        if(root==null){
            return new Info(null,0, Integer.MIN_VALUE,Integer.MAX_VALUE);
        }

        //得到左右子树内的最大二叉搜索树信息
        Info leftTree = process(root.left);
        Info rightTree =  process(root.right);


        //先对不包含当前节点的情况进行分析
        //得到以root为头的二叉树信息
        int max = Math.max(root.val,Math.max(leftTree.max,rightTree.max));
        int min = Math.min(root.val,Math.min(leftTree.min,rightTree.min));
        //如果只考虑第一种情况，则需要得到左右子树中最大的二叉树
        //得到最大二叉树的头节点与大小
        int maxBSTsize = Math.max(leftTree.maxBSTsize,rightTree.maxBSTsize);
        TreeNode maxBSThead =  leftTree.maxBSTsize>rightTree.maxBSTsize ? leftTree.maxBSTHead:rightTree.maxBSTHead;

        //考虑加入当前节点是否可以构成一棵新的二叉搜索树
        //只有在左子树的头节点是当前根节点的左孩子，右子树的头节点是当前根节点的右孩子，
        // 并且当前节点root的值大于左子树最大值，小于右子树的最小值
        if(leftTree.maxBSTHead==root.left && rightTree.maxBSTHead==root.right
                && leftTree.max<root.val && rightTree.min>root.val){
            maxBSThead = root;
            maxBSTsize = leftTree.maxBSTsize + rightTree.maxBSTsize +1;
        }

        return new Info(maxBSThead,maxBSTsize,max,min);


    }
}
