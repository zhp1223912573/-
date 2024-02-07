package 数组.子数组累加和;

/**
 * @author zhp
 * @date 2022-07-10 19:48
 * 给定一个无序数组 arr，其中元素可正、可负、可 0，给定一个整数 k。求 arr 所
 * 有的子数组 中累加和小于或等于 k 的最长子数组长度。
 * 例如:arr=[3,-2,-4,0,6]，k=-2，相加和小于或等于-2 的最长子数组为{3,-2,-
 * 4,0}，所以结果返回4
 */
public class _包含负数 {
    /**
     * 由于数组中存在负数，所以仅仅是使用双指针无法考虑到特殊情况的存在，而如果只是简单的
     * 扫描数组中存在的所有子数组并记录符合条件的最大子数组，则时间开销会达到O(n^2)
     * <p>
     * 所以使用两个数组minsum和minsumend，分别记录以i数开头的最小数组的值，以及以该子数组的右边界位置。
     * 随后，我们从第一个数值开始遍历，找到以i位置数值开头的最长符合条件子数组，并记录下来，
     * 接着，从i+1开始查找最长子数组，但并非和第一个一样重新开始查找，而是依靠我们已经得到的最长子数组长度，
     * 不需要取挨个判断i+2,i+3...的数加入数组时符不符合条件，我们尝试获得更大的子数组长度，所以直接根据第一个字符
     * 得到的子数组的最右边界+1的位置进行查找，忽视i+1到最右边界+1范围内的所有可能，因为即使存在这种可能，
     * 也无法更新最长子数组长度，重复上述操作，直到最后一个数字。
     */
    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] minSums = new int[arr.length];
        int[] minSumEnds = new int[arr.length];
        minSums[arr.length - 1] = arr[arr.length - 1];
        minSumEnds[arr.length - 1] = arr.length - 1;
        //得到以当前数值开头的最小子数组,同时记录该子数组的右边界
        for (int i = arr.length - 2; i >= 0; i--) {
            //
            if (minSums[i + 1] < 0) {
                minSums[i] = arr[i] + minSums[i + 1];
                minSumEnds[i] = minSumEnds[i + 1];
            } else {
                minSums[i] = arr[i];
                minSumEnds[i] = i;
            }
        }
        int end = 0;
        int sum = 0;
        int res = 0;
        // i是窗口的最左的位置，end是窗口最右位置的下一个位置
        for (int i = 0; i < arr.length; i++) {
            // while循环结束之后：
            // 1) 如果以i开头的情况下，累加和<=k的最长子数组是arr[i..end-1]，看看这个子数组长度能不能更新res；
            // 2) 如果以i开头的情况下，累加和<=k的最长子数组比arr[i..end-1]短，更新还是不更新res都不会影响最终结果；
            while (end < arr.length && sum + minSums[end] <= k) {
                sum += minSums[end];
                end = minSumEnds[end] + 1;
            }
            res = Math.max(res, end - i);
            if (end > i) { // 窗口内还有数
                sum -= arr[i];
            } else { // 窗口内已经没有数了，说明从i开头的所有子数组累加和都不可能<=k
                end = i + 1;
            }
        }
        return res;
    }
}
