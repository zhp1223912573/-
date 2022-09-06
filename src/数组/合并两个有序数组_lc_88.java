package 数组;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-15 16:12
 * https://leetcode.cn/problems/merge-sorted-array/
 */
public class 合并两个有序数组_lc_88 {
    /**
     * 暴力法
     * 调用sort函数，时间复杂度O(nlogn)
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums1==null || nums2==null || nums1.length<m+n){
            return ;
        }

        for(int i=m;i<nums1.length;i++){
            nums1[i] = nums2[i-m];
        }

        Arrays.sort(nums1);
    }

    /**
     * 三指针
     * 两指针指向数组有效数字尾部，一指针last指向nums1数组的尾部，
     * 比较有效数字指针的大小，将较大值移动到last指向的位置,
     * 直到nums1数组尾部的m-n给空间都被填充完，说明数组合并成功。
     */
    public static void merge1(int []nums1,int m,int[] nums2,int n){
        int p1 = m-1;
        int p2 = n-1;
        int last = nums1.length-1;
        while(p1>=0 || p2>=0){
            if(p1==-1){
                nums1[last--] = nums2[p2--];
            }else if(p2==-1){
                nums1[last--] = nums1[p1--];
            }
            else if(nums1[p1]>nums2[p2]){
                nums1[last--] = nums1[p1];
                nums1[p1--] = -1;
            }else {
                nums1[last--] = nums2[p2];
                nums2[p2--]= -1;
            }
        }
    }

    public static void main(String[] args) {
        int nums1[] = {1,2,3,0,0,0};
        int nums2[] = {2,5,6};
        merge1(nums1,3,nums2, 3);
    }
}
