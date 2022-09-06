package shiyan.Graphs.undirectedGraph;

import shiyan.In;

import java.util.Stack;


/**
 * @author zhp
 * @date 2021-12-20 13:58
 * 基于深度优先搜索的路径查找（无向图）
 * 获取目标顶点s到v的路径
 */
public class DepthFirstPaths {
    private boolean marked[];//判断顶点是否在连通图中
    private int edgeTo[];//有v-w边时，在索引为w的存入v 方便在后序获得路径
    private int s;

    public static void main(String[] args) {
        In in = new In("test3.txt");
        Graph g=new Graph(in);
        System.out.println(g.toString());

        for (int i = 0; i < g.V(); i++) {
            DepthFirstPaths depthFirstPaths = new DepthFirstPaths(g, i);
            for(int w:g.adj(i)){
                System.out.println("起始点"+i+"到达"+w+"的路径为");
              Stack<Integer> integers = (Stack<Integer>)depthFirstPaths.pathTo(w);
                while(!integers.empty()){
                    System.out.print(integers.pop()+" ");
                }
                System.out.println();
            }
            System.out.println();
        }

    }

    /**
     * 初始化
     * @param g 传入的图
     * @param s 目标顶点
     */
    public DepthFirstPaths(Graph g,int s){
        this.s=s;
        marked=new boolean[g.V()];
        edgeTo=new int[g.V()];
        dfs(g,s);
    }

    /**
     * dfs
     * @param g
     * @param v
     */
    public void dfs(Graph g,int v){
        marked[v]=true;
        for(int w:g.adj(v)){
            if(marked[w]!=true){
                //指向到底目前顶点的边的另一顶点
                edgeTo[w]=v;
                dfs(g,w);
            }
        }

    }

    /**
     * 返回一个由起始点s到达v的路径
     * @param v
     * @return
     */
    public Iterable<Integer> pathTo(int v){
        //不存在到的v顶点的路径
        if(!haspathto(v)) return null;
        //存在则创建一个stack 通过edgeTo数组找到路径
        Stack<Integer> path= new Stack<>();
        for (int i=v;i!=s;i=edgeTo[i]){
            //反向找到一条到达目标顶点的路径
            path.push(i);
        }
        //最后压入目标顶点
        path.push(s);
        return path;
    }

    /**
     * 判断是否含有一条从起始点s到v的路径
     * @param v
     * @return
     */
    public boolean haspathto(int v){
        return marked[v];
    }
}
