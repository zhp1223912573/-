package 回溯;

import java.security.interfaces.DSAParams;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-25 16:25
 * https://leetcode.cn/problems/combinations/solution/
 */
public class 组合_lc_77 {
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
        backtract(ans,path,cur+1,n,k);
        //旋转当前的数
        path.add(cur);
        backtract(ans,path,cur+1,n,k);
        path.remove(path.size()-1);

    }
}
