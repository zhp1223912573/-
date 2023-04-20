package 图.并查集;

/**
 * @author zhp
 * @date 2023-04-17 15:49
 * https://leetcode.cn/problems/redundant-connection/
 */
public class 冗余连接_lc_684 {
    int parent[];
    void init(int n){
        //这里是n+1，保证n-1个都能加入
        parent = new int[n+1];
        for(int i=0;i<n;i++){
            parent[i] = i;
        }
    }
    int find(int x){
        return x==parent[x]? x : (parent[x]=find(parent[x]));
    }
    void union(int x,int y){
        parent[find(x)] = find(y);
    }
    /**
     * 并查集合并已经连接的节点，出现当前边使两个已经联通的节点连接，则该边为冗余边
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        init(n);
        for(int edge[] :edges){
            int a = edge[0];
            int b = edge[1];
            if(find(a)==find(b)) return edge;
            else union(a,b);
        }
        return new int[]{};
    }
}
