package shiyan.Graphs.undirectedGraph;

import shiyan.In;

/**
 * @author zhp
 * @date 2021-12-20 14:00
 * 基于深度优先搜索的连通判断
 * 判断图中某点是否与另外一点连通
 * 判断某点与图中其他点联通的数量
 */
public class DepthFirstSearch {
    private boolean marked[];//判断与目标顶点是否连通
    private int count;  //与目标顶点连通的顶点数量

    public static void main(String[] args) {
        In in = new In("test2.txt");
        Graph g = new Graph(in);
        System.out.println(g.toString());
        for (int i = 0; i <g.V() ; i++) {
            DepthFirstSearch depthFirstSearch=new DepthFirstSearch(g,i);
            System.out.println("与目标顶点"+i+"连通的顶点为：");
        for(int w:g.adj(i)){
            if(depthFirstSearch.marked(w)){
                System.out.println(w+" ");
            }
        }
        System.out.println();
    }
    }

    public DepthFirstSearch(Graph g,int s){
        //初始化
        marked=new boolean[g.V()];
        dfs(g,s);
    }

    /**
     *
     * @param g
     * @param v
     */
    public void dfs(Graph g,int v){
        //将访问的顶点设置为true
        marked[v]=true;
        //总数自增
        count++;
        //遍历该顶点的所有相邻顶点
        for(int w:g.adj(v)){
            //若当前节点为true说明已经在之前的dfs中访问过 无需向下递归
            if(marked[w]!=true){
                dfs(g,w);
            }
        }
    }

    /**
     * 返回目标顶点的是否与某一顶点连通
     * @param v
     * @return
     */
    public boolean marked(int v){
        return marked[v];
    }

    /**
     * 返回与目标顶点的连通顶底总数
     * @return
     */
    public int count(){
        return count;
    }

}
