package 图.并查集;

/**
 * @author zhp
 * @date 2023-04-17 15:42
 * https://leetcode.cn/problems/number-of-provinces/
 */
public class 省份数量_lc_547 {

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
     * 经典并查集问题，套用模板即可
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        init(n);
        for(int i=0;i<n;i++){
            for(int j=i+1;j<isConnected[i].length;j++){
                if(isConnected[i][j]==1){
                    union(i,j);
                }
            }
        }
        int cnt = 0;
        for(int i=0;i<n;i++){
            if(parent[i]==i) cnt++;
        }
        return cnt;
    }
}
