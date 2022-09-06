package shiyan.Graphs.undirectedGraph;

import shiyan.In;

import java.util.LinkedList;

/**
 * @author zhp
 * @date 2021-12-20 15:15
 * 使用深度优先搜索找出图中的所有连通分量
 */
public class CC {
    private int count;//无向图中的连通分量总数
    private int id[];//图中所有顶点所属的连通分量编号
    private boolean marked[];//顶点访问与否

    public static void main(String[] args) {
        Graph g = new Graph(new In("test4.txt"));
        System.out.println(g.toString());
        System.out.println("****************");
        CC cc = new CC(g);
        int num=cc.count;
        System.out.println("该图的连通分量个数为："+num);
        for(int i=0;i<num;i++){
            for(int v=0;v<g.V();v++){
                if(cc.id[v]==i){
                    System.out.print(v+" ");
                }
            }
            System.out.println();
        }

    }

    public CC(Graph g){
        id=new int[g.V()];
        marked=new boolean[g.V()];
        for(int s=0;s<g.V();s++){
            if(marked[s]!=true){
                dfs(g,s);
                //dfs已经遍历完了一个连通分量 增加count
                count++;
            }
        }
    }

    private void dfs(Graph g, int v){
        marked[v]=true;
        //为顶点设置连通分量编号
        id[v]=count;
        for(int w:g.adj(v)){
            if(marked[w]!=true){
                dfs(g,w);
            }
        }
    }

    /**
     * 判断两点是否相连
     * @param v
     * @param w
     * @return
     */
    public boolean connected(int v, int w){
        //若两顶点所在的连通分量一致 说明两点可以连接
        return id[v]==id[w];
    }

    /**
     * 获取连通分量总数
     */
    public int getCount(){
        return count;
    }

}
