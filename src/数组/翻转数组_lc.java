package 数组;

/**
 * @author zhp
 * @date 2022-07-13 22:30
 * https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2skh7/
 */
public class 翻转数组_lc {
    /**
     * 旋转数组有两种思路：
     * 1.利用辅助数组
     * 2.通过局部翻转到整体翻转实现旋转
     *
     * 方法2存在一个细节上的问题，在进行局部翻转，旋转要分割的左右数组的中值mid时，
     * 该数mid可能因为k大于数组长度而导致溢出，为了使mid指向正确的位置，需要对数取余
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if(nums==null || nums.length<2){
            return;
        }

        rotateByreverse(nums,0,(nums.length-(k%nums.length)),nums.length-1);
    }


    public void rotateByreverse(int []nums,int left,int mid,int right ){
        //翻转前半部分
        reverse(nums,left,mid-1);
        //翻转后半部分
        reverse(nums,mid,right);
        //翻转整体，实现理想翻转效果
        reverse(nums,left,right);
    }

    private void reverse(int[] nums, int left, int right) {
        while(left<right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int arr[] = {1,2};
        翻转数组_lc lc = new 翻转数组_lc();
        lc.rotate(arr,3);
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+",");
        }
    }
}
