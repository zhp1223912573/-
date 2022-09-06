package 排序;

import java.util.*;

/**
 * @author zhp
 * @date 2022-07-18 22:30
 * https://leetcode.cn/problems/top-k-frequent-elements/
 */
public class 前k个高频元素_lc_347 {
    /**小根堆 堆排序
     * 记录数字出现次数，再堆排序即可。
     * O（nlogn）
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map <Integer,Integer> map = new HashMap<>();
        //记录数字次数
        for(int num : nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        //排序.堆，比较次数，升序
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });

        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            int num = entry.getKey();
            int count = entry.getValue();
            if(queue.size()==k){
                if(count>queue.peek()[1]){
                    queue.poll();
                    queue.offer(new int[]{num,count});
                }
            }else{
                queue.offer(new int[]{num,count});
            }
        }

        int ans[] = new int[k];
        for(int i=0;i<k;i++){
            ans[i] = queue.poll()[0];
        }
        return ans;
    }

    /**快排
     *官方解答：
     * https://leetcode.cn/problems/top-k-frequent-elements/solution/qian-k-ge-gao-pin-yuan-su-by-leetcode-solution/
     *
     */
    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        List<int[]> values = new ArrayList<int[]>();
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            values.add(new int[]{num, count});
        }
        int[] ret = new int[k];
        qsort(values, 0, values.size() - 1, ret, 0, k);
        return ret;
    }

    public void qsort(List<int[]> values, int start, int end, int[] ret, int retIndex, int k) {
        int picked = (int) (Math.random() * (end - start + 1)) + start;
        Collections.swap(values, picked, start);

        int pivot = values.get(start)[1];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            if (values.get(i)[1] >= pivot) {
                Collections.swap(values, index + 1, i);
                index++;
            }
        }
        Collections.swap(values, start, index);

        if (k <= index - start) {
            qsort(values, start, index - 1, ret, retIndex, k);
        } else {
            for (int i = start; i <= index; i++) {
                ret[retIndex++] = values.get(i)[0];
            }
            if (k > index - start + 1) {
                qsort(values, index + 1, end, ret, retIndex, k - (index - start + 1));
            }
        }
    }
}
