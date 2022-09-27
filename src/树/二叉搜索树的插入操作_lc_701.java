package 树;

/**
 * @author zhp
 * @date 2021-11-30 10:50
 * 二叉搜索树的插入操作
 */
public class 二叉搜索树的插入操作_lc_701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        return root;
    }

    /**
     * 利用二叉搜索树的性质
     * 大于节点值则左递归 小于则右递归
     * 直到当前节点为空 该节点就是要插入的位置
     *
     * @param
     * @param val
     * @return
     */

    public TreeNode insert(TreeNode root, int val){
        if(root==null) {
            TreeNode node = new TreeNode (val);
            return node;
        }

        if(root.val>val) {
            root.left=insert(root.left,val);

        }else if(root.val<val){
            root.right=insert(root.right,val);
        }

        return root;
    }

    /**
     * 递归过程也可以不返回节点
     * 但需要一个parent点
     */
    TreeNode parent ;
    public void  insert1(TreeNode root,int val){

        if(root==null) {
            TreeNode node = new TreeNode(val);
            if (parent.val > val) {
                parent.left = node;
            } else {
                parent.right = node;
            }

            return ;
        }

            parent=root;
            if(root.val>val){
                insert1(root.left,val);
            }else{
                insert1(root.right,val);
            }

            return ;
        }


    /**
     * 迭代
     */
    public TreeNode insert2(TreeNode root,int val){
        if(root==null){
            return new TreeNode(val);
        }

        TreeNode cur = root;
        TreeNode parent = root;
        while(cur!=null){
            parent=cur;
            if(cur.val>val){
                cur=cur.left;
            }else{
                cur=cur.right;
            }
        }

        if(parent.val>val){
            parent.left=new TreeNode(val);
        }else{
            parent.right=new TreeNode(val);
        }

        return root;
    }
}
