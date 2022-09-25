package 排序;


import java.util.Arrays;

/**
 * @author zhp
 * @date 2022-07-09 13:43
 *
 * 给定一个数组，求如果排序之后，相邻两数的最大差值。要求时间复杂度O(N)，且要
 * 求不能用非基于比较的排序
 */
public class _得到数组最大相邻差 {
    /**
     * 非基于比较的排序包括桶排序，计数排序，基数排序
     * 要实现该算法，需要基于桶排序的思想。
     * 假定数组num有n个元素，最小值min，最大值max。
     * 我们设置n+1个容量为2的桶数组，保存指定范围内的数值到对应的数组内，
     * 因为我们设置了n+1个桶数组，所以一定存在一个空桶，
     * 因此相邻数的最大差值一定存在于相邻桶之间，而不是桶内，因为存在一个空桶。
     * 而我们只需要直到每个桶内的最大最小值即可，用下一个相邻非空桶的最小值减去当前桶的最大值，
     * 找到最大差值即可。
     */
    public static int maxGap(int[] nums){
        if(nums==null || nums.length<2){
            return 0;
        }

        //得到数组最大最小值
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;


        for(int i=0;i<nums.length;i++){
            min = Math.min(min,nums[i]);
            max = Math.max(max,nums[i]);
        }


        if(min==max){
            return 0;
        }

        int len = nums.length;
        boolean hasNum[] = new boolean[len+1];
        int maxs[] = new int[len+1];//记录每个桶的最大值
        int mins[] = new int[len+1];//记录每个桶的最小值

        for(int i=0;i<len;i++){
            int bid = bucket(len,nums[i],min,max);//计算对应数值的桶号
            maxs[bid] = hasNum[bid]? Math.max(maxs[bid],nums[i]):nums[i];//第一次加入就直接加入，第二次开始进行比较
            mins[bid] = hasNum[bid]? Math.min(mins[bid],nums[i]):nums[i];//第一次加入就直接加入，第二次开始进行比较
            hasNum[bid] = true;
        }

        //开始计算最大差值
        int res = 0;
        int lastMax = maxs[0];//前一个最大差值
        for(int i=1;i<=len;i++){//从第二个桶开始
            if(hasNum[i]){//存在数值
                res = Math.max(res,mins[i]-lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    private static int bucket(long len, long num, long min, long max) {
        if(max==min){
            System.out.println("");
        }
        return (int)((num-min)*len/(max-min));
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int comparator(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            gap = Math.max(nums[i] - nums[i - 1], gap);
        }
        return gap;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (maxGap(arr1) != comparator(arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
