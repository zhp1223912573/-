package 树;

/**
 * @author zhp
 * @date 2021-11-30 10:31
 * 二叉搜索树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class 二叉树搜索树的最近公共祖先_lc_235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return root;
    }

    /**
     * 利用二叉搜索树的性质 直接对对树进行遍历
     * 对遍历到的root节点进行判断
     * 只要root的val大于p，q节点说明当前节点过大       向左
     *  或者root的val小于p，q节点说明当前节点过小       向右
     *  直到出现数值位于两数中间取值的[q.val,p.val]节点 该节点就是最小节点
     *  应为这个性质本题不必和获得普通二叉树一样递归求解left和right 返回两者&&值
     *  只需要返回对单边的搜索
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestAncestor(TreeNode root, TreeNode p,TreeNode q){
        if(root==null) return null;

        if(root.val>p.val && root.val>q.val){
            TreeNode left = lowestAncestor(root.left,p,q);
            if(left!=null){
                return left;
            }
        }

        if(root.val<p.val && root.val<q.val){
            TreeNode right = lowestAncestor(root.right,p,q);
            if(right!=null){
                return right;
            }
        }

        return root;

    }

    /**
     * 迭代法
     */
    public TreeNode lowestAncestor1(TreeNode root, TreeNode p,TreeNode q){
        while(true){
            if(root.val>q.val&&root.val>p.val){
                root=root.left;
            }else if(root.val<p.val&&root.val<q.val){
                root=root.right;
            }else{
                break;
            }
        }

        return root;
    }
}
