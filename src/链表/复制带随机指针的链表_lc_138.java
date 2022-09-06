package 链表;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhp
 * @date 2022-07-18 12:20
 * https://leetcode.cn/problems/copy-list-with-random-pointer/
 */
public class 复制带随机指针的链表_lc_138 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    /**
     * hashmap保存所有原始结点和其对应结点的复制，再遍历整个map，
     * 根据原始结点来构造复制结点的链表
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        Node cur = head;
        HashMap<Node,Node> map = new HashMap<>();
        while(cur!=null){
            Node copy = new Node(cur.val);
            map.put(cur,copy);
            cur = cur.next;

        }

        cur = head;
        while(cur!=null){
            Node copyNext =  map.get(cur.next);
            Node copyRandom = map.get(cur.random);
            Node copy = map.get(cur);
            copy.next = copyNext;
            copy.random = copyRandom;
            cur = cur.next;
        }

//        for(Map.Entry<Node,Node> entry : map.entrySet()){
//            Node key = entry.getKey();
//            Node value = entry.getValue();
//            if(key.random!=null){
//                Node copyRandom = map.get(key.random);
//                value.random = copyRandom;
//            }
//            if(key.next!=null){
//                Node copyNext = map.get(key.next);
//                value.next = copyNext;
//            }
//        }
        return map.get(head);

    }
}
