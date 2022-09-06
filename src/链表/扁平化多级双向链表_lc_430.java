package 链表;

import 二分.Node;

/**
 * @author zhp
 * @date 2022-07-18 12:10
 * https://leetcode.cn/problems/flatten-a-multilevel-doubly-linked-list/submissions/
 */
public class 扁平化多级双向链表_lc_430 {

    /**
     * 递归模拟
     * 遇到有子孩子的结点，就递归其子孩子结点为头结点的链表，
     * 在递归函数中完成链表的压缩
     */
    public 二分.Node flatten(二分.Node head) {
        if(head==null){
            return null;
        }
        二分.Node cur = head;
        while(cur!=null){
            if(cur.child!=null){
                二分.Node next = cur.next;
                cur.next = flatten(cur.child);
                cur.child = null;
                cur.next.prev = cur;
                二分.Node last = getLastNode(cur.next);
                last.next = next;
                if(next!=null)
                    next.prev = last;

            }
            cur = cur.next;
        }

        return head;
    }

    public 二分.Node getLastNode(Node head){
        while(head.next!=null){
            head = head.next;
        }
        return head;
    }
}
