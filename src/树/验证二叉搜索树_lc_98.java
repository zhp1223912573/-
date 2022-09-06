package 树;

import java.util.Stack;

/**
 * @author zhp
 * @date 2021-11-28 12:36
 * 验证二叉搜索树的合法性
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 */
public class 验证二叉搜索树_lc_98 {

    public boolean isValidBST(TreeNode root) {
        return false;

    }

    /**
     * 利用二叉搜索树左节点小于根节点 右节点大于根节点的性质
     * 通过先序遍历得到一个有序数组 判断该有序数组是否排序正确
     */

    /**
     * 利用一个min最小数值 在中序遍历的过程中 若出现节点数值val大于min
     * 则不符合二叉搜索树的性质 直接返回false
     */
 


    /**
     * 在不生成数组的情况下也可以判断该树是否为二叉搜索树
     * 在中序遍历时 利用一个pre来记录被遍历的二叉树节点cur的前一个节点
     * 若pre的值大于等于cur 则该树不符合二叉搜索树的性质
     */

     //递归实现
    TreeNode pre = null;
    public boolean isvalid(TreeNode root){
        if(root==null) return true;

        boolean left=isvalid(root.left); //左

        if(!left){//左树不是，直接返回无须继续判断
            return  false;
        }

        //前一个节点值大于当前节点值
        if(pre!=null && pre.val>=root.val){ //中
            return false;
        }
        //符合性质 则继续向后寻找
        pre = root;
        boolean right = isvalid(root.right);//右

        return right;
    }

    //迭代实现
    public boolean isvaliddiedai(TreeNode root){
        if(root==null) return true;
        TreeNode pre = null;
        Stack<TreeNode>stack = new Stack<>();
        TreeNode cur= root;
        while(cur!=null || !stack.empty()){
            if(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }else{
                cur=stack.pop();
                if (pre != null && cur.val<=pre.val) {
                    return false;
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return true;
    }


}
