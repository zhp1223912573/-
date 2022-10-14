package 回溯;

import java.util.*;

/**
 * @author zhp
 * @date 2022-07-25 14:20
 * https://leetcode.cn/problems/permutations-ii/solution/
 */
public class 全排列II_lc_47 {
    /**回溯
     * 与全排列i一致，都是通过回溯枚举所有可能从而得到全排列，
     * 但此问题中出现了重复数值，为了避免出现重复答案的情况，
     * 我们先对数组进行排序，得到有序的数组，使一致的数值相邻。
     * 其次，将返回答案视作n个箱子，我们要从数组内取出数值放入箱子内，
     * 为了避免重复取值，除了设置visit数组表示对应位置上的数值是否已经被使用，
     * 还需要保证同一位置不能放置重复的数值，为了实现这一点，我们需要保证，当
     * 出现重复数值时，前一个数值必须已经被访问，才能保存不会有相同位置放置了重复数值的情况。
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans  = new ArrayList<>();
        List<Integer> box = new ArrayList<>();
        boolean visited [] = new boolean[nums.length];
        Arrays.sort(nums);
        backtract(ans,box,nums,visited,0);
        return ans;
    }

    /**
     *
     * @param ans
     * @param box
     * @param nums
     * @param visited
     * @param index
     */
    private void backtract(List<List<Integer>> ans, List<Integer> box, int[] nums, boolean[] visited, int index) {
        if(index==nums.length){
            ans.add(new ArrayList<>(box));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(visited[i] ||(i>0&&nums[i]==nums[i-1]&&!visited[i-1])){
                continue;
            }
            box.add(nums[i]);
            visited[i] = true;
            backtract(ans,box,nums,visited,index+1);
            visited[i] = false;
            box.remove(index);

        }
    }

    /**新的解题思路
     * 采用dfs，每一层都设置一个set保存插入的元素，如果出现重复元素说明之前一定出现过同一排列，直接跳过。
     * 思路：https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/solution/mian-shi-ti-38-zi-fu-chuan-de-pai-lie-hui-su-fa-by/
     */
    class Solution {
        List<String> res = new LinkedList<>();
        char[] ch;
        public String[] permutation(String s) {
            ch = s.toCharArray();
            dfs(0);
            return res.toArray(new String[res.size()]);
        }

        void dfs(int x){
            if(x==ch.length-1){
                res.add(String.valueOf(ch));
            }

            HashSet<Character> set = new HashSet<>();
            for(int i=x;i<ch.length;i++){
                if(set.contains(ch[i])) continue;
                set.add(ch[i]);
                swap(i,x);
                dfs(x+1);
                swap(i,x);

            }
        }

        void swap(int a,int b ){
            char temp = ch[a];
            ch[a] = ch[b];
            ch[b] = temp;
        }
    }
}
