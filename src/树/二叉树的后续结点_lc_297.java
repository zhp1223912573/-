package 树;

/**
 * @author zhp
 * @date 2022-06-12 16:37
 * 在二叉树中找到一个节点的后继节点
 * 后继节点，二叉树中序遍历后目的节点的后一个节点
 */
public class 二叉树的后续结点_lc_297 {
    /*
    *三叉节点，多一个指向父亲的引用
    * */
    class Node{
        Node left;
        Node right;
        Node parent;
        int  value;
    }

    /**
     *  在二叉树中找到一个节点的后继节点
     *  后继节点，二叉树中序遍历后目的节点的后一个节点
     * @param target 要查找后续节点的目标节点
     * @return 目标节点的后续节点
     *
     * 思路：
     * 画图，并分析目标节点在树中的不同情况
     * 1.target有右子树，右子树的最左节点就是后续节点
     * 2.target无右子树，
     * 则向上查找当前节点是否为父节点的左子节点，
     * 是的话后续节点就是父亲节点
     * 不是的话继续向上找父亲节点，直到找到一个节点是父亲节点的左子节点，说明当前节点就是目标节点的后续节点
     * 3.特殊情况，最后一个无后续节点的节点
     */
    public Node getSuccesor(Node target){
            if(target==null) return null;

            //1.
           if(target.right!=null){
                return getLeftMost(target.right);//获取当前节点右子树的最左节点
            }else{
               Node parent = target.parent;
               //兼顾了2.3情形
               while(parent!=null && parent.left!=target){
                   target = parent;
                   parent = target.parent;
               }
               return parent ;
           }
    }

    //得到当前节点的最左节点
    private Node getLeftMost(Node root) {
        if(root==null) return null;

        while(root.left!=null){
            root = root.left;
        }

        return root;
    }
}
