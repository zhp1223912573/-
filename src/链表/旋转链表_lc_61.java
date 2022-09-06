package 链表;


/**
 * @author zhp
 * @date 2022-07-18 12:49
 * https://leetcode.cn/problems/rotate-list/
 */
public class 旋转链表_lc_61 {
    /**
     * 让链表成为闭环，同时计算结点个数，
     * 再次从开头移动n-k-1个结点，到达新头节点的前面结点pre，
     * 记录新头结点，让pre.next为空，返回新头节点即可。
     * @param head
     * @param k
     * @return
     */
    public   ListNode rotateRight(  ListNode head, int k) {
        if(head==null){
            return head;
        }

          ListNode cur = head;
        int count = 1;
        while(cur.next!=null){
            count++;
            cur = cur.next;
        }
        //让链表连成一个环
        cur.next = head;
        cur = head;
        k=k%count;
        for(int i=0;i<count-k-1;i++){
            cur = cur.next;
        }
        ListNode next = cur.next;
        cur.next = null;
        return next;
    }
}
