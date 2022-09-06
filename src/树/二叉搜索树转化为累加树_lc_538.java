package 树;

import java.util.Stack;

/**
 * @author zhp
 * @date 2021-12-03 12:14
 * 把二叉搜索树转化为累加树
 * https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
 */
public class 二叉搜索树转化为累加树_lc_538 {
    public TreeNode convertBST(TreeNode root) {
        sum=0;
        convert(root);
        return root;
    }

    /**
     * 反中序遍历
     * 利用sum记录累加值
     *
     */

    int sum;
    public  void convert(TreeNode root){
        if(root==null) return ;

        convert(root.right);
        sum+=root.val;
        root.val=sum;
        convert(root.left);

    }
    /**
     * 中序遍历迭代写法 添加pre记录值
     */
    int pre=0;
    public void diedai(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur=root;
        while(cur!=null || !stack.empty()){
            if(cur!=null){
                stack.push(cur);
                cur=cur.right;//右
            }else{
                cur=stack.pop();//中
                cur.val+=pre;
                pre=cur.val;
                cur=cur.left;//左
            }
        }
    }
}
