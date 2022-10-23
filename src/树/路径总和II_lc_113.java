package 树;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhp
 * @date 2022-10-23 3:51
 * https://leetcode.cn/problems/path-sum/submissions/
 */
public class 路径总和II_lc_113 {

    /**
     * dfs求解路径总和
     */
    List<List<Integer>> ans ;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        ans = new ArrayList();
        List<Integer> path = new ArrayList();
        getAllPath(root,targetSum,path);
        return ans;
    }

    public void getAllPath(TreeNode root,int target,List<Integer>path){
        if(root==null) return;

        path.add(root.val);
        if((target-root.val)==0&&root.left==null&&root.right==null) ans.add(new ArrayList(path));
        getAllPath(root.left,target-root.val,path);
        getAllPath(root.right,target-root.val,path);
        path.remove(path.size()-1);
    }
}
