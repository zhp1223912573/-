package 链表;


/**
 * @author zhp
 * @date 2022-07-18 1:07
 * https://leetcode.cn/problems/odd-even-linked-list/
 */
public class 奇偶链表_lc_328 {
    /**
     * 双指针
     * 奇偶指针，先链接一个奇结点，再链接一个偶结点，
     * 最后将两个结点拼接
     */
    public  ListNode oddEvenList( ListNode head) {
        if(head==null||head.next==null){
            return head;
        }

        //奇结点指针
         ListNode odd = head;
        //最后一个奇结点，链接奇链表和偶链表
         ListNode lastodd=odd;
        //偶结点指针
         ListNode even = head.next;
        //第一个偶结点，链接奇链表和偶链表
         ListNode firsteven = even;
        while(odd!=null&&even!=null){
            odd.next = even.next;
            odd = odd.next;
            if(even==null||odd==null){
                break;
            }
            even.next = odd.next;
            even = even.next;

        }
        //链接
        while(lastodd.next!=null){
            lastodd = lastodd.next;
        }
        lastodd.next = firsteven;
        return head;
    }

    /**
     * 官方解答：与上述函数方法一致，只是代码更加精简
     * @param head
     * @return
     */
    public  ListNode oddEvenList1( ListNode head){
        if(head==null){
            return head;
        }
         ListNode odd =head;
         ListNode even = head.next;
        ListNode evenHead = head.next;

        while(even!=null&&even.next!=null){
            odd = even.next;
            odd = odd.next;
            even.next= odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }
}
