package 树;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhp
 * @date 2022-10-23 10:37
 * https://leetcode.cn/problems/path-sum-iii/
 */
public class 路径总和III_lc_437 {

    /**
     * 递归求解
     * 以每个节点为根节点，递归向下，找到符合targtesum的路径就+1
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        if(root==null) return 0;

        //以当前节点为根节点
        int ret = rootPath(root,targetSum);

        //以当前根节点的子节点为根节点
        ret+=pathSum(root.left,targetSum);
        ret+=pathSum(root.right,targetSum);

        return ret;
    }

    //需要将int型target转化为long型，这样才能避免官方样例的溢出
    private int rootPath(TreeNode root, long target) {
        if(root==null) return 0;

        int ret = 0;
        if(root.val==target){
            ret++;
        }


        ret+=rootPath(root.left,target-root.val);
        ret+=rootPath(root.right,target-root.val);
        return ret;
    }


    /**
     * 前缀和
     * https://leetcode.cn/problems/path-sum-iii/solution/lu-jing-zong-he-iii-by-leetcode-solution-z9td/
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum1(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<Long, Integer>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = 0;
        curr += root.val;

        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;
    }

}
