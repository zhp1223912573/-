package 回溯;

import java.security.interfaces.DSAParams;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static sun.misc.Version.println;

/**
 * @author zhp
 * @date 2022-07-25 16:25
 * https://leetcode.cn/problems/combinations/solution/
 */
public class 组合_lc_77 {

    public static void main(String[] args) {

    }
    /**
     * 回溯
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            backtract(ans,path,1,n,k);
            return ans;
    }

    private void backtract(List<List<Integer>> ans, List<Integer> path, int cur, int n, int k) {
        //剪枝
        if(path.size()+n-cur+1<k) return ;
        if(path.size()==k){
            ans.add(new ArrayList<>(path));
            return;
        }

        //不选择当前的数
        backtract(ans,path,cur+1, n,k);
        //旋转当前的数
        path.add(cur);
        backtract(ans,path,cur+1,n,k);
        path.remove(path.size()-1);

    }
    class Solution {

        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> res = new ArrayList<>();
            if (k <= 0 || n < k) {
                return res;
            }
            // 从 1 开始是题目的设定
            Deque<Integer> path = new ArrayDeque<>();
            dfs(n, k, 1, path, res);
            return res;
        }

        private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
            // 递归终止条件是：path 的长度等于 k
            if (path.size() == k) {
                res.add(new ArrayList<>(path));
                return;
            }

            // 遍历可能的搜索起点
            for (int i = begin; i <= n; i++) {
                // 向路径变量里添加一个数
                path.addLast(i);
                // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
                dfs(n, k, i + 1, path, res);
                // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
                path.removeLast();
            }
        }

}
