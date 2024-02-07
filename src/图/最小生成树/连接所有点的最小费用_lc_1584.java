package 图.最小生成树;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2023-04-18 15:09
 * https://leetcode.cn/problems/min-cost-to-connect-all-points/
 * 最小生成树
 */
public class 连接所有点的最小费用_lc_1584 {


    int parent[];

    void init(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int x) {
        return x == parent[x] ? x : (parent[x] = find(parent[x]));
    }

    void union(int x, int y) {
        parent[find(x)] = find(y);
    }

    /**
     * kruskal
     * 对所有边进行排序，每次选择最短的边，将边的两个端点加入并查集，最后虽有的边遍历完得到的树就是最小生成树。
     * 只用于边少点多的稀疏图
     *
     * @param points
     * @return
     */
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        //n个顶点存在（n-1）*n/2条边，先将所有边提取出来
        int edges[][] = new int[(n - 1) * n / 2][3];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges[index++] = new int[]{i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])};
            }
        }
        //边按从小到大排序
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        init(n);
        int ans = 0;
        for (int edge[] : edges) {
            int a = edge[0];
            int b = edge[1];
            //当前边的两点不属于同一联通块
            if (find(a) != find(b)) {
                union(a, b);
                ans += edge[2];
            }
        }
        return ans;
    }

    /**
     * prime
     * 每轮选择距离当前点集最近的点，同时更新其他所有点能够通过当前新加入点后到达点集的距离，最后即可得到最小生成树的大小
     */
    public int minCostConnectPoints1(int[][] points) {
        int n = points.length;
        //先获取所有点之间的距离
        int graph[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                graph[i][j] = graph[j][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
            }
        }
        //节点是否已经加入点集
        boolean visit[] = new boolean[n];
        //节点距离当前点集的距离
        int dis[] = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int res = 0;
        for (int i = 0; i < n; i++) {
            //当前轮次的最近节点
            int min = -1;
            //找到距离点集的最近点
            for (int j = 0; j < n; j++) {
                //如果当前节点未访问过，并且当前节点是本轮第一次加入，或者当前节点大于其他节点大于当前点集的距离，更新
                if (!visit[j] && (min == -1 || dis[min] > dis[j])) min = j;

            }
            //找到距离点集最近的节点，加入点集，确认访问过
            visit[min] = true;
            //如果不是第一次加入的节点，需要记录加入的点的距离
            if (i != 0) res += dis[min];

            //找到了最近的点后，更新所有通过当前节点可以到达的其他节点
            for (int j = 0; j < n; j++) {
                dis[j] = Math.min(dis[j], graph[min][j]);
            }
        }

        return res;
    }


    public int minCostConnectPoints2(int[][] points) {
        int n = points.length;//节点数量
        int res = 0;//mst的总权重
        int[] cur = points[0];//当前节点
        boolean[] visited = new boolean[n];//标记遍历过的节点
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        int count = 1;//记录遍历过的节点数量
        int index = -1;//记录循环中下一轮的起始节点(即本轮节点连接的这个权重最小的节点)
        visited[0] = true;
        while (count < n) {
            int min = Integer.MAX_VALUE;//记录本轮节点连接的所有节点中的最小的权重
            for (int i = 1; i < n; i++) {//找出本轮节点连接的所有节点中还未在mst上的最小的权重
                if (!visited[i]) {
                    distance[i] = Math.min(distance[i], getWeight(cur, points[i]));
                    if (distance[i] < min) {
                        min = distance[i];
                        index = i;
                    }
                }
            }
            res += min;
            cur = points[index];
            visited[index] = true;
            count++;
        }
        return res;
    }

    public int getWeight(int x[], int y[]) {
        return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
    }
}
