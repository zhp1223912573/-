package 链表;

import 每日任务.ListNode;

import java.util.HashSet;

/**
 * @author zhp
 * @date 2022-10-21 10:40
 * https://leetcode.cn/problems/remove-duplicate-node-lcci/submissions/
 */
public class ms02_01_移除重复节点 {

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * set+顺序遍历
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode pre = null;
        HashSet<Integer> set = new HashSet();
        ListNode cur = head;
        while (cur != null) {
            if (!set.contains(cur.val)) {
                set.add(cur.val);
                pre = cur;
                cur = cur.next;
            } else {
                pre.next = cur.next;
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * 两重循环 时间换空间
     */
    public ListNode removeDuplicateNodes1(ListNode head) {
        ListNode now = head;
        while (now != null) {
            ListNode cur = now;
            while (cur.next != null) {
                if (cur.next.val == now.val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            now = now.next;
        }
        return head;
    }
}
