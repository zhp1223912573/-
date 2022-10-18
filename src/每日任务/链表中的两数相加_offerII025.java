package 每日任务;

import java.util.Stack;

/**
 * @author zhp
 * @date 2022-10-18 11:01
 * https://leetcode.cn/problems/lMSNwu/submissions/
 */
public class 链表中的两数相加_offerII025 {


    // Definition for singly-linked list.
    class ListNode {
         int val;
         ListNode next;
         ListNode() {}
          ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    /**
     * 利用辅组栈先进后出的特性实现反向相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //栈
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        ListNode tmp = l1;
        while(tmp!=null){
            stack1.push(tmp.val);
            tmp = tmp.next;
        }

        tmp = l2;
        while(tmp!=null){
            stack2.push(tmp.val);
            tmp = tmp.next;
        }

        int jin  = 0 ;
        ListNode pre = null;
        while(!stack1.isEmpty()||!stack2.isEmpty()||jin!=0){
            int v1 = stack1.isEmpty()?0:stack1.pop();
            int v2 = stack2.isEmpty()?0:stack2.pop();
            int cur = v1+v2+jin;
            jin = cur/10;
            cur %=10;
            ListNode now = new ListNode(cur);
            now.next = pre;
            pre = now;
        }
        return pre;
    }

    /**
     * 反转链表
     */
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

    /**
     * 反转链表，再顺序相加即可
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1,ListNode l2){
        //原地反转链表
        ListNode rl1 = reverseList(l1);
        ListNode rl2 = reverseList(l2);
        int carry = 0;
        ListNode newHead = null;
        while(rl1!=null||rl2!=null||carry!=0){
            int v1 = rl1==null?0:rl1.val;
            int v2 = rl2==null?0:rl2.val;
            int now = v1+v2+carry;
            int cur = now%10;
            carry = now/10;
            ListNode node = new ListNode(cur);
            node.next = newHead;
            newHead = node;
            if(rl1!=null)
                rl1 = rl1.next;
            if(rl2!=null)
                rl2 = rl2.next;
        }
        return newHead;
    }
}
