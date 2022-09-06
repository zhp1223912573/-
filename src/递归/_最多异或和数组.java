package 递归;

import java.util.HashMap;

/**
 * @author zhp
 * @date 2022-07-09 14:48
 * 给出n个数字 a_1,...,a_n，问最多有多少不重叠的非空区间，使得每个区间内数字的
 * xor都等于0
 */
public class _最多异或和数组 {
    /**设置动态数组dp
     * dp[i]表示以i结尾时，最多的异或和数组数量。
     * 当遍历到i时，存在两种情况，
     * 1.存在以当前arr[i]结尾时，异或和为0的子数组，此时dp[i] = dp[i-n]+1，n为该子数组的长度
     * 2.不存在以当前arr[i]结尾时，异或和为0的子数组，此时dp[i] = dp[i-1];
     *
     * 每次遍历到arr[i]时都向前循环查找以arr[i]结尾时异或和为0的数组，使算法时间开销大大增加，
     * 我们可以设置一个map，映射子数组前缀和上一次出现的位置，这样就可以达到O(n)的时间复杂度。
     * 举例
     *0...k...i;
     * 当0-k的数的前缀异或和为1000，我们记录下1000以及k的坐标，
     * 如果0-i的数组异或和也为1000，那么说明k+1--i的数组异或和为0，
     * 则可以立刻找到以i结尾的最小异或和数组。
     *
     * @param arr
     * @return
     */

    public static int mostEOR(int[] arr) {
        int ans = 0;
        //记录前缀异或和
        int xor = 0;
        //记录异或和子树组的数量
        int[] mosts = new int[arr.length];
        //前缀异或和坐标的映射表
        HashMap<Integer, Integer> map = new HashMap<>();
        //0最开始不存在
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            //如果该前缀和之前出现过
            if (map.containsKey(xor)) {
                //pre为与现在异或和一致的上一个数组最后一个元素坐标
                int pre = map.get(xor);
                //-1表示不存在，直接设为1，存在就是上一次的最大子数组和+1
                mosts[i] = pre == -1 ? 1 : (mosts[pre] + 1);
            }
            //检测是否当前的mosts[i]为最大值
            if (i > 0) {
                mosts[i] = Math.max(mosts[i - 1], mosts[i]);
            }
            //更新前缀和
            map.put(xor, i);
            //记录最大值答案
            ans = Math.max(ans, mosts[i]);
        }
        return ans;
    }
}
