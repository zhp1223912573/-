package 链表;


import 每日任务.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhp
 * @date 2022-10-22 11:07
 * https://leetcode.cn/problems/list-of-depth-lcci/submissions/
 */
public class ms04_03_特定节点深度链表 {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 层序遍历
     *
     * @param tree
     * @return
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        Queue<TreeNode> queue = new LinkedList();
        queue.add(tree);
        List<ListNode> list = new ArrayList();
        ListNode dummy = new ListNode(-1);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode cur = dummy;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                cur.next = new ListNode(poll.val);
                cur = cur.next;
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
            }
            list.add(dummy.next);
        }
        ListNode[] res = new ListNode[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;

    }
}
