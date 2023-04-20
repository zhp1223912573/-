package 图.并查集;

import java.util.ArrayList;

/**
 * @author zhp
 * @date 2023-04-17 16:08
 * https://leetcode.cn/problems/pond-sizes-lcci/
 *
 *      经典的联通块问题，可以dfs，也可以并查集求解。
 *      dfs简洁一点，无需处理二维到一维的转换，
 *      而并查集需要转化二维数组到一维，解决坐标的映射关系。
 *
 */
public class 水域的大小_ms_16_19 {

    /**
     *dfs
     * @param land
     * @return
     */
    public int[] pondSizes(int[][] land) {
        ArrayList<Integer> ans = new ArrayList<>();
        int x = land.length;
        int y = land[0].length;
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if(land[i][j]==0){
                    ans.add(dfs(i,j,land));
                }
            }
        }
        int res [] = new int[ans.size()];
        ans.sort((a,b)->{return a-b;});
        for(int i=0;i<ans.size();i++){
            res[i] = ans.get(i);
        }
        return res;
    }
    private Integer dfs(int x, int y, int[][] land) {
        if(x>=land.length||y>=land[0].length||x<0||y<0||land[x][y]!=0) return 0;
        land[x][y]=-1;
        int dirs[][] = {{0,1},{0,-1},{1,-1},{1,0},{1,1},{-1,0},{-1,-1},{-1,1}};
        int cnt = 1;
        for(int dir[]:dirs){
            cnt+=dfs(x+dir[0],y+dir[1],land);
        }
        return cnt;
    }

    /**
     * 并查集
     */
    int parent[];
    int cnt[];//用来统计以当前位置为根时整个树的覆盖范围
    void init(int n){
        parent = new int[n];
        cnt = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
            cnt[i]=1;//初始化覆盖区域都是1
        }
    }
    int find(int x){
        return x==parent[x]? x : (parent[x]=find(parent[x]));
    }
    void union(int x,int y){
        //将以x的根节点为根节点的树加入以y的根节点为节点的树，需要更新y的根节点的覆盖范围
        int px = find(x);
        int py = find(y);
        parent[px] = find(py);
        cnt[py] += cnt[px];
    }
    //将二维转标转化为一维
    int getId(int x,int y,int col){
        return x*col+y;
    }
    public int[] pondSizes1(int[][] land) {
        int m = land.length;
        int n = land[0].length;
        int dirs[][] = {{0,1},{0,-1},{1,-1},{1,0},{1,1},{-1,0},{-1,-1},{-1,1}};
        init(n*m);
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(land[i][j]==0){
                    for(int dir[]:dirs){
                        int nx = dir[0]+i;
                        int ny = dir[1]+j;
                        if(nx<0||ny<0||nx>=m||ny>=n||land[nx][ny]!=0) continue;
                        int id1 = getId(i,j,n);
                        int id2 = getId(nx,ny,n);
                        if(find(id1)!=find(id2)) union(id1,id2);
                    }
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int id = getId(i,j,n);
                if(find(id)==id&&land[i][j]==0) ans.add(cnt[id]);
            }
        }
        int res [] = new int[ans.size()];
        ans.sort((a,b)->{return a-b;});
        for(int i=0;i<ans.size();i++){
            res[i] = ans.get(i);
        }
        return res;
    }


    public static void main(String[] args) {
        水域的大小_ms_16_19 a = new 水域的大小_ms_16_19();
        a.pondSizes(new int[][]{{0,2,1,0},{0,1,0,1},{1,1,0,1},{0,1,0,1}});
    }
}
