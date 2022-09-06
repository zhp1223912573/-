package 回溯;

import shiyan.In;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-25 0:44
 * https://leetcode.cn/problems/subsets/
 */
public class 子集_lc_78 {

    /**回溯
     * 尝试取和不取当前位置的数值，到达最后时的路径就是我们要的结果。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans  = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backTract(ans,path, 0,nums);
        return ans;
    }
    public void backTract(List<List<Integer>> ans,List<Integer> path,int index,int nums[]){
        if(index==nums.length){
            ans.add(new ArrayList<>(path));
        }else{
                //加入当前位置数值
                path.add(nums[index]);
                //取当前数值
                backTract(ans,path,index+1,nums);
                //取出当前位置数值
                path.remove(path.size()-1);
                //不取当前数值
                backTract(ans,path,index+1,nums);
        }
    }

    /**
     * 二进制取值法
     *
     */
    public List<List<Integer>> subsets1(int[] nums) {
        int n = nums.length;
        int max = (1<<n);
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0;i<max;i++){
            int num = i;
            List<Integer> path = new ArrayList<>();
            int index = 0;
            while(num!=0){
                if((num&1)==1){
                    path.add(nums[index]);
                }
                index++;
                num>>=1;
            }
            ans.add(path);
        }
        return ans;
    }
}

