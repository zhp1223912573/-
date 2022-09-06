package 特殊技巧.子数组累加和;

/**
 * @author zhp
 * @date 2022-07-10 19:39
 * 给定一个数组 arr，该数组无序，但每个值均为正数，再给定一个正数 k。求 arr 的
 * 所有子 数组中所有元素相加和为 k 的最长子数组长度。
 * 例如，arr=[1,2,1,1,1]，k=3。
 * 累加和为 3 的最长子数组为[1,1,1]，所以结果返回 3。
 * 要求：时间复杂度O(N)，额外空间复杂度O(1)
 */
public class _全为正数 {
    /**
     * 滑动窗口问题
     * 左右指针指向子数组左右边界，
     * 如果当前子数组的和等于目标值k，记录下最大数组长度，并右移左指针找到新的子数组
     * 如果当前子数组的和小于目标值k，则右移右指针扩大数组
     * 如果当前子数组的和大于目标值k，则左移左指针缩小数组
     */
    public static int getmaxLength(int arr[],int k){
        if(arr==null || arr.length<1 || k<0){
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while(right!=arr.length){
            if(sum==k){
                len = Math.max(len,right-left-1);
                sum-=arr[left++];
            }else if(sum<k){
                right++;
                if(right==arr.length){
                    break;
                }
                sum+=arr[right];
            }else{
                sum-=arr[left++];
            }
        }
        return len;
    }
}
