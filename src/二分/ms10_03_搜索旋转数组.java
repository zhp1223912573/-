package 二分;

/**
 * @author zhp
 * @date 2022-10-25 12:30
 * https://leetcode.cn/problems/search-rotate-array-lcci/solution/xuan-zhuan-shu-zu-cong-yi-dao-nan-ge-ge-dcv7a/
 */
public class ms10_03_搜索旋转数组 {
    /**
     * 本题和搜索旋转排序数组II_lc_81题目条件一致，但是81中只要求判断是否存在目标数值target，
     * 而本题中要求返回target数值的最小下标。
     * 我们需要求出target的在数组中的最小位置。
     * 整体思路和81题一致，都是范围有序二分查找，但是为了得到最左的位置，
     *
     */
    public int search(int[] arr, int target) {
        if (arr[0] == target) {
            return 0;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] == target) {
                while (mid > 1 && arr[mid - 1] == arr[mid]) {
                    mid--;
                }
                return mid;
            } else if (arr[mid] > arr[left]) {
                if (arr[left] <= target && target < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (arr[mid] < arr[left]) {
                if (arr[mid] < target && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                left++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int arr[] ={15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        ms10_03_搜索旋转数组 a = new ms10_03_搜索旋转数组();
        int ans =  a.search(arr,5);
        System.out.println(ans);
    }
}
