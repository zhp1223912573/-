package 每日任务;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhp
 * @date 2022-07-31 0:48
 */
public class LRUCache实现_lc_146 {
    //双向结点
    class DLinkedNode{
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        DLinkedNode(){};
        DLinkedNode(int key,int value){
            this.key = key;
            this.value = value;
        }
    }
    //记录标号与节点的映射关系
    Map<Integer,DLinkedNode> cache = new HashMap<>();
    //双向链表伪头尾结点
    DLinkedNode head;
    DLinkedNode tail;
    //现有结点个数
    int size;
    //容量
    int capacity;

    LRUCache实现_lc_146(int capacity){
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        //得到结点
        DLinkedNode node = cache.get(key);
        if(node==null){
            return -1;//结点不存在
        }
        //将当前结点移动到双向链表首部
        moveToHead(node);
        return node.value;

    }

    public void put(int key, int value) {
        //查看放入key是否已经存在
        DLinkedNode node = cache.get(key);

        if(node==null){
            //不存在，创建新结点
            DLinkedNode newnode = new DLinkedNode(key,value);
            //放入内存
            cache.put(key,newnode);
            //此处为添加到链表首部
            addToHead(newnode);
            ++size;//更新此时cache内结点的数量
            if(size>capacity){
                //移除尾部结点
                DLinkedNode remove = removeTail();
                cache.remove(remove.key);
                --size;
            }
        }else{
            //存在则更新结点值
            node.value = value;
            //重新移动到双向链表首部
            moveToHead(node);
        }

    }

    //将node结点添加到头部
    private void addToHead(DLinkedNode node){
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
    }

    private void removeNode(DLinkedNode node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail(){
        DLinkedNode  res = tail.prev;
        removeNode(res);
        return res;
    }


}
