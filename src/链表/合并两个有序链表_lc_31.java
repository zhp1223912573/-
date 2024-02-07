package 链表;


/**
 * @author zhp
 * @date 2022-07-14 14:22
 * https://leetcode.cn/problems/merge-two-sorted-lists/
 */
public class 合并两个有序链表_lc_31 {
    /**
     * dummy node技巧运用
     * 使用一个prehead头节点，方便返回，
     * 添加一个pre节点，比较list1与list2的较小值，
     * pre代表的前一节点链接较小的哪一个节点。
     * list1和list2两个链表长度可能不一致，需要添加可能遗漏的节点。
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //添加一个头节点，便于返回
        ListNode prehead = new ListNode(-1);

        ListNode pre = prehead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                pre.next = list1;
                list1 = list1.next;
            } else {
                pre.next = list2;
                list2 = list2.next;
            }
            pre = pre.next;
        }

        pre.next = list1 == null ? list2 : list1;

        return prehead.next;
    }

    /**
     * 递归
     * 画图理解递归过程
     *
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode mergeTwoList(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoList(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoList(list1, list2.next);
            return list2;
        }
    }


}
