package 树;

import shiyan.Queue.LinkedQueue;
import shiyan.Queue.Queue;

/**
 * @author zhp
 * @date 2022-06-12 17:12
 * 二叉树的序列化与反序列化
 */
public class 二叉树序列化与反序列化 {


    /**
     * 二叉树的序列化与反序列只需要在递归或者迭代的过程，统一好序列化的格式，按照指定格式序列化和反序列化即可
     */

    //以先序遍历序列化和反序列化二叉树

    /**
     * 序列化二叉树
     * @param root
     * @return
     */
    public static String serialByPre(TreeNode root){
        if(root==null) return "#_";

        String res =  root.val +"_";
        res += serialByPre(root.left);
        res += serialByPre(root.right);

        return res;
    }

    /**
     * 分解先序遍历字符串，保存到队列中
     * @param preStr
     * @return
     */
    public static TreeNode reconByPreStr(String preStr){
        String split[] = preStr.split("_");
        Queue<String>  queue = new LinkedQueue<>();
        for(String string : split){
            queue.add(string);
        }
            return reserialByPre(queue);
    }

    /**
     * 反序列化
     * @param queue
     * @return
     */
    private static TreeNode reserialByPre(Queue<String> queue) {

        String values = queue.poll();
        if(values.equals("#")){
            return null;
        }


        TreeNode root = new TreeNode(Integer.valueOf(values));
        root.left = reserialByPre(queue);
        root.right = reserialByPre(queue);

        return root;
    }


}
