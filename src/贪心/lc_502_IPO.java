package 贪心;

import shiyan.In;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhp
 * @date 2022-06-12 22:30
 * 贪心问题
 * https://leetcode.cn/problems/ipo/
 */
public class lc_502_IPO {
    //项目
    class Project{
        public int profit;
        public int cost;

        public Project(int profit, int cost) {
            this.profit = profit;
            this.cost = cost;
        }
    }

   //根据项目开销从小到达排序
    private static class minCostComparator implements  Comparator<Project>{
       @Override
       public int compare(Project o1, Project o2) {
           return o1.cost - o2.cost;
       }
   }

    //根据项目利润从大到小进行排序
    private static class maxProfitComparator implements  Comparator<Project>{
        @Override
        public int compare(Project o1, Project o2) {
            return o2.profit-o1.profit;
        }
    }
    /**
     *
     * @param k
     * @param w
     * @param profits
     * @param capital
     * @return
     * 思路：
     * 1.将项目按照开销从小到大进行排序
     * 2.将项目中小于启动资金的项目加入利用集合，按照收益从大到小进行排序
     * 3.重复上述步骤，直到限制的项目次数k
     * 利用大小根堆进行排序
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Project> minCostQ = new PriorityQueue<>(new minCostComparator());
        PriorityQueue<Project> maxProfitQ = new PriorityQueue<>(new maxProfitComparator());
        for(int i=0;i<profits.length;i++){
            minCostQ.add(new Project(profits[i],capital[i]));
        }

       for(int i=0;i<k;i++){
           while(!minCostQ.isEmpty() && minCostQ.peek().cost<=w){
               maxProfitQ.add(minCostQ.poll());
           }
            if(maxProfitQ.isEmpty()){
                return w;
            }
            w+=maxProfitQ.poll().profit;
       }
            return w;
    }



}
