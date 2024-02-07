package 链表;


import java.util.HashSet;

/**
 * @author zhp
 * @date 2022-07-17 23:29
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/
 */
public class 相交链表_lc_160 {
    /**
     * 集合保存法
     * 保存所有途径节点，出现的第一个相同节点为相交节点
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode ptr = headA;
        while (ptr != null) {
            set.add(ptr);
            ptr = ptr.next;
        }

        ptr = headB;
        while (ptr != null) {
            if (set.contains(ptr)) {
                return ptr;
            }
        }
        return null;
    }

    /**
     * 规律
     * 是否相交？
     * 两链表若相交，则其末尾节点一定一致。
     * 如果确定两链表的相交节点？
     * 计数两链表的节点个数，分别为a，b
     * 共享的节点为c，
     * 则非共享节点数为a-c，b-c
     * 则非共享节点的差值为d = a-c - （b-c) = a-b (假定a>b)
     * 让长链表的指针先移动d位，再同时移动长短链表上的指针，则相遇时在相交节点上。
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode ptr1 = headA;
        ListNode ptr2 = headB;
        int alen = 0;
        int blen = 0;
        while (ptr1 != null) {
            alen++;
            if (ptr1.next == null) {
                break;
            }
            ptr1 = ptr1.next;
        }
        while (ptr2 != null) {
            blen++;
            if (ptr2.next == null) {
                break;
            }
            ptr2 = ptr2.next;

        }
        if (ptr1 != ptr2) {
            return null;
        }

        int c = Math.abs(alen - blen);

        ptr1 = alen < blen ? headB : headA;
        ptr2 = alen >= blen ? headB : headA;
        for (int i = 0; i < c; i++) {
            ptr1 = ptr1.next;
        }

        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return ptr1;
    }


    /**
     * 官方规律
     * 思路和算法
     * <p>
     * 使用双指针的方法，可以将空间复杂度降至 O(1)O(1)。
     * <p>
     * 只有当链表 headA 和 headB 都不为空时，两个链表才可能相交。
     * 因此首先判断链表 headA 和 headB 是否为空，如果其中至少有一个链表为空，则两个链表一定不相交，返回 null。
     * <p>
     * 当链表 headA 和 headB 都不为空时，创建两个指针 pA 和 pB，初始时分别指向两个链表的头节点 headA 和 headB，
     * 然后将两个指针依次遍历两个链表的每个节点。具体做法如下：
     * <p>
     * 每步操作需要同时更新指针 pA 和 pB。
     * <p>
     * 如果指针 pA 不为空，则将指针 pA 移到下一个节点；如果指针 pB 不为空，则将指针 pB 移到下一个节点。
     * <p>
     * 如果指针 pA 为空，则将指针 pA 移到链表 headB 的头节点；如果指针 pB 为空，则将指针 pB 移到链表 headA 的头节点。
     * <p>
     * 当指针 pA 和 pB 指向同一个节点或者都为空时，返回它们指向的节点或者 null。
     * <p>
     * 证明
     * <p>
     * 下面提供双指针方法的正确性证明。考虑两种情况，第一种情况是两个链表相交，第二种情况是两个链表不相交。
     * <p>
     * 情况一：两个链表相交
     * <p>
     * 链表 headA 和 headB 的长度分别是 m 和 n。假设链表 headA 的不相交部分有 a 个节点，链
     * 表 headB 的不相交部分有 b 个节点，两个链表相交的部分有 cc 个节点，则有 a+c=m ,b+c=n。
     * <p>
     * 如果 a=b，则两个指针会同时到达两个链表相交的节点，此时返回相交的节点；
     * <p>
     * 如果 a != b
     * 则指针 pA 会遍历完链表 headA，
     * 指针 pB 会遍历完链表 headB，两个指针不会同时到达链表的尾节点，然后指针 pA 移到链表 headB 的头节点，
     * 指针 pB 移到链表 headA 的头节点，然后两个指针继续移动，在指针 pA 移动了 a+c+b 次、
     * 指针 pB 移动了 b+c+a 次之后，两个指针会同时到达两个链表相交的节点，该节点也是两个指针第一次同时指向的节点，此时返回相交的节点。
     * <p>
     * 情况二：两个链表不相交
     * <p>
     * 链表 headA 和 headB 的长度分别是 m 和 n。考虑当 m=n 和 m !=n时，两个指针分别会如何移动：
     * <p>
     * 如果 m=n，则两个指针会同时到达两个链表的尾节点，然后同时变成空值 null，此时返回 null；
     * <p>
     * 如果 m !=n，
     * 则由于两个链表没有公共节点，两个指针也不会同时到达两个链表的尾节点，因此两个指针都会遍历完两个链表，
     * 在指针 pA 移动了 m+n 次、指针 pB 移动了 n+m 次之后，两个指针会同时变成空值 null，此时返回 null。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/solution/lian-biao-xiang-jiao-by-leetcode-solutio-2kne/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pa = headA;
        ListNode pb = headB;

        while (pa != pb) {
            pa = pa == null ? headB : pa.next;
            pb = pb == null ? headA : pb.next;
        }
        return pa;
    }

}
