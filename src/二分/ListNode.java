package 二分;

import java.util.ArrayList;

/**
 * @author zhp
 * @date 2022-07-14 14:26
 */
public class ListNode {
        public static void main(String[] args) {
                ArrayList<? super Number> list = new ArrayList<>();

                list.add(new Integer(1));// 编译正确
                list.add(new Float(1.0));// 编译正确

                // Object 是 Number 的父类
        }
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

}
