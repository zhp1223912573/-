package 每日任务;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhp
 * @date 2022-10-27 19:58
 * https://leetcode.cn/problems/lexicographical-numbers/submissions/
 */
public class 字典序排数_lc_386 {

    /**
     * dfs深搜获取字典序
     * 画出多查树推动规律，
     * 发现第一层为1-9，往后的层数都是0-9
     * 并且在当前数小于n时，尽可能的将当前数*10，逼近n，
     * 直到大于n，才返回。
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList();
        for(int i=1;i<10;i++){
            dfs(i,n,ans);
        }
        return ans;
    }

    void dfs(int start,int n,List<Integer> ans){
        if(start>n) return;
        ans.add(start);
        for(int i=0;i<10;i++){
            dfs(start*10+i,n,ans);
        }
    }

    /**
     * 迭代
     * 与递归思路相同，
     * 尽可能将当前数逼近n（通过*10），如果当前数大于n，
     * 我们就回退当前数，并在回退后自增即可。
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder1(int n) {
        List<Integer> ans = new ArrayList();
        for(int i=0,j=1;i<n;i++){
            ans.add(j);
            if(j*10<=n){
                j*=10;
            }else{
                while(j%10==9 || j+1>n) j/=10;
                j++;
            }
        }
        return ans;
    }

}
