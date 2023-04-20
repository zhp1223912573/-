package 迷宫;

/**
 * @author zhp
 * @date 2023-04-18 20:24
 * https://leetcode.cn/problems/detect-cycles-in-2d-grid/
 */
public class 二维网格中的探测环_lc_1559 {
    /**
     *dfs判断每个位置到字符是否存在环
     * @param grid
     * @return
     */
    public boolean containsCycle(char[][] grid){
         m = grid.length;
         n = grid[0].length;
        visit = new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                //当前位置没有被访问过，并且存在以当前位置起始的环
                if(!visit[i][j]&&dfs(i,j,-1,-1,grid[i][j],grid)) return true;
            }
        }
        return false;
    }

    int m ,n;
    int dirs[][] = {{1,0},{-1,0},{0,1},{0,-1}};
    boolean visit[][] ;

    boolean dfs(int x,int y,int px,int py,char ch, char [][]grid){
        if(visit[x][y]) return true;
        visit[x][y] = true;
        for(int dir[] : dirs){
            int nx = x+dir[0];
            int ny = y+dir[1];
            if(nx<0||ny<0||nx>=m||ny>=n||(px==nx&&py==ny)||grid[nx][ny]!=ch) continue;
            if(dfs(nx,ny,x,y,ch,grid)) return true;
        }
        return false;
    }


    /**
     * 并查集判断是否存在环
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

    public boolean containsCycle1(char[][] grid) {
       int  m = grid.length;
       int n = grid[0].length;
       boolean[][] visit = new boolean[m][n];
       init(n*m);

       for(int i=0;i<m;i++){
           for(int j=0;j<n;j++){
               visit[i][j]=true;
               for(int dir[] : dirs){
                   int nx = i+dir[0];
                   int ny = j+dir[1];
                   if(nx<0||ny<0||nx>=m||ny>=n||visit[nx][ny]||grid[nx][ny]!=grid[i][j]) continue;
                   int id1 = getId(nx, ny, n);
                   int id2 = getId(i,j,n);
                   if(find(id1)==find(id2)) return true;
                   union(id1,id2);
               }
           }
       }
       return false;
    }
}
