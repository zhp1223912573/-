package 树;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhp
 * @date 2021-11-29 12:09
 * 二叉树的最近公共祖先
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class 二叉树的最近公共祖先_lc_236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return null;

    }

    /**该解法基于回溯
     * 使用后序遍历实现回溯
     * 如果出现一个节点的左右节点包含或者是p,q那么该数就是p，q的公共祖先
     * 通过后序实现的回溯 可以从叶子节点向上寻找公共祖先
     * 如果出现了某个节点同时拥有p，q那么该节点就是最小公共祖先
     * 如果只拥有p，q中的一个节点 说明 该节点还不是最小公祖先
     * 需要不断的向上传递根节点 直到找到
     */
    public TreeNode findAncestor(TreeNode root,TreeNode p, TreeNode q){
        if(root==p||root==q||root==null) return root;

        TreeNode left = findAncestor(root.left,p,q);
        TreeNode right = findAncestor(root.right,p,q);

        if(left!=null && right != null){
            return root;
        }

        if(left==null && right!=null){
            return right;
        }
        if(left!=null && right ==null){
            return left;
        }

        return null;
    }

    /**
     * 使用fater-map记录节点的val的父节点  因为节点值唯一 所以方法可行
     * 使用vis-map记录已经p访问过的父节点
     * 在q访问同一个父节点值直接返回该节点
     *
     */
    Map<Integer,TreeNode> map = new HashMap<>();
    Set<Integer> visited = new HashSet<>();
    void dfs(TreeNode root){
        if(root.left!=null){
            map.put(root.left.val,root);
            dfs(root.left);
        }

        if(root.right!=null){
            map.put(root.right.val,root);
            dfs(root.right);
        }
    }
    public TreeNode findAncestor1(TreeNode root,TreeNode p, TreeNode q){
        dfs(root);
        while(p!=null){
            visited.add(p.val);//记录已经访问的节点
            //得到当前节点的父节点
            p=map.get(p.val);
        }
        while(q!=null){
            if(visited.contains(q.val)){//再次访问到相同节点 则该节点就是最小公共祖先节点
                return q;
            }
            q=map.get(q.val);
        }
        return  null;
    }


}
