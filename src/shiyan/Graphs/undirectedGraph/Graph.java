package shiyan.Graphs.undirectedGraph;

import shiyan.In;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhp
 * @date 2021-12-20 11:03
 * 无向图
 */
public class Graph {
    private int V;//顶点数
    private int E;//边数
    private List<Integer> adj[]; //邻接表'

    public static void main(String[] args) {
            Graph graph= new Graph(6);
            graph.addEdge(0,5);
            graph.addEdge(2,4);
            graph.addEdge(2,3);
            graph.addEdge(1,2);
            graph.addEdge(0,1);
            graph.addEdge(3,4);
            graph.addEdge(3,5);
            graph.addEdge(0,2);
            System.out.println(graph.toString());

            //测试文件初始化函数，V（），E（），toString（）函数
            Graph graph1= new Graph(new In("test1.txt"));
            System.out.println(graph1.toString());
            System.out.println("顶点数："+graph1.V());
            System.out.println("边数："+graph1.E());
            //测试adj（）函数
            for (int i = 0; i <graph1.V ; i++) {
                System.out.println("顶点"+i+"的相邻顶点为：");
                for(int w:graph1.adj(i)){
                    System.out.print(w+" ");
                }
                System.out.println();
            }
        }

        /**
         * 传入顶点数初始化
         * @param V
         */
    public Graph(int V){
            if(V<0) throw new IllegalArgumentException();
            this.V=V;
        adj=new LinkedList[V];
        this.E=0;
        for (int i = 0; i < V; i++) {
            adj[i]=new LinkedList<>();
        }
    }

    /**
     * 从输入流传入图进行初始化
     * @param in
     */
    public Graph(In in){
        if(in ==null )throw new IllegalArgumentException();
        //读入顶点数
        this.V = in.readInt();
        if(V<0) throw new IllegalArgumentException();
        adj=new LinkedList[V];
        //初始化邻接表
        for(int i=0;i<this.V;i++){
            adj[i]=new LinkedList<Integer>();
        }
        //读入边数
        int E= in.readInt();
        if(E<0) throw new IllegalArgumentException();
        //添加边数
        for(int i=0;i<E;i++){
            int v= in.readInt();
            int w=in.readInt();
            addEdge(v,w);
        }

    }

    /**
     * 向图添加一条边v-w
     * @param v
     * @param w
     */
    public void addEdge(int v, int w){
        //无向图两点互连
        adj[v].add(w);
        adj[w].add(v);
        //增加边数
        this.E++;
    }

    /**
     * 字符串表示
     * @return
     */
    public String toString(){
        String s=V+"个顶点，"+E+"条边\n";
        for(int v=0;v<V;v++){
            s+=v+": ";
            for(int w:adj[v]){
                s+=w+" ";
            }
            s+="\n";
        }
        return s;
    }

    /**
     * 和v相邻的顶点
     * @param v
     * @return
     */
    public List<Integer> adj(int v) {
        assert v >= 0 && v < V;
        return adj[v];
    }

    /**
     * 顶点数
     * @return
     */
    public int V(){
        return V;
    }

    /**
     * 边数
     * @return
     */
    public int E(){
        return E;
    }
}
