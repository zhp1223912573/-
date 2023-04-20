package 图.拓扑排序;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhp
 * @date 2023-04-16 18:14
 * https://leetcode.cn/problems/course-schedule-ii/
 */
public class 课程表II_lc_210 {
    /**
     * 经典的拓扑排序算法题
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //下一节点数组及节点入度初始化
        List<List<Integer>> nx = new ArrayList<>();
        int in[] = new int[numCourses];
        for(int i=0;i<numCourses;i++){
            nx.add(new LinkedList<>());
        }

        //读入每个节点的下一个节点
        for(int pre[]: prerequisites){
            int a = pre[0];
            int b = pre[1];
            in[a]++;
            nx.get(b).add(a);
        }

        //将入度为0的节点读入队列
        Deque<Integer> deque = new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(in[i]==0) deque.add(i);
        }

        //开始迭代，直到队列为空
        int index=0;
        int res[] = new int[numCourses];
        while(!deque.isEmpty()){
            int node = deque.pollFirst();
            res[index++] = node;
            for(int next:nx.get(node)){
                in[next]--;
                if(in[next]==0) deque.add(next);
            }
        }
        if(index<numCourses) return new int[]{};
        return res;
    }
}
