package shiyan.Search;

import shiyan.In;
import shiyan.Queue.LinkedQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhp
 * @date 2021-12-23 21:47
 * 二分查找符号表
 */
public class BinarySearchST<Key extends Comparable<Key>,Value> {
    private Key[] keys;
    private Value[] values;
    private int n;//元素个数

    public static void main(String[] args) {
        BinarySearchST<String, Integer> st=new BinarySearchST<>(10);
        st.put("A",1);
        st.put("D",4);
        st.put("C",5);
        st.put("E",7);
        st.put("V",2);
        st.put("O",0);
        st.put("M",9);
        st.put("P",11);
        st.put("N",100);
        st.put("I",1);
        st.put("M",11);
        st.put("X",123);
        st.delete("A");
        Iterable<String> keys = st.keys();
        for(String s: keys){
            Integer integer = st.get(s);
            System.out.println(s+" "+integer);
        }
        System.out.println(st.size());

    }

    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
;    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return n==0;
    }

    public Value get(Key key){
        if(isEmpty()) return null;
        int i=rank(key);
        if(i<n&&keys[i].compareTo(key)==0) return values[i];
        return null;
    }

    public void put(Key key,Value value){
        //查找key在keys中的位置
        int index=rank(key);
        //若index在数组key中更换其value
        if(index<n&&keys[index].compareTo(key)==0){
            values[index]=value;
            return;
        }

        //如果keys的大小等于n则进行扩容
        if(n==keys.length){
            resize(2*n);
        }

        //若不在 则创建一个新的key 并将index位置及其后的数值向后移动 为新的key腾出空间
        for(int i=n;i>index;i--){
            keys[i]=keys[i-1];
            values[i]=values[i-1];
        }
        //移动成功后 加入新的key及其value
        keys[index]=key;
        values[index]=value;
        //更新元素个数
        n++;

    }

    /**
     * 删除key元素
     * @param key
     */
    public void delete(Key key){
        int index=rank(key);
        //要删除的key不在符号表内
        if(index>=n&&keys[index].compareTo(key)!=0) return;
        //将要删除元素的坐标index后的元素向前移动 起到删除的操作
        for(int i=index;i<n-1;i++){
            keys[i]=keys[i+1];
            values[i]=values[i+1];
        }
        n--;
        keys[n]=null;
        values[n]=null;

    }

    public void resize(int newCapacity){
        //确保扩容后尺寸大于原本的容量
        assert newCapacity>n;
        Key[] tempk = (Key[]) new Comparable[newCapacity];
        Value[] tempv=(Value[]) new Object[newCapacity];
        for(int i=0;i<n;i++){
            tempk[i]=keys[i];
            tempv[i]=values[i];
        }
        keys=tempk;
        values=tempv;

    }

    /**
     * 二分法查找索引key在keys数组中的坐标
     * @param key
     * @return
     */
    public int rank(Key key){
        int low =0;
        int high=n-1;
        while(high>=low){
            int mid=low+(high-low)/2;
            if(keys[mid].compareTo(key)<0) low=mid+1;
            else if(keys[mid].compareTo(key)>0) high=mid-1;
            else return mid;
        }
        return low;
    }

    public Iterable<Key> keys(){
        return keys(keys[0],keys[n-1]);
    }

    public Iterable<Key> keys(Key low, Key high){
        Queue<Key> list = new LinkedList<Key>();
        int i=rank(low);
        int j=rank(high);
        if(j<i) return null;
        for(int x=i;i<=j;i++){
            list.add(keys[i]);
        }
        return list;
    }
}
