package 链表;


/**
 * @author zhp
 * @date 2022-07-18 11:32
 * https://leetcode.cn/problems/add-two-numbers/
 */
public class 两数相加_lc_2 {
    /**
     * 模拟
     * @param l1
     * @param l2
     * @return
     */
    ListNode addTwoNumbers(    ListNode l1,     ListNode l2) {
            ListNode head = null;
            ListNode tail = null;
        int carry = 0;
        while(l1!=null || l2!=null){
            int n1 = l1==null? 0:l1.val;
            int n2 = l2==null? 0:l2.val;
            int sum = carry+n1+n2;
            if(head==null){
                head = tail = new     ListNode(sum%10);
            }else{
                tail.next = new     ListNode(sum%10);
                tail = tail.next;
            }
            carry = sum/10;
            if(l1!=null){
                l1 = l1.next;
            }
            if(l2!=null){
                l2 = l2.next;
            }
        }
        if(carry>0){
            tail.next = new ListNode(carry);
        }

        return head;

    }
}
