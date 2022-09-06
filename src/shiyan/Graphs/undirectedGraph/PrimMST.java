package shiyan.Graphs.undirectedGraph;

import shiyan.Graphs.Edge;
import shiyan.In;
import shiyan.Queue.LinkedQueue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zhp
 * @date 2021-12-20 16:48
 * 最小生成树的Prim算法
 * 利用pq优先队列保存访问到的顶点间的横切边
 * 将权重最小且没有在树中的边加入mst中
 * 删除掉加入树的边
 *
 */
public class PrimMST {
    private boolean marked[];//访问顶点标记
    private LinkedQueue<Edge> mst;//保存最小生成树的所有边
    private Queue<Edge> pq;//优先队列 获取权重最小的边

    public static void main(String[] args) {
        WeightedGraph g =  new WeightedGraph(new In("test5.txt"));
        PrimMST primMST = new PrimMST(g);

        LinkedQueue<Edge> edges = primMST.edges();
        while(!edges.isEmpty()){
            Edge e = edges.poll();
            System.out.println(e);
        }

    }

    public PrimMST(WeightedGraph g){
        marked=new boolean[g.V()];
        mst = new LinkedQueue<>();
        //创建最小优先队列
        pq=new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if(o1.getWeight()<o2.getWeight()){
                    return -1;
                }else if(o1.getWeight()>o2.getWeight()){
                    return 1;
                }
             return 0;
            }
        });

        vist(g,0);//先加入0顶点
        while(!pq.isEmpty()){
            Edge e= pq.poll();

            int v=e.either();
            int w=e.other(v);
            //跳过已经加入的边
            if(marked[v]&&marked[w]) continue;
            //加入新的边
            mst.add(e);
            if(marked[v]!=true){
                vist(g,v);
            }
            if(marked[w]!=true){
                vist(g,w);
            }
        }

    }

    private void vist(WeightedGraph g,int v){
        marked[v]=true;
        for(Edge e : g.adj(v)){
            if(!marked[e.other(v)]){
                pq.add(e);
            }
        }
    }

    public LinkedQueue<Edge> edges(){
        return mst;
    }
}
