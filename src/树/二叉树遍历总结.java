package 树;



import java.util.*;

/**
 * @author zhp
 * @date 2021-11-20 12:00
 * 二叉树的遍历实现
 * 详细图片如下
 * https://www.cnblogs.com/qjmnong/p/9135386.html
 *
 *
 */

public class 二叉树遍历总结 {


    /*递归实现相对较容易 只要把输出的位置摆放在之前位置即可*/
    public void preorde(TreeNode root){
        if(root!=null){
            System.out.println(root.val);
            preorde(root.left);
            preorde(root.right);
        }
    }

    public void inorder(TreeNode root){
        if(root!=null){
            inorder(root.left);
            System.out.println(root.val);
            inorder(root.right);
        }
    }

    public void postorder(TreeNode root){
        if(root!=null){
            postorder(root.left);
            postorder(root.right);
            System.out.println(root.val);
        }
    }

    /**先序
     *
     *迭代利用栈的特性实现非递归遍历
     * */
    public static List<Integer> preorderTravesal(TreeNode root){
        if(root==null) return null;

        List<Integer> list= new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();

        stack.push(root);
        //先放入右子节点 再放入左子节点 保证先弹出左子节点 实现先序
        while(!stack.empty()){
           TreeNode node=stack.pop();
            list.add(node.val);

            if(node.right!=null){
                stack.push(node.right);
            }

            if(node.left!=null){
                stack.push(node.left);
            }
        }

        return list;
    }

    /**后序遍历
     * 先序遍历是中左右 后序遍历是左右中 将先序的顺序反转一下 输出中右左 最后再反转整个list即可得到
     * */
    public static List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> list = new ArrayList<Integer>();

        Stack<TreeNode> s = new Stack<TreeNode>();

        s.push(root);

        while( !s.isEmpty() ) {
            TreeNode node = s.pop();
            if(node.left != null) {
                s.push(node.left);
            }

            if(node.right != null) {
                s.push(node.right);
            }
            //最后加入父节点
            list.add(node.val);
        }
        //反转
        Collections.reverse(list);
        return list;
    }

    /**中序遍历是左中右 先不断压入左子节点 直到为空 说明当前左子节点不存在 则返回栈顶节点
    * 该节点为左子节点的父节点 即是中 此时输出他 然后压入其右子节点 */
    public static List<Integer> inorderTraversal(TreeNode root){
        if(root==null) return null;
        List<Integer> list = new LinkedList<>();

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur=root;
        while(cur!=null || !stack.empty()){
            if(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }else{
                cur=stack.pop();
                list.add(cur.val);
                cur=cur.right;
            }
        }

        return list;

    }

    /*先后中序遍历模板*/
    public static List<Integer> templateForThree(TreeNode root,int model){
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();
        if(root!=null) stack.push(root);

        while(!stack.empty()){
            TreeNode node=stack.peek();
            if(node!=null){
                stack.pop();
                switch(model){
                    //先序
                    case 1:
                        if(root.right!=null) stack.push(root.right);
                        if(root.left!=null) stack.push(root.left);
                        stack.push(node);
                        stack.push(null);
                        break;
                        //中序
                    case 2:
                        if(root.right!=null) stack.push(root.right);
                        stack.push(node);
                        stack.push(null);
                        if(root.left!=null) stack.push(root.left);
                        break;
                        //后序
                    case 3:
                        stack.push(node);
                        stack.push(null);
                        if(root.right!=null) stack.push(root.right);
                        if(root.left!=null) stack.push(root.left);
                        break;
                    default:
                        System.out.println("wrong model number!");
                        break;
                }
            }else{
                stack.pop();//弹出空节点
                node = stack.peek();
                stack.pop();
                list.add(node.val);
            }
        }

        return list;

    }

    /**morris遍历
     * 上述两种遍历空间开销都是O(n),如果要求一种空间开销为O(1)的算法，可以使用morris遍历
     * 该算法通过使用原树中大量空闲指针的方式，达到了节省空间的目的
     *
     * 基本思路：
     * 假设来到当前节点粗如，开始时来到头节点位置
     * 1）如果cur没有左孩子，cur向右移动（cur=cur.right)
     * 2)如果cur有右孩子，找到左子树上的最右的节点mostRight
     *      a.如果mostRigt的右指针指向空，让其指向cur，然后cur向左移动（cur=cur.left)
     *      b.如果mostRight的右指针指向cur，让其指向null，然后cur向右移动（cur=cur.right //此时是第二次访问cur了
     * 3)cur为空时遍历停止
     *
     * 实例演示：
     * 下述是一棵满二叉树，通过上述步骤的遍历结果是：1，2，4，2，5，1，3，6，3，7
     *       1
     *  2        3
     *4   5     6   7
     *
     *
     */
    public static void morris(TreeNode root){
        if(root==null){
            return ;
        }

        TreeNode cur = root;
        TreeNode mostRight = null;

        while(cur!=null){
            mostRight = cur.left;//此时的mostRight代表左子树
            if(mostRight!=null) {//存在左子树
                while(mostRight.right!=null && mostRight.right!=cur){//找到左子树上的最右节点
                    mostRight = mostRight.right;
                }

                if(mostRight.right==null){//当前的cur是第一次被访问到
                    mostRight.right = cur;//让最右节点右连当前节点cur，这样实现了回到cur的效果
                    cur = cur.left;//之后继续向左找，看看是否还有左子树节点尚未输出
                    continue;//必须重新开始循环，才能继续输出左子树的左子树节点信息
                }else{//当前的cur是第二次被访问到
                    mostRight.right = null;//取消掉相连条件，避免破坏原有树结构
                }
            }

            cur = cur.right;//不存在左子树或者mostRight指向cur时右移cur，
        }
    }

    /**先序morris
     *
     * 根据morris算法输出的结果分析，部分节点会被重复输出，
     * 为了实现先序遍历的效果，需要对原有的输出结果进行处理。
     * 对首次出现的节点直接输出，第二次输出的节点跳过，这样就可以实现先序遍历。
     *
     *  下述是一棵满二叉树，
     *            1
     *       2        3
     *   4   5     6   7
     *  morris遍历结果是：1，2，4，2，5，1，3，6，3，7
     * preMorris遍历结果：1，2，4，5，3，6，7
     *    符合先序遍历结果。
     *
     *
     * @param root
     */
    public static void preMorris(TreeNode root){
        if(root==null){
            return ;
        }

        TreeNode cur = root;
        TreeNode mostRight = null;

        while(cur!=null){
            mostRight = cur.left;//此时的mostRight代表左子树
            if(mostRight!=null) {//存在左子树
                while(mostRight.right!=null && mostRight.right!=cur){//找到左子树上的最右节点
                    mostRight = mostRight.right;
                }

                if(mostRight.right==null){//当前的cur是第一次被访问到
                    System.out.print(cur.val+" ");
                    mostRight.right = cur;//让最右节点右连当前节点cur，这样实现了回到cur的效果
                    cur = cur.left;//之后继续向左找，看看是否还有左子树节点尚未输出
                    continue;//必须重新开始循环，才能继续输出左子树的左子树节点信息
                }else{//当前的cur是第二次被访问到
                    mostRight.right = null;//取消掉相连条件，避免破坏原有树结构
                }
            }else{//不存在左子树
                System.out.print(cur.val+" ");
            }

            cur = cur.right;//不存在左子树或者mostRight指向cur时右移cur，
        }
    }

    /**中序morris
     *
     * 根据morris算法输出的结果分析，部分节点会被重复输出，
     * 为了实现中序遍历的效果，需要对原有的输出结果进行处理。
     * 对首次出现的节点直接输出，第1次输出的节点跳过，输出第二次出现的节点这样就可以实现中序遍历。
     *
     *  下述是一棵满二叉树，
     *            1
     *       2        3
     *   4   5     6   7
     *  morris遍历结果是：1，2，4，2，5，1，3，6，3，7
     * inMorris遍历结果：4,2,5,1,6,3,7
     *    符合中序遍历结果。
     *
     *
     * @param root
     */
    public static void inMorris(TreeNode root){
        if(root==null){
            return ;
        }

        TreeNode cur = root;
        TreeNode mostRight = null;

        while(cur!=null){
            mostRight = cur.left;//此时的mostRight代表左子树
            if(mostRight!=null) {//存在左子树
                while(mostRight.right!=null && mostRight.right!=cur){//找到左子树上的最右节点
                    mostRight = mostRight.right;
                }

                if(mostRight.right==null){//当前的cur是第一次被访问到

                    mostRight.right = cur;//让最右节点右连当前节点cur，这样实现了回到cur的效果
                    cur = cur.left;//之后继续向左找，看看是否还有左子树节点尚未输出
                    continue;//必须重新开始循环，才能继续输出左子树的左子树节点信息
                }else{//当前的cur是第二次被访问到
                    System.out.print(cur.val+" ");
                    mostRight.right = null;//取消掉相连条件，避免破坏原有树结构
                }
            }else{//不存在左子树
                System.out.print(cur.val+" ");//
            }

            cur = cur.right;//不存在左子树或者mostRight指向cur时右移cur，
        }
    }

    /**后序morris
     *
     * 根据morris算法输出的结果分析，部分节点会被重复输出，
     * 为了实现后序遍历的效果，需要对原有的输出结果进行处理。
     *
     * 每当第二次遇到某个节点，则逆序打印该节点的最右节点
     * 为了实现空间复杂度O(1),我们不能利用栈等数据结构来实现逆序打印，
     * 所以我们直接在原有树上逆序要输出的链表，最后输出完了再逆序回来。
     *
     *  下述是一棵满二叉树，
     *            1
     *       2        3
     *   4   5     6   7
     *  morris遍历结果是：1，2，4，2，5，1，3，6，3，7
     * posMorris遍历结果：4，5，2，6，7，3，1
     *    符合后序遍历结果。
     *
     *
     * @param root
     */
    public static void posMorris(TreeNode root){
        if(root==null){
            return ;
        }

        TreeNode cur = root;
        TreeNode mostRight = null;

        while(cur!=null){
            mostRight = cur.left;//此时的mostRight代表左子树
            if(mostRight!=null) {//存在左子树
                while(mostRight.right!=null && mostRight.right!=cur){//找到左子树上的最右节点
                    mostRight = mostRight.right;
                }

                if(mostRight.right==null){//当前的cur是第一次被访问到

                    mostRight.right = cur;//让最右节点右连当前节点cur，这样实现了回到cur的效果
                    cur = cur.left;//之后继续向左找，看看是否还有左子树节点尚未输出
                    continue;//必须重新开始循环，才能继续输出左子树的左子树节点信息
                }else{//当前的cur是第二次被访问到

                    mostRight.right = null;//取消掉相连条件，避免破坏原有树结构
                    printEdge(cur.left);
                }
            }
            cur = cur.right;//不存在左子树或者mostRight指向cur时右移cur，
        }
        printEdge(root);
    }

    //以x为头的树，逆序打印这棵树的右边界
    public static void printEdge(TreeNode x){
        TreeNode tail = reverseEdge(x);
        TreeNode cur = tail;
        while(cur!=null){
            System.out.print(cur.val+" ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    /**逆序链表并返回头节点
     *
     * @param from
     * @return
     */
    private static TreeNode reverseEdge(TreeNode from) {
        TreeNode pre =null;
        TreeNode next =null;
        while(from!=null){
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.val = 1;
        TreeNode node = new TreeNode(2);
        node.left=new TreeNode(4);
        node.right = new TreeNode(5);
        root.left = node;

        node = new TreeNode(3);
        node.left=new TreeNode(6);
        node.right = new TreeNode(7);
        root.right = node;


        posMorris(root);


        List<Integer> integers = postorderTraversal(root);
        System.out.println(integers);
    }
}

