package 每日任务;

/**
 * @author zhp
 * @date 2022-10-22 10:56
 * https://leetcode.cn/problems/minimum-height-tree-lcci/solution/
 */
public class ms04_02_最小高度树 {
    /**
     * 通过二分获得左右半部分数组中的最中间节点，
     * 保证左右子树尽快能的填满，从而使树尽可能的矮
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums,0,nums.length);
    }

    private TreeNode helper(int[] nums,int left,int right){
        if(left==right) return null;
        int mid = left + (right-left)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums,left,mid);
        node.right = helper(nums,mid+1,right);
        return node;
    }
}
