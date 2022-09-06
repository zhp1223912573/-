package 预处理;

/**
 * @author zhp
 * @date 2022-07-04 21:09
 *
 * 给定一个数组arr长度为N，你可以把任意长度大于0且小于N的前缀作为左部分，剩下的
 * 作为右部分。但是每种划分下都有左部分的最大值和右部分的最大值，请返回最大的，
 * 左部分最大值减去右部分最大值的绝对值。
 */
public class _数组左右数组最大值差值绝对值最大值 {
    /**预处理
     * 给定某个数组：
     * -1 3 2 5 9 -2
     * 求出该数组的前缀最大值和后缀最大值
     * 前缀最大值：
     * -1 3 3 5 9 9
     * 后缀最大值
     * 9 9 9 9 9 -2
     * 根据上述得到两个预处理数组，分别减去对应位的值，找到绝对值最大值
     *
     * 综上：通过预处理，空间开销为O(n),时间开销为O(n)
     */
    public static int maxABS(int arr[]){
        if(arr==null || arr.length<2){
            return -1;
        }

        int qianzhui [] = new int[arr.length];
        int houzhui [] = new int[arr.length];

        qianzhui[0] = arr[0];
        houzhui[arr.length-1] = arr[arr.length-1];
        for(int i=1;i<arr.length;i++){
            qianzhui[i] = Math.max(qianzhui[i-1],arr[i]);
        }

        for(int i=arr.length-2;i>=0;i--){
            houzhui[i] = Math.max(houzhui[i+1],arr[i]);
        }

        int max=0;
        for(int i=0;i<arr.length-1;i++){
            max = Math.max(max,Math.abs(qianzhui[i]-houzhui[i+1]));
        }
        return max;
    }

    /**
     * 分析给定的数组:
     *  -1 3 2 5 9 8 -2
     *  数组的最大值max为9，最大值9不是在左部分数组，就是在右部份数组，
     *  所以一定有一部分的数组最大值为max=9，
     *  假定该max在左半部分，那么，右半部分一定包含位置为n-1的数值，
     *  也就是说不管n-1位置上的数是不是右半部分最大，我们都要考虑他，
     *  那么我们就直接将该右半部分设置为只有位置n-1的一部分数值，
     *  这样我们就可以掩盖掉在max右边但大于最后一个位置即n-1位置上的数的影响。
     *  左右数组的最大值差值的最大值就只取决于max与n-1位置上的数。
     *
     *  上述步骤同样适用于max位于右半部分数组的情况。
     *  因此数组中左右数组的最大值的差值的最大值为:
     *  ABSmax = MAX( |max-arr[0]| , |max-arr[n-1| )
     *
     *  通过该算法，我们只需要设置几个变量，空间开销降低，时间开销不变
     */
    public static int maxABS1(int arr[]){
        if(arr==null || arr.length<2){
            return -1;
        }

        int max = Integer.MIN_VALUE;
        //找到数组中的最大值
        for(int i=0;i<arr.length;i++){
            max = Math.max(max,arr[i]);
        }
        return Math.max(Math.abs(max-arr[0]),Math.abs(max-arr[arr.length-1]));
        //return max-Math.min(arr[0],arr[arr.length-1]);
    }

    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i != arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000) - 499;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(200);
        System.out.println(maxABS(arr));
        System.out.println(maxABS1(arr));
    }
}
