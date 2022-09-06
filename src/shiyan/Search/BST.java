package shiyan.Search;

import shiyan.In;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhp
 * @date 2021-12-23 22:45
 * 二叉搜索树
 */
    public class BST<Key extends Comparable<Key>,Value> {

        private Node root;//该二叉搜索树的根节点

    public static void main(String[] args) {
        BST<String,Integer>bst= new BST<>();
        bst.put("a",31);
        bst.put("c",31);
        bst.put("a",21);
        bst.put("d",39);
        bst.put("p",1);
        bst.put("o",0);
        bst.put("i",9);
        Integer a = bst.get("a");
        System.out.println(a);
        int size = bst.size();
        String min = bst.min();
        String max = bst.max();
        System.out.println("二叉树中节点个数为"+size);
        System.out.println("最大节点为"+max);
        System.out.println("最小节点为"+min);
        String select = bst.select(2);

        System.out.println("index为2的节点"+select);

        bst.delete("a");
        Integer value=bst.get("a");
        System.out.println(value);
        int size1=bst.size();
        System.out.println("删除a后树的节点个数"+size1);

        Iterable<String> keys = bst.keys();
        for(String s : keys){
            Integer integer = bst.get(s);
            s+=" "+integer;
            System.out.println(s);


        }

    }

    private class Node{
        private Key key;    //键
        private Value value;//值
        private int n;      //以该节点为子节点的树中的节点个数
        private Node left,right;//左右子节点

        public Node(Key key,Value value,int n){
            this.key=key;
            this.value=value;
            this.n=n;
        }
    }

    /**
     * 返回树的节点个数
     * @return
     */
    public int size(){
        return size(root);
    }

    /**
     * 返回以root为根节点为树的节点数
     * @param root
     * @return
     */
    private int size(Node root){
        if(root==null) return 0;
        return root.n;
    }

    /**
     * 获取键为key的值
     * @param key
     * @return
     */
    public Value get(Key key){
        if(key==null) return null;
        return get(root,key);
    }

    private Value get(Node root,Key key){
        if(root==null) return null;
        //保存根节点key与要搜索的key的比较值
        int compare=root.key.compareTo(key);
        //若root.key小于key 向右子树寻找 反之 向左
        if(compare<0) return get(root.right,key);
        else if(compare>0) return get(root.left,key);
        return root.value;
    }

    /**
     * 插入键值key-value
     * @param key
     * @param value
     */
    public void put(Key key,Value value){
        if(key==null || value==null) throw new NullPointerException();
        root=put(root,key,value);
    }

    private Node put(Node root,Key key,Value value){
        //若根节点为空 说明key值不在树中 需要创建并返回一个新节点
        if(root==null) return new Node(key,value,1);
        //获取比较值
        int compare = root.key.compareTo(key);
        if(compare<0) root.right=put(root.right,key,value);
        else if(compare>0) root.left=put(root.left,key,value);
        else root.value=value;
        //更新树的节点总数
        root.n=size(root.left)+size(root.right)+1;
        return root;
    }

    /**
     * 返回bst中的最小节点
     * @return
     */
    public Key min(){
        if(root==null) return null;
        return min(root).key;
    }

    private Node min(Node root){
        if(root.left==null) return root;
        return min(root.left);
    }

    /**
     * 返回bst中的最大节点
     * @return
     */
    public Key max(){
        if(root==null) return null;
        return max(root).key;
    }

    private Node max(Node root){
        if(root.right==null) return root;
        return max(root.right);
    }

    /**
     * 返回索引为index的节点
     * @param index
     * @return
     */
    public Key select(int index){
        Node node =select(root,index);
        if(node == null) return null;
        return node.key;
    }

    private Node select(Node root,int index){
        if(root==null) return null;
        //查询左子树节点个数
        int size=size(root.left);
        //index若小于左子树个数 说明要找的node节点仍然在左子树中
        if(index<size) return select(root.left,index);
        //index大于左子树 说明在右子树中 在递归寻找时需要减去根节点及其左子树的节点个数
        else if(index>size) return select(root.right,index-size-1);
        //相等则找到要返回的节点
        else return root;

    }

    /**
     * 删除最小节点
     */
    public void deleteMin(){
        if(root==null) return ;
        root=deleteMin(root);
    }

    private Node deleteMin(Node root){
        //左接节点为空 返回右节点
        if(root.left==null) return root.right;
        //递归找到最小的节点
        root.left= deleteMin(root.left);
        //删除后 重新计算节点个数
        root.n=size(root.left)+size(root.right)+1;

        return root;

    }

    /**
     * 删除键为key的节点
     * @param key
     */
    public void delete(Key key){
        if(key==null) throw new NullPointerException();
        root=delete(root,key);
    }

    private Node delete(Node root,Key key){
        //根节点为空 直接返回null
        if(root==null) return null;
        //获取比较值
        int compare = root.key.compareTo(key);
        //根节点小于key 向右寻找删除的节点
        if(compare<0) root.right=delete(root.right,key);
        //大于key 向左寻找
        else if(compare>0) root.left=delete(root.left,key);
        //等于key 找到删除节点
        else{
            //删除节点左节点为空 返回右节点
            if(root.left==null) return root.right;
            //右节点为空 返回左节点
            if(root.right==null) return root.left;
            //都不为空
            else{
                //先记录下要删除的节点
                Node temp=root;
                //将待删除节点用他右子树的最小值替换
                root=min(root.right);
                //继续删除待删除节点的右子树中被替换的节点 调用deletemin找到右子树中的最小节点
                root.right=deleteMin(root.right);
                //待删除节点被替换后 其左子树上的节点需要重新连接 使用临时变量记录下的temp重现连接上
                root.left=temp.left;
            }
        }
        //更新树的节点个数
        root.n=size(root.left)+size(root.right)+1;
        return root;
    }

    /**
     * 返回整棵树
     * @return
     */
    public Iterable<Key> keys(){
        return keys(min(),max());
    }

    /**
     * 返回键值low到high的key值
     * @param low
     * @param high
     * @return
     */
    public Iterable<Key> keys(Key low ,Key high){
        Queue<Key> queue = new LinkedList<>();
        keys(queue,root,low,high);
        return queue;
    }

    private void keys(Queue<Key> queue,Node root,Key low,Key high){
        if(root==null) return ;
        int comparelow=low.compareTo(root.key);
        int comparehigh=high.compareTo(root.key);
        //小于0 说明当前根节点过大 需要向左递归寻找等于low的key 直到找到左边界
        if(comparelow<0) keys(queue,root.left,low,high);
        //找到等于key的节点时 将其加入队列
        if(comparelow<=0 && comparehigh>=0) queue.add(root.key);
        //大于0 说明当前根节点过小 向右递归寻找等于high的key 直到找到右边界
        if(comparehigh>0) keys(queue,root.right,low,high);
    }
}
