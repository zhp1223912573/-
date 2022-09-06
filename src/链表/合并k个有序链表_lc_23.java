package 链表;



import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author zhp
 * @date 2022-07-28 16:37
 * https://leetcode.cn/problems/merge-k-sorted-lists/submissions/
 */
public class 合并k个有序链表_lc_23 {
    /**顺序合并
     * 借用合并两个有序链表_lc_31的代码，合并k个有序链表即可。
     */
    public  ListNode mergeKLists( ListNode[] lists) {
         ListNode preHead = new  ListNode(Integer.MIN_VALUE);
         ListNode pre = preHead;
        for( ListNode list :lists ){
            pre = mergeTwoLists(pre,list);
        }
        return preHead.next;
    }

    /**分治合并
     * 同样复用合并两链表，但是采取分治的思想
     */
    public  ListNode mergeKLists1( ListNode[] lists){
        return merge(lists, 0,lists.length-1);
    }
    private  ListNode merge( ListNode[] lists, int left, int right) {
        if(left==right){
            return lists[left];
        }

        if(left>right){
            return null;
        }

        int mid = (right-left)/2+left;
        return mergeTwoLists(merge(lists,left,mid),merge(lists,mid+1,right));
    }

    //迭代合并两链表
    public  ListNode mergeTwoLists( ListNode list1,  ListNode list2) {
         ListNode preHead = new  ListNode(-1);
         ListNode pre = preHead;

        while(list1!=null&&list2!=null){
            if(list1.val<=list2.val){
                pre.next = list1;
                list1 = list1.next;
            }else{
                pre.next = list2;
                list2 = list2.next;
            }
            pre = pre.next;
        }

        if(list1!=null){
            pre.next = list1;
        }
        if(list2!=null){
            pre.next = list2;
        }

        return preHead.next;
    }
    //递归合并两链表
    public  ListNode mergeTwoLists1( ListNode list1,  ListNode list2){
        if(list1==null){
            return list2;
        }else if(list2==null){
            return list1;
        }else if(list1.val<=list2.val){
            list1.next = mergeTwoLists1(list1.next,list2);
            return list1;
        }else{
            list2.next = mergeTwoLists(list1,list2.next);
            return list2;
        }
    }

    /**优先队列
     *将每个链表的结点加入优先队列，在队列内定义按照节点值大小排序，
     * 取队列首部值即可。
     */
    PriorityQueue< ListNode> pq = new PriorityQueue<>(new Comparator< ListNode>() {
            //按照结点的值大小来排序
            @Override
            public int compare( ListNode o1,  ListNode o2) {
                return o1.val-o2.val;
            }
        });
    public  ListNode mergeKLists2( ListNode[] lists) {
            for( ListNode listNode : lists){
                if(listNode!=null){
                    pq.add(listNode);
                }
            }

             ListNode preHead = new  ListNode(-1);
             ListNode pre = preHead;
            while(!pq.isEmpty()){
                ListNode poll = pq.poll();
                pre.next = poll;
                if(poll.next!=null){
                    poll = poll.next;
                    pq.add(poll);
                }
                pre = pre.next;
            }

            return preHead.next;
    }
}
