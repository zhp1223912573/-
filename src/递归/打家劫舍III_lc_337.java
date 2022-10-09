package 递归;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhp
 * @date 2022-09-06 21:48
 * 树形dp问题
 */
public class 打家劫舍III_lc_337 {

   class TreeNode {
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

    /**递归
     *偷或者不偷当前结点，递归所有情况，因为众多重复情形的出现，导致超时。
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if(root==null) return 0;
        if(root.left==null&&root.right==null) return root.val;
        //偷当前结点
        int stealRoot = root.val;
        if(root.left!=null) stealRoot += rob(root.left.left)+rob(root.left.right);
        if(root.right!=null) stealRoot += rob(root.right.left)+rob(root.right.right);
        //不偷当前结点
        int noStealRoot = 0;
        noStealRoot += rob(root.left)+rob(root.right);
        return Math.max(noStealRoot,stealRoot);
    }

    /**记忆化递归
     *虽然优化了，但也同样超时
     * @param root
     * @return
     */
    public int rob1(TreeNode root){
        Map<TreeNode,Integer> memory = new HashMap<>();
        return rob1(root,memory);
    }
    public int rob1(TreeNode root, Map<TreeNode,Integer> memory){
        if(root==null) return 0;
        if(root.left==null&&root.right==null) return root.val;
        //获取记录
        if(memory.get(root)!=null) return memory.get(root);
        //偷当前结点
        int stealRoot = root.val;
        if(root.left!=null) stealRoot += rob(root.left.left)+rob(root.left.right);
        if(root.right!=null) stealRoot += rob(root.right.left)+rob(root.right.right);
        //不偷当前结点
        int noStealRoot = 0;
        noStealRoot += rob(root.left)+rob(root.right);
        //记录
        memory.put(root,Math.max(noStealRoot,stealRoot));
        return Math.max(noStealRoot,stealRoot);
    }

    /**树形dp
     * 状态分析，
     * 每个结点只有被偷和不被偷的状态，而要找出最大的受益值，显然需要以整棵树的根节点判断整棵树的最大收益，
     * 所以采取后序遍历来获取最大收益值。
     * 而每个结点为根节点的最大收益取决于其左右子结点（也就是左右子树）的偷与不偷两种状态的最大收益，
     * 1.如果偷当前结点，那么该结点的最大收益为左右子结点不被偷的情况下的最大收益之和加上当前结点。
     * 2.如果不偷当前结点，那么该结点的最大收益为左右树的两者被偷或者不被偷的情况下的最大值之和。
     *
     */
        public int rob2(TreeNode root) {
            int[] ints = rob3(root);
            return Math.max(ints[0],ints[1]);
        }

        public int[] rob3(TreeNode root){
            int res[] = new int[2];
            if(root==null) return res;

            //获取当前结点的左右子树情况
            int left[] = rob3(root.left);
            int right[] = rob3(root.right);

            //第一种情况 偷当前结点
            res[0] = root.val+left[1]+right[1];
            //第二种情况 不偷当前结点
            res[1] = Math.max(left[0],left[1])+Math.max(right[0],right[1]);
            return res;
        }
}
