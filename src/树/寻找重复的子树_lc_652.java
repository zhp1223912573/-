package 树;

import java.util.*;

/**
 * @author zhp
 * @date 2022-07-18 16:29
 * https://leetcode.cn/problems/find-duplicate-subtrees/
 */
public class 寻找重复的子树_lc_652 {

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


    /**序列化树
     * 时间复杂度O（n^2)
     * 将子树序列化，比较不同子树是否一致，一致的画，保存起来。
     * 用map保存某2现次数的映射关系，
     * 当前不同结点的序列化树在map中出现两次，说明存在结构完全相同的子树，
     * 这时就可以保存结点了。
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees1(TreeNode root) {
        Map<String,Integer> map = new HashMap<>();
        List<TreeNode> ans = new ArrayList<>();
        findDuplicate(root,map,ans);
        return ans;
    }

    /**
     * 序列化二叉树，同时检查是否出现相同子树。
     * @param root
     * @param map
     * @param ans
     */
    private String findDuplicate(TreeNode root, Map<String, Integer> map, List<TreeNode> ans) {
        if(root==null){
            return "#";
        }
        //递归得到序列树
        String serial = root.val +","+ findDuplicate(root.left,map,ans)+","+findDuplicate(root.right,map,ans);
        //更新serial次数
        map.put(serial,map.getOrDefault(serial,0)+1);
        //出现两次说明，存在相同子树
        if(map.get(serial)==2){
            ans.add(root);
        }
        return serial;
    }


    /**
     * 得到所有子树节点，依次子树是否一致的比较，得到一个不重复的子树节点集合，
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> subTree = new ArrayList<>();
        Set<TreeNode> ans = new HashSet<>();
        getSubTree(root.left,subTree);
        getSubTree(root.right,subTree);

        for(int i=0;i<subTree.size();i++){
            for(int j=1;j<subTree.size();j++){
                if(isSameTree(subTree.get(i),subTree.get(j))){
                    ans.add(subTree.get(i));
                    break;
                }
            }
        }
        return new ArrayList<>(ans);
    }

    private boolean isSameTree(TreeNode treeNode, TreeNode treeNode1) {
        if(treeNode==null){
            return treeNode1==null;
        }else if(treeNode1==null){
            return false;
        }

        boolean isSame = treeNode.val==treeNode1.val;
        if(!isSame){
            return false;
        }

        boolean left = isSameTree(treeNode.left, treeNode1.left);
        boolean right = isSameTree(treeNode.right,treeNode1.right);
        return left&&right;
    }

    public void getSubTree(TreeNode root,List<TreeNode> subTree){
        if(root==null){
            return ;
        }

        subTree.add(root);
        getSubTree(root.left,subTree);
        getSubTree(root.right,subTree);
    }
}
