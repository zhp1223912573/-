package 回溯;

import java.util.*;

/**
 * @author zhp
 * @date 2022-07-25 15:59
 */
public class 组合总和II_lc_40 {
    /**
     * 相比于组合总和I，数组多了重复的元素，且元素只能取一次，不能不限次取出，
     * 并且答案不能出现重复的序列。
     * 同之前的全排列II等题一致，对数组排序，将相同的元素放置在一起，
     * 加多一个visited数组标注已经被访问过的元素，并且相同的多个元素必须必须要在其前面的相同元素被
     * 使用了，才能使用。
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        boolean visited[] = new boolean[candidates.length];
        backtract(ans,path,target,candidates,0,visited);
        return ans;
    }

    private void backtract(List<List<Integer>> ans, List<Integer> path, int target,int []candidates,int start,boolean[]visited) {
        if(target==0){
            ans.add(new ArrayList<>(path));
            return ;
        }

        for(int i = start; i<candidates.length;i++){
            if(visited[i]||(i>0&&candidates[i]==candidates[i-1]&&!visited[i-1])) continue;
            //剪枝
            if(candidates[i]>target){
                return ;
            }
            visited[i] = true;
            path.add(candidates[i]);
            backtract(ans,path,target-candidates[i],candidates,i,visited);
            path.remove(path.size()-1);
            visited[i]= false;

        }
    }

    /**
     * 通过set实现去重操作
     * @param ans
     * @param path
     * @param candidates
     * @param start
     * @param target
     */
    void back(List<List<Integer>> ans, List<Integer> path,int [] candidates,int start,int target){
        if(target==0){
            ans.add(new ArrayList(path));
        }

        Set<Integer> occ = new HashSet<>();
        for(int i=start;i<candidates.length;i++){
            if(target<candidates[i]) return;
            if(occ.contains(candidates[i])){
                continue;
            }
            occ.add(candidates[i]);

            path.add(candidates[i]);
            //此处是+1,因为我们不是使用数组记录每个位置是否已经被使用，
            //set的作用是记录当前层中是否已经存在重复元素，存在的话说明已经出现该数值开头的其他组合
            // 不应当重复使用，直接跳过
            back(ans,path,candidates,i+1,target-candidates[i]);

            path.remove(path.size()-1);
        }

    }
}
