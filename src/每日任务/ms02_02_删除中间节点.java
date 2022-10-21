package 每日任务;

/**
 * @author zhp
 * @date 2022-10-21 11:12
 * https://leetcode.cn/problems/delete-middle-node-lcci/submissions/
 */
public class ms02_02_删除中间节点 {
    /**
     * 替换代替删除
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
