package 树;

/**
 * @author zhp
 * @date 2021-11-22 12:22
 * 二叉树节点个数求解
 */
public class 二叉树节点个数_lc_222 {

    /**
     * 下述是求解普通二叉树节点个数的思路
     */

    /**
     * 递归求解
     * 分别得到左右子节点个数 然后相加再加一表示当前子节点个数
     *
     * @param root
     * @return
     */
    public int getNodeSum(TreeNode root) {
        if (root == null) return 0;
        int leftnum = getNodeSum(root.left);
        int rightnum = getNodeSum(root.right);
        int sum = leftnum + rightnum + 1;
        return sum;
    }

    /**
     * 递归简化版
     *
     * @param root
     * @return
     */
    public int getNodeSum_simple(TreeNode root) {
        if (root == null) return 0;
        return 1 + getNodeSum_simple(root.left) + getNodeSum_simple(root.right);
    }

    /**
     * 利用完全二叉树的特性求解
     * 判断当前遍历节点是否为一棵满二叉树，
     * 若该树为满二叉树且层数为n 则满二叉树的节点数为2^n 根节点层数为1
     * 若不为满二叉树，则递归求解其子节点，一个节点也是满二叉树
     */
    public int getNodeSum_tree(TreeNode root) {
        if (root == null) return 0;

        TreeNode left = root.left;
        TreeNode right = root.right;
        int leftnum = 0;
        int rightnum = 0;
        //递归左子节点
        while (left != null) {
            left = left.left;
            leftnum++;
        }
        //递归右子节点
        while (right != null) {
            right = right.right;
            rightnum++;
        }
        //若上述递归求解得到的节点深度一致 说明当前root节点为一棵二叉树
        //可以直接使用2^深度 来得到节点总数 并返回
        if (leftnum == rightnum) {
            return (1 << leftnum + 1) - 1;
        }

        //若目前的root节点树不是一棵满二叉树 直接递归求解其左右子节点 去寻找满二叉树
        //返回是需要+1 代表root节点
        return getNodeSum_tree(root.left) + getNodeSum_tree(root.right) + 1;

    }


    /**下述是求解完全二叉树的节点个数
     *
     *
     */

    /**
     * 递归求解
     * <p>
     * 1.先得到当前树的深度，不断向左孩子遍历，得到深度。
     * 2.判断该当前根节点的右子树的最左节点层数是否等于最大深度，
     * 是的话说明当前节点的左孩子是满二叉树
     * 不是的话说明当前节点的右孩子是满二叉树
     */
    public static int nodeNum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return bs(root, 1, mostleftLevel(root, 1));
    }

    /**
     * 求解树的节点数
     *
     * @param root  根节点
     * @param level 当前根节点所在层数
     * @param h     整棵树的最大深度，在递归过程中不变
     * @return
     */
    public static int bs(TreeNode root, int level, int h) {
        if (level == h) {//如果当前节点所在深度等于最大深度，说明当前节点是叶结点，直接返回1
            return 1;
        }

        //如果当前节点的右子树的最左节点的深度等于最大深度，说明当前节点的左子树是一棵满二叉树（完全二叉树的性质，画图理解）
        //而满二叉树的节点个数为2^(子树的深度-1）-1,记住这里需要加上当前节点，所以需要再+1。
        //最后以当前节点的右孩子为新的树根节点向下递归求解，该右子树也是一棵完全二叉树
        if (mostleftLevel(root.right, level + 1) == h) {
            return (1 << (h - level)) + bs(root.right, level + 1, h);
        }
        //如果当前节点的右子树的最左节点的深度不等于最大深度，说明当前节点的右子树就是一棵满二叉树（完全二叉树的性质，画图理解）
        //而满二叉树的节点个数为2^(子树的深度-1）-1,记住这里需要加上当前节点，所以需要再+1。
        //最后以当前节点的左孩子为新的树根节点向下递归求解，该左子树也是一棵完全二叉树
        else {
            //这里多了个-1，因为如果是右孩子为满二叉树，那么深度应该是最大深度-1才对
            return (1 << (h - level - 1)) + bs(root.left, level + 1, h);

        }
    }

    /**
     * 得到目的树的深度
     *
     * @param root  根节点
     * @param level 当前根节点在二叉树内的第几层
     * @return 最左节点的层数
     */
    public static int mostleftLevel(TreeNode root, int level) {
        while (root.left != null) {
            level++;
            root = root.left;
        }
        return level;
    }


    /**
     * 基于二分和位运算实现的完全二叉树节点个数查找
     * 时间开销为O(log^2n)
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        TreeNode node = root;
        int level = 0;
        //计算深度
        while (node.left != null) {
            node = node.left;
            level++;
        }

        //最底层的最左左节点和最右节点的编号
        int left = 1 << level;
        int right = (1 << (level + 1)) - 1;
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            if (check(root, level, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public boolean check(TreeNode root, int level, int k) {

        int bits = 1 << (level - 1);
        while (root != null && bits > 0) {
            if ((bits & k) == 0) {
                root = root.left;
            } else {
                root = root.right;
            }
            bits >>= 1;
        }
        return root != null;
    }
}
