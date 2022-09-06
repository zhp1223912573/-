package 排序;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-18 23:35
 * https://leetcode.cn/problems/query-kth-smallest-trimmed-number/
 * 周赛题
 */
public class 裁剪数字后查询第K小的数字_lc_2343 {

    class node{
        String  value;
        int index;

        public node( String   value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    /**
     * 自定义排序规则
     *
     * 基于类的排序，我们直接将字符串保存到node结点，
     * 在比较器内调用字符串的compareTo函数即可
     * @param nums
     * @param queries
     * @return
     */
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int m = queries.length;
        int n = queries[0].length;
        int ans[] = new int[m];
        int l = nums[0].length();


        //遍历所有请求，根据请求来划分nums的字符串
        for(int i=0;i<m;i++){
            node num[]  = new node[nums.length];
            int left = queries[i][1];
            int k = queries[i][0];
            String value ;
            for(int j=0;j<nums.length;j++){
                //裁剪字符串
                value = left>=l ?nums[j] :nums[j].substring(l-left,l);
                node no = new node(value,j);
                num[j] = no;
            }
            //比较裁剪后的字符串的大小，按字典序排序
            Arrays.sort(num, new Comparator<node>() {
                @Override
                public int compare(node o1, node o2) {
                    return o1.value.compareTo(o2.value);
                }
            });

            ans[i] = num[k-1].index;

        }
        return ans;
    }

    /**基数排序
     * 使用二维数组List<List<Integer>> lists保留从nums内的字符串的最低位到最高位，
     * 也就是总共m（字符串长度)轮的排序索引。
     * 每一轮的排序都是根据上一轮的排序结果得来的，将nums内的所有字符串通过
     * 基数排序，最后根据queries内的裁剪需求来找到对应的比较伦次，从而得到
     * 正确的排序结果。
     * @param nums
     * @param queries
     * @return
     */
    public int[] smallestTrimmedNumbers1(String[] nums, int[][] queries){
        int n = nums.length;
        int m = nums[0].length();
        //使用lists保存每轮基数排序的结果，list[i][j]表示第i轮第j小的数的坐标
        List<List<Integer>> lists = new ArrayList<>(m+1);
        for(int i=0;i<m+1;i++){
            lists.add(new ArrayList<>());
        }

        //初始化第0轮的排序结果，因为第0轮还么开始排序，顺序和初始状态一致
        for(int i = 0;i<n;i++){
            lists.get(0).add(i);
        }
        //开始第一轮排序
        for(int i = 0;i<=m;i++){
            //表示此轮的出现的0-9数值的所有坐标
            List<List<Integer>> turn = new ArrayList<>(10);
            for(int i1 = 0;i1<10;i1++){
                turn.add(new ArrayList<>());
            }
            //得到上一轮的所有坐标，在这里根据此位上的值一一更换位置，
            // 把第 i - 1 轮的结果，根据 nums 中右数第 i 位数，依次放入桶中
            for(int x : lists.get(i-1)){
                turn.get(nums[x].charAt(m-i)-'0').add(x);
            }
            // 把每个桶的结果连接起来，成为第 i 轮的结果
            for(int j=0;j<10;j++){
                for(int x : turn.get(j)){
                    lists.get(i).add(x);
                }
            }
        }

        int [] ans = new int[queries.length];
        int count = 0;
        for(int [] item : queries){
            ans[count++]=lists.get(item[1]).get(item[0]-1);
        }


        return ans;
    }

}
