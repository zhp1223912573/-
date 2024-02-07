package 链表;


import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhp
 * @date 2022-07-18 0:30
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class 删除链表的倒数第N个结点_lc_19 {
    /**
     * 暴力法
     * 两次遍历，一次记录节点数，第二次根据节点数与需要的倒数第N个进行遍历即可
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        int len = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < len - n + 1; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    /**
     * 快慢指针
     * 这里需要添加一个伪头节点，方便处理。
     * 快慢指针先指向伪头节点。
     * 快指针先走n步，慢指针再和快指针一起走，
     * 当快指针到达末尾时，慢指针恰好走到倒数第n个节点的前结点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
            if (fast == null) {
                return null;
            }
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * 栈
     * 压入所有结点，再弹出n个，则最后弹出的就是要删除的结点，栈顶结点就是该删除结点的前置结点
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        Deque<ListNode> stack = new LinkedList<>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode pre = stack.peek();
        pre.next = pre.next.next;
        return dummy.next;
    }
}
