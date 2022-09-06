package 回溯;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-25 14:34
 * https://leetcode.cn/problems/subsets-ii/solution/zi-ji-ii-by-leetcode-solution-7inq/
 */
public class 子集II_lc_79 {
    /**
     * 子集I的扩展，出现了重复的字符串，保证答案不出现重复的序列。
     * 则基本解题思路和子集I一致，但多加一层是否出现字符的重复放置的判断，
     * 该层判断的思路与全排列II的相似，都是判断先对数值排序，如果某个数已经被访问过，
     * 则不能重复访问，如果一个数和他前一个数一致且前一个数没有被访问过，则不能访问当前数。
     */
    public List<List<Integer>> subsets1(int[] nums) {
        int n = nums.length;

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for(int mask=0;mask<(1<<n);mask++){
            path.clear();
            boolean flag = true;
            for(int i=0;i<n;i++){
                if((mask&(1<<i))!=0){
                    if(i>0&&((mask>>(i-1)&1)==0)&&nums[i]==nums[i-1]){
                        flag=false;
                        break;
                    }
                    path.add(nums[i]);
                }
            }
            if(flag) ans.add(new ArrayList<>(path));
        }
        return ans;
    }

    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfs(false, 0, nums);
        return ans;
    }


    private void dfs(boolean chosepre, int cur, int[] nums) {
        if(cur==nums.length) {
            ans.add(new ArrayList<>(t));
        }

        dfs(false,cur+1,nums);
        if(!chosepre&&cur>0&&nums[cur]==nums[cur-1]){
            return;
        }
        t.add(nums[cur]);
        dfs(true,cur+1,nums);
        t.remove(t.size()-1);
    }

}
