package 栈和队列;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author zhp
 * @date 2021-11-20 10:56
 * https://leetcode.cn/problems/top-k-frequent-elements/
 */
public class lc_0347
{

    /*最朴素的做法
    直接用map记录数字出现的次数 然后遍历次数数组 得到最大值
    * 在循环中加入res数组*/
    public int[] mapSolution(int []nums , int k){
        int res[]=new int[k];   //结果数组
        Map<Integer,Integer> map=new HashMap<>(); //存储次数

        //统计各个数出现的次数
        for(int num : nums){
            if(map.containsKey(num)){
                map.put(num,map.get(num)+1);
            }else{
                map.put(num,1);
            }
        }

        int maxtimes=0;//设置某个数出现的最大次数
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(entry.getValue()>maxtimes){
                maxtimes=entry.getValue();
            }
        }

        //设置res数组
        while(k>0){
            for(Map.Entry<Integer,Integer> entry : map.entrySet()){
                if(entry.getValue()==maxtimes){
                    res[k-1]=entry.getKey();
                    k--;
                }
            }
            maxtimes--;
        }
        return res;
    }

    /*排序.堆--使用优先队列实现一个最小堆 堆大小为k
    * 向堆中添加数字及其出现的次数
    * 若堆中元素小于k个 则直接添加
    * 若等于k个 则与堆顶元素进行比价 */
    public int[] DuiBypriorityQueue(int []nums,int k){
        int res[]=new int[k];

        Map<Integer,Integer>map = new HashMap<>();
        for(int num : nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }

        //声明一个优先队列 按小到大排序
        // int[]  第一个元素存放出现的数字 第二个元素存放出现的次数
         PriorityQueue<int []> priorityQueue=new PriorityQueue<>(new Comparator<int[]>() {
             @Override
             public int compare(int[] o1, int[] o2) {
                 return o1[1]-o2[1];
             }
         });

        for(Map.Entry<Integer,Integer> entry:map.entrySet()) {
            int num=entry.getKey();
            int count=entry.getValue();
            if(priorityQueue.size()<k){
                priorityQueue.offer(new int[]{num,count});
            }else{
               if( priorityQueue.peek()[1]<count){
                   priorityQueue.poll();
                   priorityQueue.offer(new int[]{num,count});
               }
            }
        }

        for (int i = 0; i < k; ++i) {
            res[i] = priorityQueue.poll()[0];
        }

        return res;
    }


}
