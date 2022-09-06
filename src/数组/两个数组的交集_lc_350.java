package 数组;

import sun.security.krb5.internal.tools.Klist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-13 23:36
 * https://leetcode.cn/problems/intersection-of-two-arrays-ii/
 */
public class 两个数组的交集_lc_350 {
    /**
     * 思路总结：
     * 1.hashmap记录
     * 2.排序+双指针
     * 对数组排序，双指针指向两个数组首部，比较指针值，
     * 较小值的指针右移，相等则记录，到结尾退出。
     */


    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int left = 0;
        int right = 0;
        List<Integer> ans = new ArrayList<>();
        while(left!=nums1.length&&right!=nums2.length){
            if(nums1[left]<nums2[right]){
                left++;
            }else if(nums1[left]>nums2[right]){
                right++;
            }else{
                ans.add(nums1[left]);
                left++;
                right++;
            }
        }
        int num [] = new int[ans.size()];
        for (int i = 0; i <num.length ; i++) {
            num[i]=ans.get(i);
        }
        return num;
    }

    public static void main(String[] args) {
            int nums1[] ={1,2,2,1};
            int nums2[] ={2,2};
        int[] intersect = intersect(nums1, nums2);
        System.out.println(Arrays.toString(intersect));
    }
}
