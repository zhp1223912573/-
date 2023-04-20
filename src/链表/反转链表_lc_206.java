package 链表;


/**
 * @author zhp
 * @date 2022-07-14 13:08
 * https://leetcode.cn/problems/reverse-linked-list/
 */
public class 反转链表_lc_206 {
    /**
     * 利用dummy node节点技巧，为链表加一个头节点，递归反转
     */


    /**
     * 迭代
     * @param head
     * @return
     */
    public  static ListNode reverseList(ListNode head) {

                if(head==null){
                    return null;
                }
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
     * 递归
     * 递归过程为了返回新的头节点需要接受反转前的尾节点，并在递归收缩的过程中回传。
     * 1->2->3->4->5->6->null
     * 递归到5时，由于当前节点的后一节点为空，当前节点就是新的头节点，
     * 也就是在节点5开始回收，回到节点4时，设置节点5的下一节点为当前节点4，
     * 当前节点的下一节点设置为null，继续回收
      * @param head
     * @return
     */
    public static ListNode reverseList1(ListNode head){
        //到达尾节点，也就是反转后的新头结点
       if(head==null||head.next==null){
           return head;
       }

       //传递新的头结点，也就链表未反转时的尾节点
       ListNode newHead = reverseList1(head.next);
       head.next.next = head;
       head.next = null;
       return newHead;



    }

}
