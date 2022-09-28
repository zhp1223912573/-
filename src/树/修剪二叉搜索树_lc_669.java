package 树;

/**
 * @author zhp
 * @date 2021-12-03 10:52
 * 修剪二叉搜索树
 * https://leetcode-cn.com/problems/trim-a-binary-search-tree/
 */
public class 修剪二叉搜索树_lc_669
{
    public TreeNode trimBST(TreeNode root, int low, int high) {
        return trim(root,low,high);

    }

    /**
     * 这个方法是先递归到底层叶子节点 不断返回符合条件的节点
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trim(TreeNode root,int low,int high){
        if(root==null) return null;
        if(root.left!=null) root.left=trim(root.left,low,high);
        if(root.right!=null) root.right=trim(root.right,low,high);
        if(root.val<low){
            return root.right;
        }
        if(root.val>high){
            return root.left;
        }
        return root;
    }

    /**
     * 该方法是从上往下得到符合条件节点
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trim1(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trim1(root.right, low, high);
        }
        if (root.val > high) {
            return trim1(root.left, low, high);
        }
        // root在[low,high]范围内
        root.left = trim1(root.left, low, high);
        root.right = trim1(root.right, low, high);
        return root;
    }

    /**
     * 迭代法
     *
     * 移动根节点到区间 low--high的范围内，
     * 在删除当前根节点所有不在区间内的子节点
     */
    public TreeNode trim2(TreeNode root,int low,int high){
        if(root==null) return null;
        /*将root节点移动到【low，high】之间*/
        while(root!=null &&(root.val<low||root.val>high)){
            if(root.val<low){
                root=root.right;
            }else{
                root=root.left;
            }
        }

        TreeNode cur = root;
        /*开始删除不在区间范围内的节点*/
        while(cur!=null){
           while(cur.left!=null && cur.left.val<low){
                cur.left=cur.left.right;
            }
            cur=cur.left;
        }

        cur=root;
        /*开始删除不在区间范围内的节点*/
        while(cur!=null){
            while(cur.right!=null && cur.right.val>high){
                cur.right=cur.right.left;
            }
            cur=cur.right;
        }
        return root;
    }
}
