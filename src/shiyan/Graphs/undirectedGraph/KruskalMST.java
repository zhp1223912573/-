package shiyan.Graphs.undirectedGraph;

import shiyan.Graphs.Edge;
import shiyan.In;
import shiyan.Queue.LinkedQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhp
 * @date 2021-12-20 19:32
 * 最小生成树的Krusal算法
 * 思路：将所有边存入优先队列中，获取权重最小的边
 *      判断该边的两个顶点是否都在树中
 *      若是 则跳过该边
 *      若不是 则说明该边还不在树中，将边加入树
 *      反复上述判断 直到优先队列为空或者树的边数等于图顶点-1 说明已经生成了最小生成树
 */
public class KruskalMST {
    LinkedQueue<Edge> mst;//存储生成树

    public static void main(String[] args) {
        WeightedGraph g= new WeightedGraph(new In("test5.txt"));
        KruskalMST kruskalMST = new KruskalMST(g);
        LinkedQueue<Edge> edges = kruskalMST.edges();
        while(!edges.isEmpty()){
            Edge e = edges.poll();
            System.out.println(e);
        }
    }

    public KruskalMST(WeightedGraph g){
        //初始化
        mst = new LinkedQueue<>();
        //创建最小优先队列
        PriorityQueue<Edge> minpq=new PriorityQueue<>(new Comparator<Edge>() {
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
        //将所有边放入最小优先队列中
        for (int i = 0; i < g.V(); i++) {
            for(Edge e: g.adj(i)){
                minpq.add(e);
            }
        }
        //设置index标记已经在树中的顶点
        boolean index[]=new boolean[g.V()];
        //但队列为空或者树中顶点个数等于图顶点个数-1时 最小生成树构成
        while(!minpq.isEmpty()&&mst.size()!=g.V()-1){
            //提取权值最小的边
            Edge e = minpq.poll();
            //获取边的两个端点
            int v=e.either();
            int w=e.other(v);
            //若两个端点都在树中 说明该边已经加入 寻找下一条最小边
            if(index[v]&&index[w]) continue;
            //加入该边的两个端点
            index[v]=true;
            index[w]=true;
            //将边加入树中
            mst.add(e);
        }
    }

    public LinkedQueue<Edge> edges(){
        return mst;
    }
}
