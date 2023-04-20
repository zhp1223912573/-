package 树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhp
 * @date 2021-11-27 10:52
 * 二叉树的所有路径
 * 返回所有从根节点到叶子节点的路径
 * https://leetcode.cn/problems/binary-tree-paths/
 */
public class    二叉树所有路径_lc_257 {
    public List<String> binaryTreePaths(TreeNode root) {
        //1.
            List<String> paths= new ArrayList<>();
            constructPaths(root,"",paths);
            return paths;
    }

    /**深度优先
     * dfs
     * @param root
     * @param s
     * @param paths
     */
    private void constructPaths(TreeNode root, String s, List<String> paths) {
        if(root!=null){
            StringBuffer stringBuffer = new StringBuffer(s);
            stringBuffer.append(root.val);
            if(root.right==null && root.left==null){
                paths.add(stringBuffer.toString());
            }else{
                stringBuffer.append("->");
                constructPaths(root.left,stringBuffer.toString(),paths);
                constructPaths(root.right,stringBuffer.toString(),paths);
            }
        }
    }

    /**广度优先
     *出现左右子节点都为空的节点就加入路径即可
     */
    public List<String> constructPathsI(TreeNode root){
        List<String> paths=new ArrayList<>();
        if(root==null) return paths;
        Queue<TreeNode> nodeQueue=new LinkedList<>();
        Queue<String> pathQueue= new LinkedList<>();

        nodeQueue.offer(root);
        pathQueue.offer(Integer.toString(root.val));

        while(!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();

            if(node.right==null && root.left==null){
                paths.add(path);
            }else{
                if(root.left!=null){
                    nodeQueue.offer(root.left);
                    pathQueue.offer(path+"->"+Integer.toString(root.left.val));
                }
                if(root.right!=null){
                    nodeQueue.offer(root.right);
                    pathQueue.offer(path+"->"+Integer.toString(root.right.val));
                }
            }
        }

        return paths;

    }

}
