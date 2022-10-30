package 链表;

import 每日任务.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhp
 * @date 2022-10-18 11:22
 * https://leetcode.cn/problems/LGjMqU/
 */
public class 重排链表_offerII026 {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    /**
     * 将链表节点保存到有序数组内，使用双指针根据题意要求重新连接即可完成
     * 需要额外空间保存节点，但简单，易于实现。
     * @param head
     */
    public void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while(cur!=null){
            list.add(cur);
            cur = cur.next;
        }

        //双指针指向list首尾
        int l =0,r=list.size()-1;
        while(l<r){
            list.get(l).next = list.get(r);
            l++;
            if(l==r) break;
            list.get(r).next = list.get(l);
            r--;
        }
        //最后要记得把原先中间节点next置为null，因为该节点已经被转化为最后节点
        list.get(l).next=null;

    }

    /**
     * 如何在空间O（1）的情况下实现？
     * 原地反转，快慢指针找到中间节点，把链表的后半段进行反转，
     * 再和上述题解类似，从链表的两端开始遍历，直到相遇，遍历过程组实现题意的连接要求。
     */
    public void reorderList1(ListNode head){
        ListNode middle = findMiddle(head);
        ListNode r = reverseList(middle.next);
        ListNode l = head;
        while(l!=null){
            ListNode lnext = l.next;
            l.next = r;
            l = lnext;
            //r为空，说明右半边已经遍历完，并且上述的最后节点的next也置为null，直接返回即可
            if(r==null) break;
            ListNode rnext = r.next;
            r.next = l;
            r = rnext;
        }
    }

    //反转链表
    public ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //快慢指针找中节点
    public ListNode findMiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null&&fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
