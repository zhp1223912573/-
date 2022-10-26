package 每日任务;

import java.util.List;

/**
 * @author zhp
 * @date 2022-10-24 13:19
 * https://leetcode.cn/problems/hanota-lcci/submissions/
 */
public class ms08_06_汉诺塔问题 {
    /**
     * 经典的递归问题
     * 还是一样套路，
     * 将1-n个盘子移动到最后的柱子上，我们先解决1--n-1的盘子的问题，
     * 最后只剩下一个盘子，直接将该盘子移动到最后的柱子。
     * 设置递归函数的过程需要注意柱子状态的变化。
     * @param A
     * @param B
     * @param C
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        if(A.isEmpty()) return ;
        func(A.size(),A,B,C);
    }

    void func(int n,List<Integer> start,List<Integer> other,List<Integer>end){
        if(n==1){
            end.add(start.remove(start.size()-1));
        }else{
            func(n-1,start,end,other);
            end.add(start.remove(start.size()-1));
            func(n-1,other,start,end);
        }
    }
}
