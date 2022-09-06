package 链表;


/**
 * @author zhp
 * @date 2022-07-28 23:15
 * https://leetcode.cn/problems/insertion-sort-list/
 */
public class 对链表进行插入排序_lc_147 {
    /**插入排序
     * https://leetcode.cn/problems/insertion-sort-list/solution/dui-lian-biao-jin-xing-cha-ru-pai-xu-by-leetcode-s/
     * @param head
     * @return
     */
    public    ListNode insertionSortList(   ListNode head) {
        if(head==null){
            return null;
        }
        //添加虚拟头节点，方便头节点前的值插入
           ListNode dummy = new    ListNode(0);
        dummy.next = head;
        //lastSorted表示有序链表的最后一个结点，cur表示即将插入的结点，两者相邻
           ListNode lastSorted = head,cur = head.next;
        while(cur!=null){
            //如果有序链表的最后一个结点的值小于即将插入的结点，说明不需要更改位置
            if(lastSorted.val<=cur.val){
                lastSorted = lastSorted.next;
            }else{//找到cur的结点的的前置结点pre的位置
                ListNode pre =dummy;
                while(pre.next.val<=cur.val){
                    pre = pre.next;
                }
                //找到cur结点插入位置的前去结点
                lastSorted.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
            }
            //cur指向下一个要插入的结点
            cur = lastSorted.next;
        }
        return dummy.next;
    }
}
