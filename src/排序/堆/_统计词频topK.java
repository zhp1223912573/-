package 排序.堆;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author zhp
 * @date 2022-07-04 13:40
 * 给定一个字符串类型的数组arr，求其中出现次数最多的前K个
 */
public class _统计词频topK {
    /**排序.堆
     *
     * 1.大根堆，需要n个空间，n是单词总数
     * 2.小根堆，小根堆只需要k个空间，比大根堆需要的少一些。
     *
     * 利用小根堆堆顶为最小元素的特性，将堆顶元素理解为门槛，
     * 若堆满了的时候，要压入堆的元素大于门槛，说明门槛不能是topK的单词，直接弹出，在压入新单词
     * 反复上述操作即可
     * */

    public static class Node{
        public String word;
        public int times;

        public Node(String word, int times) {
            this.word = word;
            this.times = times;
        }
    }

    public static class NodeComparator implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            return o1.times-o2.times;
        }
    }

    public static void printTopKandRank(String[] args,int topK){
        if (args == null || args.length == 0 || topK < 1) {
            return ;
        }
        //统计词频
        HashMap<String,Integer> words = new HashMap<>();
        for(String word : args){
            if(words.containsKey(word)){
                words.put(word,words.get(word)+1);
            }else{
                words.put(word,1);
            }
        }

        PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());//小根堆
        for(Map.Entry<String, Integer> entry : words.entrySet()){
            Node cur = new Node(entry.getKey(),entry.getValue());
            if(heap.size()<topK){
                heap.add(cur);
            }else{
                if(heap.peek().times<cur.times){
                    heap.poll();
                    heap.add(cur);
                }
            }

            while (!heap.isEmpty()) {
                System.out.println(heap.poll().word);
            }
        }
    }

    public static String[] generateRandomArray(int len, int max) {
        String[] res = new String[len];
        for (int i = 0; i != len; i++) {
            res[i] = String.valueOf((int) (Math.random() * (max + 1)));
        }
        return res;
    }

    public static void printArray(String[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] arr1 = { "A", "B", "A", "C", "A", "C", "B", "B", "K" };
        printTopKandRank(arr1, 2);

        System.out.println("********************");
        String[] arr2 = generateRandomArray(50, 10);
        int topK = 3;
        //printArray(arr2);
        printTopKandRank(arr2, topK);

    }
}
