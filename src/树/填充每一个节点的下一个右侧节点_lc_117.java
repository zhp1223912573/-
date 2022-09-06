package 树;

import sun.java2d.loops.ProcessPath;

/**
 * @author zhp
 * @date 2022-07-22 22:19
 * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii
 */
public class 填充每一个节点的下一个右侧节点_lc_117 {
    /**
     * 每一层的next节点的构建都依赖于上一侧，
     * 而根节点就是已经构建好的一层。
     * @param root
     * @return
     */
    Node last ;//记录遍历的上一个节点，用于连接当前正在遍历的节点
    Node newStart;//每一层的新开头节点
    public Node connect(Node root) {

        if(root==null){
            return null;
        }

        Node start = root;
        while(start!=null){
            //每遍历完一层都要更新上一节点和起始节点，避免上一层尾部和当前层首个节点连接
            last = null;
            newStart = null;
            for(Node p = start;p!=null;p=p.next){
                if(p.left!=null){
                    handle(p.left);
                }

                if(p.right!=null){
                    handle(p.right);
                }
            }
            start = newStart;//更换到下一层
        }

        return root;
    }

    private void handle(Node node) {
        if(last!=null){
            last.next = node;
        }

        //newStart为空说明当前的node就是当前层的首节点
        if(newStart==null){
            newStart = node;
        }

        last = node ;//指向当前node，
    }
}
