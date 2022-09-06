package 树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhp
 * @date 2021-12-03 11:32
 * 将有序数组构造成一棵平衡二叉树
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class 将有序数组转化为二叉搜索树_lc_108 {
    /**
     *构造一棵平衡二叉树本质上和构造一颗普通树没区别
     * 都是对数组进行分割操作--可见lc_654构造最大二叉树
     * 只不过平衡二叉树需要保证左右子树深度差不超过1
     * 所以可以选择从数组中间进行分割
     *
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return arrayToBST(nums,0,nums.length-1);
    }

    /**
     * 从数组中部进行分割
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public TreeNode arrayToBST(int[] nums,int start,int end){
        if(start>end) return null;

        int mid=(start+end)/2;
        TreeNode node= new TreeNode(nums[mid]);

        node.left=arrayToBST(nums,start,mid-1);
        node.right=arrayToBST(nums,mid+1,end);
        return node;
    }

    /**模拟法
     * 使用三个队列模拟递归过程
     * nodequeue 存放要填值的节点
     * leftqueue    保存左区间下表
     * rightqueue   保存右区间下表
     */

    public TreeNode moni(int nums[]) {
        TreeNode root = new TreeNode(0);
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> leftQueue = new LinkedList<>();
        Queue<Integer> rightQueue = new LinkedList<>();
        nodeQueue.offer(root);
        leftQueue.offer(0);
        rightQueue.offer(nums.length - 1);
        while (!nodeQueue.isEmpty()) {
            TreeNode cur = nodeQueue.poll();
            int left = leftQueue.poll();
            int right = rightQueue.poll();
            int mid = left + (right - left) / 2;
            cur.val = nums[mid];

            if (left <= mid - 1) {    //处理左区间
                cur.left = new TreeNode(0);
                nodeQueue.offer(cur.left);
                leftQueue.offer(left);
                rightQueue.offer(mid - 1);
            }

            if (right >= mid + 1) {//处理右区间
                cur.right = new TreeNode(0);
                nodeQueue.offer(cur.right);
                leftQueue.offer(mid + 1);
                rightQueue.offer(right);
            }

        }
        return root;
    }
}
