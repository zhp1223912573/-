package 链表;


/**
 * @author zhp
 * @date 2022-07-17 22:43
 * https://leetcode.cn/problems/linked-list-cycle/submissions/
 */
public class 环形链表检测_lc_141 {
    /**
     * 快慢指针
     * @param head
     * @return
     */
    public boolean hasCycle(   ListNode head) {
           ListNode slow = head;
        ListNode fast = head;
        while(fast!=null&&fast.next!=null){

            fast=fast.next.next;
            slow = slow.next;
            if(fast==slow){
                return true;
            }
        }
        return false;
    }
}
