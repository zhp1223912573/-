package 链表;

import 每日任务.ListNode;

/**
 * @author zhp
 * @date 2022-10-21 11:12
 * https://leetcode.cn/problems/delete-middle-node-lcci/submissions/
 */
public class ms02_02_删除中间节点 {

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    /**
     * 替换代替删除
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
