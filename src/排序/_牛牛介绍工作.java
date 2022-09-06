package 排序;


import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author zhp
 * @date 2022-07-05 19:46
 * 为了找到自己满意的工作，牛牛收集了每种工作的难度和报酬。牛牛选工作的标准是在难度不超过自身能力
 * 值的情况下，牛牛选择报酬最高的工作。在牛牛选定了自己的工作后，牛牛的小伙伴们来找牛牛帮忙选工作，
 * 牛牛依然使用自己的标准来帮助小伙伴们。牛牛的小伙伴太多了，于是他只好把这个任务交给了你。
 * class Job {
 * public int money;// 该工作的报酬
 * public int hard; // 该工作的难度
 * public Job(int money, int hard) {
 * this.money = money;
 * this.hard = hard;
 * }
 * }
 * 给定一个Job类型的数组jobarr，表示所有的工作。给定一个int类型的数组arr，表示所有小伙伴的能力。
 * 返回int类型的数组，表示每一个小伙伴按照牛牛的标准选工作后所能获得的报酬
 */
public class _牛牛介绍工作 {
    /**
     * 经典面向对象的排序问题。
     * 根据对象的属性编写排序器，使对象按照指定要求排序。
     * <p>
     * 先对所有工作按照难度从小到大，报酬从大到小进行排序，得到指定数组jobs。
     * 将jobs内同样难度的工作报酬却不是最高的其他工作去除掉，使用顺序表保存。
     * 读入ability数组，为每个人找到适合且报酬最大的工作。
     */

    class Job {
        public int money;// 该工作的报酬
        public int hard; // 该工作的难度

        public Job(int money, int hard) {
            this.money = money;
            this.hard = hard;
        }
    }

    public static class jobComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money - o1.money);
        }
    }

    public static int[]  getMoneys(Job []jobs,int []ability) {
        Arrays.sort(jobs,new jobComparator());
        TreeMap<Integer,Integer> map = new TreeMap<>();
        map.put(jobs[0].hard,jobs[0].money);
        Job pre = jobs[0];//记录不去除的工作
        for(int i=1;i<jobs.length;i++){
            if(pre.hard!=jobs[i].hard && pre.money>jobs[i].money){
                pre = jobs[i];
                map.put(jobs[i].hard,jobs[i].money);
            }
        }
        int ans[] = new int[ability.length];
        for(int i=0;i<ability.length;i++){
            Integer key = map.floorEntry(ability[i]).getKey();//找到小于等于ability【i】的元素
            ans[i] = key==null? map.get(key):0;
        }

        return ans;
    }
}

