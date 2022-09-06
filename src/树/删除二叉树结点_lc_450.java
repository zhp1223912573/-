package 树;

/**
 * @author zhp
 * @date 2021-11-30 11:52
 * 删除二叉搜索树中的节点
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/
 */
public class 删除二叉树结点_lc_450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        return root;
    }

    /**普通二叉树删除节点的方法 很重要！！！
     * 递归 代码相对简洁
     * 用交换值的操作来删除目标节点。
     *
     * 代码中目标节点（要删除的节点）被操作了两次
     * 第一次是和目标节点的右子树最左面节点交换。
     * 第二次直接被NULL覆盖了。
     */

    public TreeNode delete(TreeNode root,int key){
        if(root==null) return null;
        if(root.val==key){
            if(root.right==null){   // 这里第二次操作目标值：最终删除的作用
                return root.left;   //此处不能改为null 因为如果key就是该树的根节点 应当直接返回其左节点 （2，1）
            }
            TreeNode cur = root.right;//和右子树中的最左节点交换

            while(cur.left!=null){
                cur=cur.left;
            }
            //通过交换节点值实现第一次删除
            int val = root.val;
            root.val=cur.val;
            cur.val=val;
        }

        root.left=delete(root.left,key);
        root.right=delete(root.right,key);
        return root;
    }

    /**递归
     * 删除目标节点
     * 1、左子节点为空 返回右节点
     * 2.右子节点为空 返回左节点
     * 3.都不为空 将左子节点链接到右子树的最左节点上
     *
     */

    public TreeNode delete1(TreeNode root ,int key){
        if(root==null) return null;

        if (root.val > key) {
            root.left = delete1(root.left, key);
            return root;
        }
        if (root.val < key) {
            root.right = delete1(root.right, key);
            return root;
        }
        if(root.val==key){
            if(root.right==null){
                return root.left;
            }

            TreeNode cur = root.right;
            while(cur.left!=null){
                cur = cur.left;
            }

            root.val = cur.val;
            cur.val = key;

            root.right = delete1(root.right,key);
        }


        return root;

    }
}
