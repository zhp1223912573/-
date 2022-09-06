package shiyan.Graphs;

/**
 * @author zhp
 * @date 2021-12-20 15:55
 * 边
 */
public class Edge implements Comparable<Edge> {

    //边的两个顶点
    private int v;
    private int w;
    //边的权重
    private double weight;

    public Edge(int v,int w,double weight){
        this.v=v;
        this.w=w;
        this.weight=weight;
    }

    /**
     * 获取边的权重
     * @return
     */
    public double getWeight(){
        return weight;
    }

    /**
     * 返回v点
     * @return
     */
    public int either(){
        return v;
    }

    /**
     * 返回边的另一顶点
     * @param vertex
     * @return
     */
    public int other(int vertex){
        if(vertex==v) return w;
        else return v;

    }

    /**
     * 字符串表示
     * @return
     */
    public String toString(){
        return new String(v+"-"+w+" "+weight);
    }


    @Override
    public int compareTo(Edge o) {
        if(weight>o.weight){
            return 1;
        }else if(weight<o.weight){
            return -1;
        }else{
            return 0;
        }
    }
}
