package 树;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhp
 * @date 2022-10-22 12:19
 * https://leetcode.cn/problems/bst-sequences-lcci/comments/
 */
public class ms04_09_二叉搜索树序列 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /**很有意思的一道题
     * 基于bfs和回溯算法
     * 题解思路：https://leetcode.cn/problems/bst-sequences-lcci/solution/mei-yi-ge-jie-dian-du-bi-xu-pai-zai-ta-d-n679/
     */
    List<List<Integer>> ans;
     public List<List<Integer>> BSTSequences(TreeNode root) {
        ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if(root==null){
             ans.add(path);
             return ans;
        }
        //保存节点的队列
        List<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        bfs(queue,path);

        return ans;
    }
    

    private void bfs(List<TreeNode> queue, List<Integer> path) {
         if(queue.isEmpty()){
             ans.add(new ArrayList<>(path));
             return ;
         }

         //对队列状态进行备份
        ArrayList<TreeNode> copy = new ArrayList<>(queue);
         for(int i=0;i<queue.size();i++){
             TreeNode cur = queue.get(i);
             path.add(cur.val);
             queue.remove(i);
             // 将左右子节点加入队列
             if (cur.left != null) queue.add(cur.left);
             if (cur.right != null) queue.add(cur.right);
             bfs(queue, path);
             // 恢复 path 和 queue ，进行回溯
             path.remove(path.size() - 1);
             queue = new ArrayList<>(copy);
         }

    }
}
