package shiyan.Graphs.undirectedGraph;

import shiyan.In;
import shiyan.Queue.LinkedQueue;

import java.util.Queue;
import java.util.Stack;

/**
 * @author zhp
 * @date 2021-12-20 15:01
 * 基于广度优先搜索的图中路径查找
 *
 */
public class BreadthFirstPath {
    private boolean marked[];//判断顶点是否在连通图中
    private int edgeTo[];//有v-w边时，在索引为w的存入v 方便在后序获得路径
    private int s;//起始点

    public static void main(String[] args) {
        In in = new In("test3.txt");
        Graph g=new Graph(in);
        System.out.println(g.toString());

        for (int i = 0; i < g.V(); i++) {
            BreadthFirstPath breadthFirstPath = new BreadthFirstPath(g, i);
            for(int w=0;w<g.V();w++){
                if(w!=i){
                    System.out.println("起始点"+i+"到达"+w+"的路径为");
                    Stack<Integer> integers = (Stack<Integer>)breadthFirstPath.pathTo(w);
                    while(!integers.empty()){
                        System.out.print(integers.pop()+" ");
                    }
                    System.out.println();
                }
                System.out.println();
                }

        }
    }

    public BreadthFirstPath(Graph g,int s){
        this.s=s;
        marked=new boolean[g.V()];
        edgeTo=new int[g.V()];
        bfs(g,s);
    }

    /**
     * bfs
     * @param g
     * @param s
     */
    public void bfs(Graph g,int s){
        //利用队列实现bfs
        LinkedQueue<Integer> queue= new LinkedQueue<>();
        //先把起始顶点加入队列
        queue.add(s);
        //标记访问
        marked[s]=true;
        while(!queue.isEmpty()){
            int v=queue.poll();//删除顶点
            for(int w:g.adj(v)){
                if(marked[w]!=true){    //未标记才可进入
                    marked[w]=true;     //标记访问
                    edgeTo[w]=v;        //加入边
                    queue.add(w);
                }
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
