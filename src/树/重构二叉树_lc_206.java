package 树;

import com.sun.corba.se.impl.dynamicany.DynEnumImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhp
 * @date 2021-11-27 12:26
 * 中序树与前后序树重构二叉树
 * https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0106.%E4%BB%8E%E4%B8%AD%E5%BA%8F%E4%B8%8E%E5%90%8E%E5%BA%8F%E9%81%8D%E5%8E%86%E5%BA%8F%E5%88%97%E6%9E%84%E9%80%A0%E4%BA%8C%E5%8F%89%E6%A0%91.md
 */
public class 重构二叉树_lc_206 {
    /**
     * 利用中序和先序 还是中序和后序来还原一颗树 本质上都是一样的
     * 利用先序第一个，后序最后一个节点为根节点的特性对中序数组进行切割
     * 只有在递归过程中的选择的数组坐标不一致 其他基本相符
     */
    //利用下述结构可以不必在递归过程中传递数组 减少了额外开销
    //同时使用map来装填中序数组及其下标的映射关系 不用在递归函数中再设置一个for循环寻找
    Map<Integer,Integer> map = new HashMap<>();
    int post[];
    int pre[];
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        post=postorder;
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        TreeNode node = rebuild(0,inorder.length-1,0,postorder.length-1);
        return node;
    }

    /**中序 后序
     * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solution/tu-jie-gou-zao-er-cha-shu-wei-wan-dai-xu-by-user72/
     * @param starti
     * @param endi
     * @param startp
     * @param endp
     * @return
     */
    public TreeNode rebuildPI(int starti,int endi,int startp,int endp){
        //不继续递归
        if(starti>endi || startp>endp) return null;

        //以后序数组的最后一个元素创建一个root
        TreeNode root = new TreeNode(post[endp]);
        //利用inorder的map来直接找到当前根节点为与inorder的那个位置
        int i=map.get(root.val);

        //利用i来分割inorder数组和postorder数组
        root.left=rebuild(starti,i-1,startp,startp+i-starti-1);//详情见链接图
        root.right=rebuild(i+1,endi,startp+i-starti,endp-1);

        return root;

    }

    /**中序先序
     *
     * @param starti
     * @param endi
     * @param startp
     * @param endp
     * @return
     */
    public  TreeNode rebuidlI(int starti,int endi,int startp,int endp){
        //不继续递归
        if(starti>endi || startp>endp) return null;

        //以先序数组的第一个元素创建一个root
        TreeNode root = new TreeNode(pre[startp]);
        //利用inorder的map来直接找到当前根节点为与inorder的那个位置
        int i=map.get(root.val);

        //利用i来分割inorder数组和postorder数组
        root.left=rebuild(starti,i-1,startp+1,startp+i-starti);//详情见链接图
        root.right=rebuild(i+1,endi,startp+i-starti+1,endp);

        return root;
    }


    public TreeNode rebuild(int starti,int endi,int startp,int endp){
        if(startp>endp){
            return null;
        }

        TreeNode root = new TreeNode(pre[startp]);
        int i = map.get(root.val);

        int num = i-starti;

        root.left = rebuild(starti,i-1,startp+1,startp+num);
        root.right = rebuild(i+1,endi,startp+num+1,endp);
        return  root;
    }
}
