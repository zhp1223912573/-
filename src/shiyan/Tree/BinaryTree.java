package shiyan.Tree;

import jdk.nashorn.internal.ir.BinaryNode;
import shiyan.Queue.LinkedQueue;

import java.util.*;

/**
 * @author zhp
 * @date 2021-12-17 21:54
 * 二叉树实现
 * recurison
 * iteration
 */
public class BinaryTree<T extends Comparable> implements Tree<T> {

    public static void main(String[] args) {

//            String[] preList={"A","B","D","G","C","E","F","H"};
//        String[] inList={"D","G","B","A","E","C","H","F"};
//        String[] postList={"G","D","B","E","H","F","C","A"};
//        /**
//         * 先根遍历:A,B,D,G,C,E,F,H
//         * 中根遍历:D,G,B,A,E,C,H,F
//         * 后根遍历:G,D,B,E,H,F,C,A
//         */
//        //先序中序重构
//        BinaryTree<String> btree=new BinaryTree<String>(preList,inList,false);
//        //树的深度及节点个数
//        System.out.println("树深度为："+btree.height()+",节点个数为："+btree.size());
//        //先序遍历输出
//        String outputPre=btree.preOrder();
//        System.out.println("递归先序："+outputPre);
//        String outputPreTravesal=btree.preOrderTravesal();
//        System.out.println("迭代先序:"+outputPreTravesal);
//        System.out.println("************************");
//        //中序遍历输出
//        String outputIn=btree.inOrder();
//        System.out.println("递归中序："+outputIn);
//        String outputInTravesal=btree.inOrderTravesal();
//        System.out.println("迭代中序:"+outputInTravesal);
//        System.out.println("************************");
//        //后序遍历输出
//        String outputPost=btree.postOrder();
//        System.out.println("递归后序："+outputPost);
//        String outputPostTravesal=btree.postOrderTravesal();
//        System.out.println("迭代后序:"+outputPostTravesal);
//        System.out.println("************************");
//        //层序遍历
//        System.out.println("层序遍历："+btree.levelOrder());
//
//        //后序中序重构
//        BinaryTree<String> btree1=new BinaryTree<String>(postList,inList,true);
//        //树的深度及节点个数
//        System.out.println("树深度为："+btree1.height()+",节点个数为："+btree1.size());
//        //先序遍历输出
//        String outputPre1=btree1.preOrder();
//        System.out.println("递归先序："+outputPre1);
//        String outputPreTravesal1=btree1.preOrderTravesal();
//        System.out.println("迭代先序:"+outputPreTravesal1);
//        System.out.println("************************");
//        //中序遍历输出
//        String outputIn1=btree1.inOrder();
//        System.out.println("递归中序："+outputIn1);
//        String outputInTravesal1=btree1.inOrderTravesal();
//        System.out.println("迭代中序:"+outputInTravesal1);
//        System.out.println("************************");
//        //后序遍历输出
//        String outputPost1=btree1.postOrder();
//        System.out.println("递归后序："+outputPost1);
//        String outputPostTravesal1=btree1.postOrderTravesal();
//        System.out.println("迭代后序:"+outputPostTravesal1);
//        System.out.println("************************");

        //插入多个数值 中序输出查看是否有序
        BinaryTree<Integer> btree= new BinaryTree<>();
        btree.insert(10);
        btree.insert(10);
        btree.insert(9);
        btree.insert(8);
        btree.insert(1);
        btree.insert(5);
        btree.insert(2);
        btree.insert(3);
        btree.insert(4);
        btree.insert(6);
        btree.insert(7);
        btree.insert(100);
        System.out.println("中序输出："+btree.inOrder());
        System.out.println("最大值为："+btree.findMax());
        System.out.println("最小值为："+btree.findMin());
        btree.remove(10);
        System.out.println("删除节点值为10的节点");
        System.out.println("中序输出："+btree.inOrder());
        System.out.println("最大值为："+btree.findMax());
        System.out.println("最小值为："+btree.findMin());
//        BinaryTree<Character>btree=new BinaryTree<>();
//        btree.insert('a');
//        btree.insert('c');
//        btree.insert('a');
//        btree.insert('d');
//        btree.insert('e');
//        btree.insert('S');
//        btree.insert('D');
//        btree.insert('A');
//        btree.insert('V');
//        System.out.println("中序输出："+btree.inOrder());
//        System.out.println("最大值为："+btree.findMax());
//        System.out.println("最小值为："+btree.findMin());

        btree.clear();
        System.out.println("清除");
        System.out.println("中序输出："+btree.inOrder());
        System.out.println("size:"+btree.size());
 }

    //root节点为根节点
    private TreeNode<T> root;


    public BinaryTree(){
        root=null;
    }


    /**
     * 根据输入先序，中序后序数组构造搜索树
     * @param p 先序或后序数组 依据isPreOrPost判断
     * @param i 中序数组
     * @param isPreOrPost 0表示p为pre先序数组 1表示p为post后续数组
     */
    public BinaryTree(T[] p,T[] i,boolean isPreOrPost){
        //判断出入数据
        if(p==null||i==null){
            throw new NullPointerException("传入数组为空！");
        }

        if(!isPreOrPost){
            //先序中序构造二叉树
          this.root=rebuildTreeByPreAndPost(p,i,0,i.length-1,0,p.length-1);
        }else{
            //后序中序构造二叉树
          this.root=rebuildTreeByPostAndPost(p,i,0,i.length-1,0,p.length-1);
        }


    }

    /**依据传入的先序中序数组转化为二叉树
     *
     * 思路：将传入的数组不断进行分割  连接左右节点 并递归返回根节点，
     * @param pre   先序数组
     * @param in    中序数组
     * @param starti    中序数组起始坐标
     * @param endi      中序数组起始坐标
     * @param startp    先序数组起始坐标
     * @param endp      先序数组起始坐标
     * @return
     */
    public TreeNode<T> rebuildTreeByPreAndPost(T[] pre,T[] in,int starti,int endi,int startp,int endp){


        //将先序数组的起始坐标startp作为根节点
        TreeNode<T> root=new TreeNode<>(pre[startp]);

        //如果数组没有其他元素 则返回root节点
        if(starti==endi&&startp==endp){
            return root;
        }
        //在中序数组中找到目前树的根节点位置 将数组划分为左子树数组和右子树数组
        int index;
        for(index=starti;index<=endi;index++){
            //如果中序数组中的index位置坐标和当前root节点值一致 则直接退出
           if(pre[startp].compareTo(in[index])==0){
               break;
           }
        }

        //计算出左右子树的长度
        int leftlength=index-starti;
        int rightlength=endi-index;

        //进行递归分割
        if(leftlength>0)
        root.left=rebuildTreeByPreAndPost(pre,in,starti,index-1,startp+1,startp+leftlength);
        if(rightlength>0)
        root.right=rebuildTreeByPreAndPost(pre,in,index+1,endi,startp+leftlength+1,endp);

        //构建完成 返回root
        return root;
    }

    /**
     * 依据传入的后序中序数组转化为二叉树
     * @param pre
     * @param in
     * @param starti
     * @param endi
     * @param startp
     * @param endp
     * @return
     */
    public TreeNode<T> rebuildTreeByPostAndPost(T[] pre,T[] in,int starti,int endi,int startp,int endp){
        //将后序数组的终点坐标endp作为根节点
        TreeNode<T> root=new TreeNode<>(pre[endp]);

        //如果数组没有其他元素 则返回root节点
        if(starti==endi&&startp==endp){
            return root;
        }
        //在中序数组中找到目前树的根节点位置 将数组划分为左子树数组和右子树数组
        int index;
        for(index=starti;index<=endi;index++){
            //如果中序数组中的index位置坐标和当前root节点值一致 则直接退出
            if(pre[endp].compareTo(in[index])==0){
                break;
            }
        }

        //计算出左右子树的长度
        int leftlength=index-starti;
        int rightlength=endi-index;

        //进行递归分割
        if(leftlength>0)
            root.left=rebuildTreeByPreAndPost(pre,in,starti,index-1,startp,startp+leftlength-1);
        if(rightlength>0)
            root.right=rebuildTreeByPreAndPost(pre,in,index+1,endi,startp+leftlength,endp-1);

        //构建完成 返回root
        return root;
    }
    /**
     * 判断二叉树是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return root!=null;
    }

    /**
     *调用size（Tree Node<T> root)函数
     * @return
     */
    @Override
    public int size() {
        return size(root);
    }

    /**
     * 递归得到节点数量
     * @param root
     * @return
     */
    private int size(TreeNode<T> root){
        if(root==null) return 0;
        return size(root.left)+size(root.right)+1;
    }


    @Override
    public int height() {
        return height(root);
    }

    /**
     * 返回左右子树中的最大值
     * @param root
     * @return
     */
    private int height(TreeNode<T> root){
        if(root==null) return 0;
        return Integer.max(height(root.left)+1,height(root.right)+1);
    }

    /**
     * 先序，中序，后序的递归实现如下
     * @return
     */

    @Override
    public String preOrder() {
       String out= preorder(root);
       //取出多余的，
        if(out.length()>0){
            out=out.substring(0,out.length()-1);
        }
        return out;
    }
    private String preorder(TreeNode<T> root){
        StringBuilder sb=new StringBuilder();
        if(root!=null){
            sb.append(root.val);
            sb.append(",");
            sb.append(preorder(root.left));
            sb.append(preorder(root.right));
        }
        return sb.toString();
    }
    @Override
    public String inOrder() {
        String out=inorder(root);
        //取出多余，
        if(out.length()>0){
            out=out.substring(0,out.length()-1);
        }

        return out;
    }
    private String inorder(TreeNode<T> root){
        StringBuilder sb=new StringBuilder();
        if(root!=null){
            sb.append(inorder(root.left));
            sb.append(root.val);
            sb.append(",");
            sb.append(inorder(root.right));
        }
        return sb.toString();
    }
    @Override
    public String postOrder() {
        String out=postorder(root);
        //取出多余，
        if(out.length()>0){
            out=out.substring(0,out.length()-1);
        }

        return out;
    }
    private String postorder(TreeNode<T> root){
        StringBuilder sb=new StringBuilder();
        if(root!=null){
            sb.append(postorder(root.left));
            sb.append(postorder(root.right));
            sb.append(root.val);
            sb.append(",");
        }
        return sb.toString();
    }


    /**
     * 先序遍历迭代调用
     * @return
     */
    public String preOrderTravesal(){
        String output=templateForThree(this.root,1);
        if(output.length()>0){
            output=output.substring(0,output.length()-1);
        }
        return output;
    }

    /**
     * 中序遍历迭代调用
     * @return
     */
    public String inOrderTravesal(){
        String output=templateForThree(this.root,2);
        if(output.length()>0){
            output=output.substring(0,output.length()-1);
        }
        return output;
    }

    /**
     * 后序遍历迭代掉用
     * @return
     */
    public String postOrderTravesal(){
        String output=templateForThree(this.root,3);
        if(output.length()>0){
            output=output.substring(0,output.length()-1);
        }
        return output;
    }
    /*先后中序遍历模板*/
    /**
     * 先序中序后序的迭代实现
     * @param root
     * @param model 1--先序 2--中序 3--后序
     * @return
     */
    private  String templateForThree(TreeNode<T> root, int model){
        //sb存储要输出的遍历树
        StringBuilder sb=new StringBuilder();
        //利用栈来实现
        Stack<TreeNode<T>> stack=new Stack<>();
        if(root!=null) stack.push(root);

        while(!stack.empty()){
            TreeNode<T> node=stack.peek();
            if(node!=null){
                stack.pop();
                /*
                再不同位置放入null从而实现先后中序遍历
                 */
                switch(model){
                    //先序
                    case 1:
                        //放入当前根节点的左节点，右节点 再放入目前遍历的根节点  然后放入null

                        if(node.right!=null) stack.push(node.right);
                        if(node.left!=null) stack.push(node.left);
                        stack.push(node);
                        stack.push(null);
                        break;
                        //中序
                    case 2:
                        //放入当前根节点的右节点，根节点 然后放入null  再放入目前遍历的根节点的左节点
                        if(node.right!=null) stack.push(node.right);
                        stack.push(node);
                        stack.push(null);
                        if(node.left!=null) stack.push(node.left);
                        //后序
                        break;
                    case 3:
                        //放入当前根节点，放入null 放入右节点 再放入目前遍历的根节点的左节点
                        stack.push(node);
                        stack.push(null);
                        if(node.right!=null) stack.push(node.right);
                        if(node.left!=null) stack.push(node.left);
                        break;
                }
            }
            //进入该条件分支 说明当前遍历的节点就是我们之前放入的null 而该节点的后一节点就是我们需要输出的节点
            else{
                stack.pop();//弹出空节点
                node = stack.peek();
                stack.pop();
                sb.append(node.val);
                sb.append(",");
            }
        }

        return sb.toString();

    }

    /**
     * 层序遍历
     * @return
     */
    @Override
    public String levelOrder() {
        LinkedQueue<TreeNode<T>> queue=new LinkedQueue<>();
        StringBuilder sb = new StringBuilder();

        if(root!=null){
            queue.add(root);
        }

        while(!queue.isEmpty()){
            TreeNode<T> node = queue.poll();
            sb.append(node.val);
            sb.append(",");
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }

        String out=sb.toString();
        if(out.length()>0){
            out=out.substring(0,sb.length()-1);
        }

        return out;
    }


    @Override
    public void insert(T data) {
        if(data==null){
            throw new NullPointerException();
        }

        //调用插入函数 设置根节点
        root=insert(data,root);

    }

    /**
     *这里的插入是以二叉搜索树的方式插入
     * @param data  插入节点值
     * @param root  插入的树的根节点
     */
    private TreeNode<T> insert(T data,TreeNode<T> root){
        if(root==null){
            root=new TreeNode<>(data);
        }

        //获取插入节点值和当前节点的插值 判断该新节点该插入当左还是右子树
        int c=data.compareTo(root.val);
        if(c<0){
            root.left=insert(data,root.left);
        }else if(c>0){
            root.right=insert(data,root.right);
        }

        return root;
    }

    /**
     * 删除值为data的节点
     * @param data
     */
    @Override
    public void remove(T data) {
        if(data==null){
            throw new NullPointerException();
        }

        root=remove(data,root);
    }

    /**删除节点
     * 1、删除节点的子节点都为空 直接删除
     * 2、删除节点的一个子节点为空 实验另一子节点替换当前节点 实现删除
     * 3、删除节点的两个子节点都不为空 用右子树的最小值替换当前节点值 再递归删除目前节点的右子树
     * @param data
     * @param root
     * @return
     */
    private TreeNode<T> remove(T data,TreeNode<T> root){
        if(root==null){
            return root;
        }

        //依旧删除节点值和当前节点值的比较选择递归的方向
        int compare=data.compareTo(root.val);

        if(compare<0){
           root.left= remove(data,root.left);
        }else if(compare>0){
            root.right=remove(data,root.right);
        }else if(root.left!=null&&root.right!=null){
            //左右子节点都不为空 则将当前节点用右子树的最小节点值替换 再递归删除右子树
            root.val=findMin(root.right);
            root.right=remove(root.val,root.right);

        }else{
            //根节点的左右子节点其中一个为空 将非空的子节点替换自己 实现删除的操作
            root=(root.left==null)?root.right:root.left;
        }

        return root;
    }
    /**
     * 向左不断寻找非空节点 即是最小节点
     * @return
     */
    @Override
    public T findMin() {
        return findMin(root);
    }

    private T findMin(TreeNode<T> root){
        if(root==null){
            return null;
        }
        TreeNode<T> node = root;
        while(node.left!=null){
            node=node.left;
        }
        return node.val;
    }
    /**
     * 向右不断寻找非空节点 即是最大节点
     * @return
     */
    @Override
    public T findMax() {
        if(root==null){
            return null;
        }

        TreeNode<T> node = root;
        while(node.right!=null){
            node=node.right;
        }
        return node.val;
    }



    @Override
    public void clear() {
        this.root=null;
    }
}
