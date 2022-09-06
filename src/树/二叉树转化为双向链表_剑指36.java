package 树;

/**
 * @author zhp
 * @date 2022-07-05 21:32
 * 双向链表节点结构和二叉树节点结构是一样的，如果你把last认为是left，
 * next认为是next的话。
 * 给定一个搜索二叉树的头节点head，请转化成一条有序的双向链表，并返回链
 * 表的头节点
 */
public class 二叉树转化为双向链表_剑指36 {
    /**
     * 二叉搜索树中序遍历时，返回的节点值有序。
     * 所以我们只需要在中序递归过程中返回左右子树的尾节点，就可以实现转换。
     */
    public static class Info{
        public TreeNode start;
        public TreeNode end;

        public Info(TreeNode start, TreeNode end) {
            this.start = start;
            this.end = end;
        }
    }
    public static TreeNode convert(TreeNode head) {
        if (head == null) {
            return null;
        }
        return BSTtoDoubleLinkedList(head).start;
    }
    public static Info BSTtoDoubleLinkedList(TreeNode root) {
        if(root == null){
            return new Info(null,null);
        }

        //获取包含当前节点左右子树的信息info类
        Info leftTree = BSTtoDoubleLinkedList(root.left);
        Info rightTree = BSTtoDoubleLinkedList(root.right);
        //左子树不为空，将左子树的尾节点与当前节点相连
        if(leftTree.end!=null){
            leftTree.end.right = root;
        }
        root.left = leftTree.end;

        //有子树不为空，将右子树的头节点与当前节点相连
        if(rightTree.start!=null){
            rightTree.start.left = root;
        }
        root.right = rightTree.start;
        //按上述步骤就将需要当前节点的左子树和右子树连接成功

        //返回信息,需要返回合并后的树的头与尾节点，因为这个节点就是当前节点的左右子树的头与尾节点
        //所以，如果他们不存在的话，说明当前节点就是头或者尾节点，直接返回即可。
        return new Info(leftTree.start!=null ? leftTree.start :root,
                rightTree.end!=null? rightTree.end:root);
    }

    /**
     * 对于二叉搜索树，中序遍历得到的结果就是我们想要构造的二叉树的样子，
     * 所以，我们模拟中序遍历的递归过程，将前后结点连接即可。
     */
    static TreeNode pre ;//记录前一个遍历的结点
    static TreeNode head;//记录头节点
    public static TreeNode convert1(TreeNode root){
        pre = null;
        inorder(root);
        pre.right = head;
        head.left = pre;
        return head;

    }

    public static void inorder(TreeNode cur){
        if(cur==null){
            return ;
        }

        inorder(cur.left);
        if(pre!=null){
            pre.right = cur;
        }else{
            head = cur;
        }
        cur.left = pre;
        pre = cur;
        inorder(cur.right);

    }
}
