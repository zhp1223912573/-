package 树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhp
 * @date 2021-11-22 11:26
 * 最小深度
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 */
public class 二叉树最小深度_lc_111 {
    public int minDepth(TreeNode root) {
        return -1;

    }

    /**官方解答 递归
     *把大问题拆解为小问题分别寻找答案
     *
     */
    public int minD(TreeNode root){
        if(root==null)  return 0;

        if(root.left==null && root.right==null) return 1;

        int min=Integer.MAX_VALUE;

        if(root.left!=null) min=Integer.min(minD(root.left),min);

        if(root.right!=null) min=Integer.min(minD(root.right),min);

        return min+1;
    }

    /**迭代寻找
     * 如果出现
     * @param root
     * @return
     */
    public  int getMindepth(TreeNode root) {
        if(root==null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            depth++;
            while(size>0){
                TreeNode node =queue.poll();
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
                if(node.left==null && node.right==null) return depth;
            }
        }
        return depth;
    }

    /**利用深度进行剪枝
     *
     */
    int min=Integer.MAX_VALUE;
    public int callFunc(TreeNode root){
        if(root==null) return 0;
        Func(root,1);
        return min;
    }
    public void Func(TreeNode root,int deep){
        if(root==null) return ;
        if(deep>min) return ; //如果所在深度大于min则没必要往下寻找了
        if(root.left==null && root.right==null) {
            min=Integer.min(min,deep);
        }

        Func(root.left,deep+1);
        Func(root.right,deep+1);
    }

}
