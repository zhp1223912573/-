package 图.拓扑排序;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhp
 * @date 2023-04-16 16:46
 * https://leetcode.cn/problems/course-schedule/submissions/
 */
public class 课程表I_lc_207 {
    public static void main(String[] args) {
        课程表I_lc_207 a = new 课程表I_lc_207();
        a.canFinish1(2, new int[][]{{1, 0}});
    }

    /**
     * 拓扑排序经典模板
     * 统计节点入度，以及每个节点的后续节点。
     * 将入度为0的节点加入队列，不断读取队列中入度为0的节点的下一个节点，
     * 同时更新下一节点的入度数，如果入度变为0，将该节点加入队列。
     * 重复上述过程中可以统计加入到队列的节点数量，从而判断是否存在环。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> nx = new LinkedList<>();
        int in[] = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            nx.add(new LinkedList());
        }
        //更新入度和下一节点
        for (int i = 0; i < prerequisites.length; i++) {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            in[a]++;
            nx.get(b).add(a);
        }
        //先将入度为0的节点加入队列
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) deque.addLast(i);
        }
        //不断迭代，直到队列中不存在入度为0的节点
        int cnt = 0;//统计整个过程中入度为0的节点的数量，小于总结点数量说明存在环
        while (!deque.isEmpty()) {
            int node = deque.pollFirst();
            cnt++;
            for (int next : nx.get(node)) {
                in[next]--;
                if (in[next] == 0) deque.addLast(next);
            }
        }
        return cnt == numCourses;
    }

    /**
     * 基于dfs判断是否存在环，如果不存在，则可以得到拓扑排序
     */
    int visit[];
    List<List<Integer>> nx = new ArrayList<>();

    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        //0-未访问 1-当前访问 -1-其他访问
        visit = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            nx.add(new LinkedList<>());
        }

        for (int pre[] : prerequisites) {
            int a = pre[0];
            int b = pre[1];
            nx.get(b).add(a);
        }

        for (int i = 0; i < numCourses; i++) {
            if (dfs(i)) return false;
        }

        return true;
    }

    /**
     * @param node
     * @return
     */
    private boolean dfs(int node) {
        //如果当前节点在此轮dfs中已经被访问过，我们再次访问该节点，说明存在环
        if (visit[node] == 1) return true;
        //如果当前节点在其他轮dfs中访问过，再次访问该节点，直接返回
        if (visit[node] == -1) return false;
        //设置为当前轮访问
        visit[node] = 1;
        //遍历当前节点的所有下一节点
        for (int next : nx.get(node)) {
            if (dfs(next)) return true;
        }
        //当前节点的所有下一节点都访问完了，说明不存在环，设置为-1；
        visit[node] = -1;
        return false;
    }
}
