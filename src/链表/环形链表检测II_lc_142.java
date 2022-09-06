package 链表;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author zhp
 * @date 2022-07-17 23:10
 * https://leetcode.cn/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode-solution/
 */
public class 环形链表检测II_lc_142 {
    /**快慢指针遍历
     * 规律推导
     * 设起始节点到环入口节点的距离为a
     *   环入口到相遇节点的距离为b
     *   相遇节点到环入口的距离为c
     *
     * 则快指针运动的路程为：a+b+n*(b+c) n表示在两者相遇之前，快指针以及在环内移动的圈数
     *  慢指针运动的路程为：a+b
     *  两者的路程之比为2：1
     *  则 2*（a+b） = a+b+n*(b+c) ==> a = (n-1)*(b+c) + c
     *  也就是初始节点到入口节点的距离为
     *  从相遇点到入环点的距离加上 n-1n−1 圈的环长，恰好等于从链表头部到入环点的距离。
     *  因此，当发现 slow 与 fast 相遇时，我们再额外使用一个指针 ptr。
     *  起始，它指向链表头部；随后，它和 slow 每次向后移动一个位置。最终，它们会在入环点相遇
     */
    public     ListNode detectCycle1(    ListNode head){
        if(head==null||head.next==null){
            return null;
        }
            ListNode fast = head;
            ListNode slow = head;
        while(fast!=null){
            slow = slow.next;
            if(fast.next!=null){
                fast = fast.next.next;
            }else{
                return null;
            }

            if(fast==slow){
                    ListNode ptr = head;
                int a = 0;
                while(slow!=ptr){
                    a++;
                    slow = slow.next;
                    ptr = ptr.next;
                }
                return ptr;
            }
        }
        return null;
    }


    /**
     * 保存遍历过的节点，第一个重复遍历的节点就是入口节点
     */
    public     ListNode detectCycle(    ListNode head) {
        HashSet<    ListNode> set = new HashSet<>();
        ListNode ptr = head;
        while(ptr!=null){
            if(set.contains(ptr)){
                return ptr;
            }else{
                set.add(ptr);
            }
            ptr = ptr.next;
        }
        return null;
    }
}
