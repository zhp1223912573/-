package 链表;

/**
 * @author zhp
 * @date 2022-07-14 14:52
 * https://leetcode.cn/problems/palindrome-linked-list/
 */
public class 回文链表_lc_234 {
    /**
     * 如何不借助额外空间，且时间复杂度为O(1)?
     * 尝试使用快慢指针，快指针走两部，满指针走一部，当快指针走到终点时，慢指针恰好走到中点。
     * 我们将终点的链表反转，在从左右两端（也就是两个链表的头节点）开始遍历验证。
     */

    public boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null){
            return true;
        }

        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        ListNode prepre = null;

        //移动过程中同时反转前半部分链表
        while(fast!=null&& fast.next!=null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            pre.next = prepre;
            prepre = pre;
        }

        //如果此时fast不指向空，说明当前链表的节点个数为奇数个，需要使slow和pre指向不同且相邻的节点
        if(fast!=null){
            slow = slow.next;
        }
        //反转完成，开始回文检查,此时pre与slow毗邻
        while(pre!=null&&slow!=null){
            if(pre.val==slow.val){
                pre = pre.next;
                slow = slow.next;
            }else{
                return false;
            }
        }
        return true;

    }
}
