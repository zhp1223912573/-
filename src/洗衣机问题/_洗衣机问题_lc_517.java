package 洗衣机问题;

/**
 * @author zhp
 * @date 2022-07-03 21:34
 *
 * 假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
 *
 * 在每一步操作中，你可以选择任意 m (1 <= m <= n) 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
 *
 * 给定一个整数数组 machines 代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的 最少的操作步数 。如果不能使每台洗衣机中衣物的数量相等，则返回 -1 。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/super-washing-machines
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _洗衣机问题_lc_517 {


    public static int MinOps(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int size = arr.length;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += arr[i];
        }
        if (sum % size != 0) {
            return -1;
        }
        int avg = sum / size;
        int leftSum = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int L = i * avg - leftSum;
            int R = (size - i - 1) * avg - (sum - leftSum - arr[i]);
            if (L > 0 && R > 0) {
                ans = Math.max(ans, Math.abs(L) + Math.abs(R));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(L), Math.abs(R)));
            }
            leftSum += arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
