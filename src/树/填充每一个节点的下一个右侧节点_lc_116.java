package 树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhp
 * @date 2022-07-22 16:16
 * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/submissions/
 */
public class 填充每一个节点的下一个右侧节点_lc_116 {


    /**
     * 广搜
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if(root==null){
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            Node next = null;
            for(int i=0;i<size;i++){
                Node cur = queue.poll();
                cur.next = next;
                next = cur;
                if(cur.right!=null){
                    queue.add(cur.right);
                }
                if(cur.left!=null){
                    queue.add(cur.left);
                }
            }
        }
        return root;
    }


    /**
     * 空间开销为O(1)的递归和迭代实现
     * 分析题意，该树为完美二叉树，则属于同一节点的左右两孩子可以直接相连，
     * 关键问题在于不同父母的相邻孩子如何相连？
     *  画图观察可发现，当当前要连接的节点的父母节点拥有下一个节点next，那么
     *      该父母节点的右孩子的相邻节点也就是next指针指向的节点，就是父母节点的兄弟节点的孩子，
     *      又因为此树是完美二叉树，则该父母节点的兄弟节点一定有左孩子，那么将当前父母节点的
     *      右孩子同父母节点的兄弟节点的左孩子相连即可。
     */
    public Node connect1(Node root){
        if(root==null){
            return null;
        }

        if(root.left!=null){
            root.left.next=root.right;
        }
        if(root.right!=null&&root.next!=null){
            root.right.next = root.next.left;
        }

        connect1(root.left);
        connect1(root.right);


        return root;
    }

    /**
     * 迭代的实现和递归基本一致
     * @param root
     * @return
     */
    public Node connect2(Node root){
        Node leftmost = root;
        //每一层都从最左边的节点开始
        while(leftmost.left!=null){
            //设置头节点，开始对每个节点的孩子进行连接操作
            Node head = leftmost;
            while(head!=null){
                //当前节点的左右孩子相连
                head.left.next = head.right;
                if(head.next!=null){
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftmost = leftmost.left;
        }
        return root;
    }



}
