package 数组;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zhp
 * @date 2022-07-28 13:14
 * https://leetcode.cn/problems/sliding-window-maximum/
 *
 */
public class 滑动窗口最大值_lc_239 {
    /**单调队列
     * 双端队列保存数值元素下表，为了得到移动窗口内的最大值，我们可以维护一个符合如下规则的队列：
     * 队列中的元素指向的数组值始终按非递增顺序排序，并且队列中的元素个数为k个，
     * 这样队列的首个元素始终就是滑动窗口的最大值。
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque  =  new ArrayDeque<>();
        int ans [] = new int[nums.length-k+1];
        for(int i=0;i<k;i++){
            while(!deque.isEmpty()&&nums[deque.peekLast()]<nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        ans[0] = nums[deque.peekFirst()];

        for(int i=k;i<nums.length;i++){
            while(!deque.isEmpty()&&nums[deque.peekLast()]<nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);

            while(deque.peekFirst()<=i-k){
                deque.pollFirst();
            }
            ans[i-k+1] = nums[deque.peekFirst()];
        }
        return ans;
    }

    /**分块+预处理
     *
     *
     */
    public int[] maxSlidingWindow1(int[] nums, int k){
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        for(int i =0;i<n;i++){
            if(i%k==0){
                prefixMax[i] = nums[i];
            }else{
                prefixMax[i] = Math.max(prefixMax[i-1],nums[i]);
            }
        }

        for(int i=n-1;i>=0;i--){
            if(i==n-1||(i+1)%k==0){
                suffixMax[i] = nums[i];
            }else{
                suffixMax[i] = Math.max(suffixMax[i+1],nums[i]);
            }
        }

        int ans[] = new int[n-k+1];
        for(int i=0;i<n-k+1;i++){
            ans[i] = Math.max(suffixMax[i],prefixMax[i+k-1]) ;
        }
        return ans;
    }
}
