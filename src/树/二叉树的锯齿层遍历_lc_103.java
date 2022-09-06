package 树;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import java.util.*;

/**
 * @author zhp
 * @date 2022-07-24 20:50
 * https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
 */
public class 二叉树的锯齿层遍历_lc_103 {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    /**
     *层序遍历逆转队列元素即可。
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();
        if(root==null) return ans;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int flag = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode poll = queue.poll();
                if(poll.left!=null){
                    queue.add(poll.left);
                }
                if(poll.right!=null){
                    queue.add(poll.right);
                }
                list.add(poll.val);
            }
            if(flag==1){
                Collections.reverse(list);
            }
            flag = 1-flag;
            ans.add(list);

         }
        return ans;
     }


}
