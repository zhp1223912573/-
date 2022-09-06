package 栈和队列;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhp
 * @date 2022-07-20 15:32
 * https://leetcode.cn/problems/keys-and-rooms/solution/yao-chi-he-fang-jian-by-leetcode-solution/
 */
public class 钥匙和房间_lc_841 {
    /**
     * 广度
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int m = rooms.size();
        boolean door[] = new boolean[m];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while(!queue.isEmpty()){
            int lock = queue.poll();
            if(!door[lock]){
                for(Integer i : rooms.get(lock)){
                    queue.add(i);
                }
                door[lock] =true;
                count++;
            }
        }

        return count==m;
    }

    /**
     * 深度
     */
    int num = 0;
    public boolean canVisitAllRooms1(List<List<Integer>> rooms){
        int m = rooms.size();
        boolean visited[] = new boolean[m];
        dfs(rooms,visited,0);
        return num==m;
    }

    private void dfs(List<List<Integer>> rooms, boolean[] visited, int door) {
        visited[door] = true;
        num++;
        for (int it : rooms.get(door)) {
            if (!visited[it]) {
                dfs(rooms, visited,it);
            }
        }
    }
}
