package 二分;

/**
 * @author zhp
 * @date 2022-07-21 13:44
 * https://leetcode.cn/problems/find-the-duplicate-number/
 */
public class 寻找重复数_lc_287 {
    /**
     * 二分查找
     * 题目要求额外空间为O(1)
     *时间复杂度O(nlogn)
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int left = 1;
        int right = n;
        //int ans = -1;
        while(left<right){
            int mid = (right-left)/2+left;
            int cnt = 0;
            for(int i=0;i<n;i++) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }

            if(cnt<=mid){
                left = mid+1;
            }else{
                // ans = mid;
                right = mid;
            }
        }
        return left;
    }

    /**快慢指针
     * 一种转换模型的解法。
     * 观察数组[1,3,2,2],建立坐标i到num[i]的映射关系，
     * 0-1
     * 1-3
     * 2-2
     * 3-2
     * 我们从下标0出发，根据num[0]进入下一个位置：
     * 0-1-3-2-2-2...
     * 则构建成一个有环的链表。
     * 则从理论上讲，数组中如果有重复的数，那么就会产生多对一的映射，这样，形成的链表就一定会有环路了
     * 综上
     * 1.数组中有一个重复的整数 <==> 链表中存在环
     * 2.找到数组中的重复整数 <==> 找到链表的环入口
     *
     * 所以此题就变成了找到链表中环入口的问题，解法与142题一致，
     * 先使用快慢指针，等到两者相遇，再设置一个新指针从头开始与慢指针一起移动，两者相遇的地方就是环入口。
     *
     */
    public int findDuplicate1(int[] nums) {
        int fast = 0;
        int slow =0;
        slow = nums[slow];
        fast = nums[nums[fast]];//
        while(fast!=slow){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        int pre1 = 0;
        int pre2 = slow;
        while(pre1!=pre2){
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }
        return nums[pre1];
    }

}
