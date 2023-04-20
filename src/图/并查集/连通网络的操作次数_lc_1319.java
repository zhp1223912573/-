package 图.并查集;

/**
 * @author zhp
 * @date 2023-04-17 15:59
 * https://leetcode.cn/problems/number-of-operations-to-make-network-connected/
 */
public class 连通网络的操作次数_lc_1319 {
    int parent[];
    void init(int n){
        parent = new int[n];
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
     * 题目含义：如果两台已经属于直接或间接联通，那么当前连接就是冗余的。
     * 我们就可以使用冗余连接来连接不属于当前联通块的其他联通块。
     * 所以改题转化为求解冗余连接数是否能够使所有联通块互联。
     * 假设冗余连接为n，联通块为m，要是m个联通块互联，需要m-1条连接，
     * 因此n要大于等于m-1
     * @param n
     * @param connections
     * @return
     */
    public int makeConnected(int n, int[][] connections) {
        init(n);
        int rong = 0;
        int m = 0;
        for(int edge[] : connections){
            int a = edge[0];
            int b = edge[1];
            if(find(a)==find(b)) rong++;
            else union(a,b);
        }
        for(int i=0;i<n;i++){
            if(parent[i]==i) m++;
        }
        return rong>=m-1?m-1:-1;
    }
}
