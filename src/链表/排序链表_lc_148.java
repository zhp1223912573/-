package 链表;


import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @author zhp
 * @date 2022-07-28 22:45
 * https://leetcode.cn/problems/sort-list/
 */
public class 排序链表_lc_148 {

    /**优先队列
     * 借助额外数据结构并使用n级别的额外空间
     * @param head
     * @return
     */
    public    ListNode sortList(   ListNode head) {
        PriorityQueue<   ListNode> pq = new PriorityQueue<>((o1, o2)->o1.val-o2.val);
           ListNode preHead = new    ListNode(-1);

           ListNode pre = preHead;
        while(head!=null){
            pq.add(new    ListNode(head.val));
            head = head.next;
        }

        Iterator<   ListNode> iterator = pq.iterator();
        while(!pq.isEmpty()){
               ListNode poll = pq.poll();
            pre.next = poll;
            pre = pre.next;
        }
        return preHead.next;
    }


    /**
     * 自顶向下的归并排序
     * 时间：O(nlogn)
     * 空间：O(logn)，其中 n是链表的长度。空间复杂度主要取决于递归调用的栈空间。
     */
    class Solution {
        public    ListNode sortList(   ListNode head) {
            return sortList(head, null);
        }

        public    ListNode sortList(   ListNode head,    ListNode tail) {
            if (head == null) {
                return head;
            }
            if (head.next == tail) {
                head.next = null;
                return head;
            }
               ListNode slow = head, fast = head;
            while (fast != tail) {
                slow = slow.next;
                fast = fast.next;
                if (fast != tail) {
                    fast = fast.next;
                }
            }
               ListNode mid = slow;
               ListNode list1 = sortList(head, mid);
               ListNode list2 = sortList(mid, tail);
               ListNode sorted = merge(list1, list2);
            return sorted;
        }

        public    ListNode merge(   ListNode head1,    ListNode head2) {
               ListNode dummyHead = new    ListNode(0);
               ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
            while (temp1 != null && temp2 != null) {
                if (temp1.val <= temp2.val) {
                    temp.next = temp1;
                    temp1 = temp1.next;
                } else {
                    temp.next = temp2;
                    temp2 = temp2.next;
                }
                temp = temp.next;
            }
            if (temp1 != null) {
                temp.next = temp1;
            } else if (temp2 != null) {
                temp.next = temp2;
            }
            return dummyHead.next;
        }
    }

    /**自底向上的归并排序
     *https://leetcode.cn/problems/sort-list/solution/pai-xu-lian-biao-by-leetcode-solution/
     * 计算出链表结点个数，先将链表分为1个每组，接着两个每组，四个每组。。。直到所有结点都被排序成功，
     *
     * @param head
     * @return
     */
    public    ListNode sortList1(   ListNode head) {
            if(head==null){
                return null;
            }
            //计算结点个数
            int length = 0;
               ListNode node = head;
            while(node!=null){
                length++;
                node = node.next;
            }

            //添加虚拟头节点，便于头结点的插入返回等操作。
               ListNode dummy = new    ListNode(-1);
            dummy.next = head;

            //开始自底向上归并排序
            for(int subLength = 1;subLength<length;subLength<<=1){
                   ListNode pre = dummy;
                   ListNode cur = dummy.next;
                while(cur!=null){
                    //记录第一个分组的头节点
                       ListNode head1 = cur;
                    //扩展第一分组的结点数到当前每组结点数sublength
                    for(int i =1;i<subLength&&cur.next!=null;i++){
                         cur = cur.next;
                    }
                    //记录第二分组的头节点
                       ListNode head2 = cur.next;
                    cur.next = null;//分开第一分组和第二分组，直到最后再链接起来
                    cur = head2;//重新指向第二分组的头节点
                    //同上，扩展第二分组的结点数到当前每组节点数sublength
                    for(int i=1;i<subLength&&cur!=null&&cur.next!=null;i++){
                        cur = cur.next;
                    }
                    //记录下一伦次的第一分组的头节点
                       ListNode next = null;
                    if(cur!=null){//如果当前第二分组的最后一个结点不为空，尝试获取下一伦次的第一分组的头节点，并且将当前第二分组的尾结点指空
                        next = cur.next;
                        cur.next = null;
                    }

                    //得到了第一二分组，可以合并了
                       ListNode merged = merge(head1,head2);
                    //pre是前一次组合后的尾巴结点，首轮的尾结点就是我们设置的虚拟结点，次轮开始的尾结点都是上一轮第二组的尾巴结点
                    pre.next = merged;//连接不同伦次排序的组合
                    //pre需要更新位当前合并后链表的尾巴结点
                    while(pre.next!=null){
                        pre = pre.next;
                    }
                    cur = next;//指向新伦次的第一组的开头
                }
            }
            return dummy.next;
   }

    public    ListNode merge(   ListNode head1,    ListNode head2) {
           ListNode dummyHead = new    ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }
}
