package 树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhp
 * @date 2021-11-21 10:25
 * 层序遍历
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class 层序遍历_lc_102 {

    List<List<Integer>> res=new ArrayList<>();

    /**递归实现
     *res为每层的节点按序排列的list集合
     * @param root
     * @param deep  当前节点所在的深度
     *
     */
    public void DFS(TreeNode root,int deep){
        //设置返回条件
        if(root==null) return ;
        deep++;

        //如果当前res的层数小于深度deep 说明目前节点是该层的第一个节点 我们为其创建一个list
        if(res.size()<deep){
            List<Integer> item = new ArrayList<>();
            res.add(item);
        }

        //加入当前递归的节点val
        res.get(deep-1).add(root.val);

        if(root.left!=null) DFS(root.left,deep);
        if(root.right!=null) DFS(root.right,deep);

    }


    public List<List<Integer>> levelOrder(TreeNode root) {

        return res;
    }


    public List<List<Integer>> BFS(TreeNode root){
        //BFS 使用一个队列实现
        Queue<TreeNode> queue=new LinkedList<>();
        if(root==null) return res;
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> itemList= new ArrayList<>();
            //记录当前队列的个数 该值即是此层的节点个数
            int len=queue.size();
            //不为0 说明节点还没有遍历完
            while(len>0){
                TreeNode node = queue.poll();
                itemList.add(node.val);
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
                len--;
            }
            res.add(itemList);
        }
        return res;
    }
}
