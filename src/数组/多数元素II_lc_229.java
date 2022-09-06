package 数组;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-27 13:45
 * https://leetcode.cn/problems/majority-element-ii/
 */
public class 多数元素II_lc_229 {
    /**摩尔投票法
     * 还是对投票法的运用，一个数组中最多存在两个出现次数超过n/3的数，
     * 所以我们维护两个候选者candidate和代表该候选者的票数vote，
     * 一旦一号候选者票数为0，则记录当前的候选者为候选者1，
     * 一旦候选者1与新出现的候选者不一致，且候选者2的票数为0，则更新候选者2为当前候选者，
     * 如果出现既不与候选者1与候选者2一致的人，那么就将候选者12的票数都减减，
     * 表示清除这三个数。
     * 最后剩下的两个候选者还需要再遍历，检验是否出现次数超过n/3;
     *
     *
     * 有一个对摩尔投票法非常形象的比喻：多方混战。
     * 首先要知道，在任何数组中，出现次数大于该数组长度1/3的值最多只有两个。
     * 我们把这道题比作一场多方混战，战斗结果一定只有最多两个阵营幸存，其他阵营被歼灭。数组中的数字即代表某士兵所在的阵营。
     * 我们维护两个潜在幸存阵营A和B。我们遍历数组，如果遇到了属于A或者属于B的士兵，则把士兵加入A或B队伍中，该队伍人数加一。继续遍历。
     * 如果遇到了一个士兵既不属于A阵营，也不属于B阵营，这时有两种情况：
     * 情况一：A阵营和B阵营都还有活着的士兵，那么进行一次厮杀，参与厮杀的三个士兵全部阵亡：A阵营的一个士兵阵亡，B阵营的一个士兵阵亡，这个不知道从哪个阵营来的士兵也阵亡。继续遍历。
     * 情况二：A阵营或B阵营已经没有士兵了。没有士兵的阵营暂时从地球上消失了。那么把当前遍历到的新士兵算作新的潜在幸存阵营，这个新阵营只有他一个人。继续遍历。
     * 大战结束，最后A和B阵营就是初始人数最多的阵营。判断一下A，B的人数是否超过所有人数的三分之一就行了。
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        int candidate1 = -1;
        int candidate2 = -1;
        int vote1 = 0;
        int vote2 = 0;

        for(int num:nums){
            if(vote1>0&&num==candidate1){
                vote1++;
            }else if(vote2>0&&candidate2==num){
                vote2++;
            } else if(vote1==0){
                candidate1=num;
                vote1++;
            }else if(vote2==0){
                candidate2 = num;
                vote2++;
            } else{
                vote1--;
                vote2--;
            }

        }

        int count1 = 0;
        int count2 = 0;
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        for(int num:nums){
            if(num==candidate1){
                count1++;
            }

            if(num==candidate2){
                count2++;
            }
        }
        if(count1>=(n/3)){
            ans.add(candidate1);
        }

        if(count2>=(n/3)){
            ans.add(candidate2);
        }

        return ans;
    }
}
