package 树;

/**
 * @author zhp
 * @date 2022-07-06 16:23
 * 已知一棵二叉树中没有重复节点，并且给定了这棵树的中序遍历数组和先序遍历
 * 数组，返回后序遍历数组。
 * 比如给定：
 * int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
 * int[] in = { 4, 2, 5, 1, 6, 3, 7 };
 * 返回：
 * {4,5,2,6,7,3,1}
 */
public class _重构二叉树 {
    /**
     * @param pre 先序
     * @param in  中序
     * @param post 后序
     * @param preB 先序左边界
     * @param preE 先序右边界
     * @param inB  中序左边界
     * @param inE  中序右边界
     * @param postB 后序左边界
     * @param postE 后序右边界
     */
    public static void rebuildTree(int []pre,int []in,int []post,
                                   int preB,int preE,int inB,int inE,int postB,int postE){
        if(preB>preE){//全部重建完成
            return ;
        }

        if(preB==preE){//只剩下一个节点，直接设置
            post[postB] = pre[preB];
            return ;
        }
        post[postB] = pre[preB];//进入时将pre数组的第一位即根节点设置为post数组的最后一位，即整棵树的根节点

        //找到当前根节点在in数组内的位置
        int find = inB;
        for(;find<inE;find++){
            if(pre[preB]==in[find]){
                break;
            }
        }

        //设置完当前树的根节点，向其左右子树进行递归，完善整棵树的重构
        rebuildTree(pre,in,post,
                preB+1,preB+find-inB,inB,find-1,postB,postB+find-inB-1);
        rebuildTree(pre,in,post,
                preB+find-inB+1,preE,find+1,inE,postB+find-inB,postE);
    }
}
