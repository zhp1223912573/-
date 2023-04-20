package 图.拓扑排序;

import sun.plugin.javascript.navig.Link;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhp
 * @date 2023-04-16 19:19
 * https://leetcode.cn/problems/find-eventual-safe-states/
 */
public class 找到最终的安全状态_lc_802 {
    /**
     * 题目要求找到某个节点到达所有他能到达的终端节点的过程不经过环.
     * 设置出度数组以及当前节点的前一个节点的数组。
     * 从出度为0的节点，也就是终端节点出发，逆序找到符合条件的节点。
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        //初始化
        int n = graph.length;
        List<List<Integer>> pre = new ArrayList<>();
        int de[] = new int[n];
        for(int i=0;i<n;i++){
            pre.add(new LinkedList<>());
        }

        //填充出度和前节点数组
        for(int i=0;i<n;i++){
            for(int next:graph[i]){
                pre.get(next).add(i);
                de[i] ++;
            }
        }

        //现将终端节点加入
        Deque<Integer> deque = new LinkedList<>();
        for(int i=0;i<n;i++){
            if(de[i]==0) deque.add(i);
        }

        //不断移除终端节点，并将新的终端节点加入
        List<Integer> ans = new LinkedList<>();
        while(!deque.isEmpty()){
            int node = deque.pollFirst();
            ans.add(node);
            for(int previous:pre.get(node)){
                de[previous]--;
                if(de[previous]==0) deque.add(previous);
            }
        }
        ans.sort((a,b)->a-b);
        return ans;
    }

    /**
     * 基于dfs
     */
    int visit[];
    boolean dfs(int node,int [][] graph){
        if(visit[node]==1) return true;
        if(visit[node]==-1) return false;
        visit[node]=1;
        for(int nx:graph[node]){
            if(dfs(nx,graph)) return true;
        }
        visit[node]=-1;
        return false;
    }
    public List<Integer> eventualSafeNodes1(int[][] graph){
        List<Integer> ans = new LinkedList<>();
        int n =graph.length;
        visit = new int[n];
        for(int i=0;i<n;i++){
            if(!dfs(i,graph)) ans.add(i);
        }
        return ans;
    }


}
