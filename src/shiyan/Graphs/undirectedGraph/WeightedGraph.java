package shiyan.Graphs.undirectedGraph;

import shiyan.Graphs.Edge;
import shiyan.In;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhp
 * @date 2021-12-20 16:03
 * 加权无向图
 */
public class WeightedGraph {
    private int V;
    private int E;
    private List<Edge>[] adj; //邻接表

    public static void main(String[] args) {
        WeightedGraph g=new WeightedGraph(8);
        g.addEdge(new Edge(4,5,0.35));
        g.addEdge(new Edge(4,7,0.37));
        g.addEdge(new Edge(5,7,0.28));
        g.addEdge(new Edge(0,7,0.16));
        g.addEdge(new Edge(1,5,0.32));
        g.addEdge(new Edge(0,4,0.38));
        g.addEdge(new Edge(2,3,0.17));
        g.addEdge(new Edge(1,7,0.19));
        g.addEdge(new Edge(0,2,0.26));
        g.addEdge(new Edge(1,2,0.36));
        g.addEdge(new Edge(1,3,0.29));
        g.addEdge(new Edge(2,7,0.34));
        g.addEdge(new Edge(6,2,0.40));
        g.addEdge(new Edge(3,6,0.52));
        g.addEdge(new Edge(6,0,0.58));
        g.addEdge(new Edge(6,4,0.93));
        System.out.println(g.toString());
        WeightedGraph g1=new WeightedGraph(new In("test5.txt"));
        System.out.println(g1);

        WeightedGraph weightedGraph = new WeightedGraph(new In("test5.txt"));
        for(int v=0;v<weightedGraph.V();v++){
            System.out.println("顶点"+v+"的边");
            for(Edge e : weightedGraph.adj(v)){
                System.out.print(e+" ");
            }
            System.out.println();
        }

    }


    /**
     * 传入顶点数初始化
     * @param V
     */
    public WeightedGraph(int V){
        if(V<0) throw new IllegalArgumentException();
        this.V=V;
        adj=new LinkedList[V];
        this.E=0;
        for (int i = 0; i < V; i++) {
            adj[i]=new LinkedList<Edge>();
        }
    }

    /**
     * 返回顶点个数
     * @return
     */
    public int V(){
        return V;
    }

    /**
     * 返回边个数
     * @return
     */
    public int E(){

        return E;
    }

    /**
     * 从输入流传入图进行初始化
     * @param in
     */
    public WeightedGraph(In in){
        if(in ==null )throw new IllegalArgumentException();
        //读入顶点数
        this.V = in.readInt();
        if(V<0) throw new IllegalArgumentException();
        adj=new LinkedList[V];
        //初始化邻接表
        for(int i=0;i<this.V;i++){
            adj[i]=new LinkedList<Edge>();
        }
        //读入边数
        int E= in.readInt();
        if(E<0) throw new IllegalArgumentException();
        //添加边数
        for(int i=0;i<E;i++){
            int v= in.readInt();
            int w=in.readInt();
            double weight=in.readDouble();
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }

    }

    /**
     * 向图添加一条边v-w
     * @param e
     */
    public void addEdge(Edge e) {
        int v=e.either();
        int w=e.other(v);
        //无向图两点互连
        adj[v].add(e);
        adj[w].add(e);
        //增加边数
        this.E++;
    }

    /**
     * 返回v顶点的所有边
     * @param v
     * @return
     */
    public  Iterable<Edge> adj(int v){
        return adj[v];
    }



    /**
     * 对象的字符串表示
     * @return
     */
    public String toString(){
        String s=V+"个顶点，"+E+"条边\n";
        for(int v=0;v<V;v++){
            s+=v+": ";
            for(Edge e:adj[v]){
                s+=e+" ";
            }
            s+="\n";
        }
        return s;
    }
}
