package 树;


import java.util.Stack;

/**
 * @author zhp
 * @date 2022-07-24 21:21
 * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
 */
public class 二叉搜索树的第k个结点_lc_230 {

      public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }
        /**递归实现
         * 中序遍历
         */
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**递归实现
     * 中序遍历
     */
    public int kthSmallest(TreeNode root, int k) {
        return   getK(root,k);
    }
    int count =0;
    public int getK(TreeNode root,int k){
        if(root==null) return -1;

        int left = getK(root.left,k);
        if(left!=-1) return left;
        //
        count++;
        if(count==k) return root.val;

        int right = getK(root.right,k);
        if(right!=-1) return right;

        return -1;
    }

    /**迭代
     * 中序遍历
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest1(TreeNode root, int k) {
        Stack<TreeNode> stack = new java.util.Stack<>();
        TreeNode cur = root;
        int num = 0;
        while(cur!=null||!stack.isEmpty()){
            if(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }else{
                TreeNode pop = stack.pop();
                num++;
                if(num==k){
                    return pop.val;
                }
                cur = pop.right;

            }
        }
        return -1;
    }

    /**递归
     *通过即使当前结点的左子树结点个数left，来判断当前结点是否为第k小的结点，
     * 如果left<k-1,说明第k个结点在当前结点右子树中
     * 如果left>k-1,说明第k个结点在当前结点左子树中
     * 如果left==k-1，说明当前结点就是第k小的结点
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest2(TreeNode root, int k) {
        if(root==null) return -1;
        TreeNode cur = root;
        while(cur!=null){
            int left = getCount(cur.left);
            if(left>k-1){
                cur = cur.left;

            }else if(left<k-1){
                cur = cur.right;
                k = k-left-1;
            }else{
                return cur.val;
            }
        }
        return -1;
    }

    public int getCount(TreeNode root){
        if(root==null) return 0;
        return  1+(getCount(root.left)+getCount(root.right));
    }







}
