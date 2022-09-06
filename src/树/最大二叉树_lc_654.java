package 树;

import org.junit.Test;

import java.util.List;

/**
 * @author zhp
 * @date 2021-11-28 11:13
 * https://leetcode-cn.com/problems/maximum-binary-tree/
 * 最大二叉树
 */
public class 最大二叉树_lc_654 {
    @Test
    public void output(){
        int nums[]={3,2,1,6,0,5};
        TreeNode root = constructMaximumBinaryTree(nums);
        List<Integer> integers = 二叉树遍历总结.preorderTravesal(root);
        System.out.println(integers);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructTree(nums,0,nums.length-1);
    }

    private TreeNode constructTree(int[] nums, int start, int end) {
        if(start>end) return null;

        int index=-1;
        int max=-1;
        for(int i=start;i<=end;i++){
            if(nums[i]>max){
                index=i;
                max=nums[i];
            }
        }

        TreeNode root = new TreeNode(max);
        root.left=constructTree(nums,start,index-1);
        root.right=constructTree(nums,index+1,end);

        return root;
    }


}
