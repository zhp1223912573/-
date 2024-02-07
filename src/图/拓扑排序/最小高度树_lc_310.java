package 图.拓扑排序;

import java.util.*;

/**
 * @author zhp
 * @date 2023-04-16 18:29
 * https://leetcode.cn/problems/minimum-height-trees/
 */
public class 最小高度树_lc_310 {
    /**
     * 该题的难点在于如何想到通过拓扑排序实现
     * 为了让树的高度尽可能的小，我们应当选取最内层的节点作为根节点（具体的证明过程不会）
     * 所以我们尝试取将外层的节点移除，直到最后剩下的最内层节点就是可以使当前树最矮的节点合集。
     *
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //只有一个节点直接返回
        if (n == 1) return Arrays.asList(0);
        //后续节点及出度数组初始化
        List<List<Integer>> nx = new ArrayList<>();
        int de[] = new int[n];
        for (int i = 0; i < n; i++) {
            nx.add(new LinkedList<>());
        }

        //树的边是无向边，所以需要都加入
        for (int edge[] : edges) {
            int a = edge[0];
            int b = edge[1];
            de[a]++;
            de[b]++;
            nx.get(a).add(b);
            nx.get(b).add(a);
        }

        //将出度为1的，也就是最外层的节点加入队列
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (de[i] == 1) deque.add(i);
        }


        //通过类似二叉树的层序遍历的方法，逐层去除最外层的节点，剩下最后的一层就是可以作为根节点的节点集合
        List<Integer> ans = new LinkedList();
        while (!deque.isEmpty()) {
            ans = new LinkedList<>();
            int len = deque.size();
            for (int i = 0; i < len; i++) {
                int node = deque.pollFirst();
                ans.add(node);
                for (int next : nx.get(node)) {
                    de[next]--;
                    if (de[next] == 1) deque.add(next);
                }
            }
        }
        return ans;
    }
}
