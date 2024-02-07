package 字符串;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhp
 * @date 2022-07-28 0:14
 * https://leetcode.cn/problems/longest-consecutive-sequence/
 *
 */
public class 最长连续序列_lc_128 {
    /**
     * 使用set对数组内元素去重，并对set内的元素进行遍历，
     * 为了得到最长的序列长度，则set内一定存在一个序列为，x，x+1,x+2,...,x+y,
     * 则该序列最长为y+1.
     * 找到该序列后，我们如果从x+1开始继续寻找更长的序列的话，就是冗余的，不可能找到更长的序列，
     * 因为如果更长的序列如果存在的话，那一定是以x开头时的更长。
     * 所以，我们每次寻找一个连续的序列时，都需要保证序列的开头值x，前面不存在x-1的数值，
     * 否则不可能存在以x开头的最长的序列。
     * 通过上述的步骤优化，通过外层循环和内存循环，保证外层循环只有序列开头数值才能进入，
     * 而内存循环只有非开头的数值可以进入，这样所有数值都只能进入一次，总体上时间开销为O(n)
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num :nums){
            set.add(num);
        }
        int ans = 0;
        for(int num:set){
            if(!set.contains(num-1)){
                int cur = num;
                int maxlen = 1;

                while(set.contains(cur+1)){
                    cur += 1;
                    maxlen++;
                }
                ans = Math.max(ans,maxlen);
            }
        }
        return ans;
    }

}
