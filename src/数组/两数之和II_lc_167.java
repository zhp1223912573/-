package 数组;

/**
 * @author zhp
 * @date 2022-07-15 19:16
 * https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/solution/liang-shu-zhi-he-ii-shu-ru-you-xu-shu-zu-by-leet-2/
 * 本题与两数之和_lc_1相似
 */
public class 两数之和II_lc_167 {
    /**二分
     * 该数组有序，第一个数选定，第二个数二分查找
     * O(nlogn)
     */
    public int[] twoSum(int numbers[],int target){
        for (int i = 0; i < numbers.length; ++i) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};

    }

    /**
     * 双指针
     * 数组有效，直接双指针缩小，直到找到答案
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum1(int numbers[],int target){
        int left = 0;
        int right = numbers.length-1;
        while(left<right){
            if(numbers[left]+numbers[right]==target){
                return new int[]{left,right};
            }else if(numbers[left]+numbers[right]<target){
                left++;
            }else{
                right--;
            }
        }
        return new int[]{-1,-1};
    }

}
