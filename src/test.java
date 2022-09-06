import java.util.*;

/**
 * @author zhp
 * @date 2022-07-17 10:39
 */
public class test {
    public int maximumSum(int[] nums) {
       Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        Arrays.sort(nums);
        int max = -1;
        for(int i=nums.length-1;i>=0;i--){
            int k = countSum(nums[i]);
            if(map.containsKey(k)){
                max = Math.max(max,nums[i]+nums[map.get(k)]);
            }else{
                map.put(k,i);
            }
        }
        return max;
    }

    public  int countSum(int num){
        int sum = 0;
        while(num>0){
            int n = num%10;
            num/=10;
            sum+=n;
        }
        return sum;
    }

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
     * @param nums
     * @param queries
     * @return
     */
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
            int m = queries.length;
            int n = queries[0].length;
            int ans[] = new int[m];
            int l = nums[0].length();


            for(int i=0;i<m;i++){
                node num[]  = new node[nums.length];
                int left = queries[i][1];
                int k = queries[i][0];
                String value ;
                for(int j=0;j<nums.length;j++){
                    value = left>=l ?nums[j] :nums[j].substring(l-left,l);
                    node no = new node(value,j);
                    num[j] = no;
                }
              Arrays.sort(num, new Comparator<node>() {
                  @Override
                  public int compare(node o1, node o2) {
                      return o1.value.compareTo(o2.value);
                  }
              });
                int i1 ;
                for( i1 = 0;i1<k;i1++){
                    if(num[i1].value==num[k-1].value){
                        break;
                    }
                }

                ans[i] = num[k-1].index;

            }
            return ans;
    }

    /**
     * 基数排序
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

        for(int i = 0;i<n;i++){
            lists.get(0).add(i);//第0轮还么开始排序，顺序和初始状态一致
        }
        //开始第一轮排序
        for(int i = 0;i<=m;i++){
            //表示此轮的0-9的所有坐标
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

    public int minOperations(int[] nums, int[] numsDivide) {
        Arrays.sort(nums);
        int g = 0;
        for(int num:numsDivide){
            g = gcb(g,num);
        }
        for(int i = 0;i<nums.length;i++){
            if(g%nums[i]==0){
                return i;
            }
        }
        return -1;
    }

    public int gcb(int a,int b){
        return a==0? b : gcb(b%a,a);
    }


    public static void main(String[] args) {
        test t = new test();
        String arrs[]  = {"102","473","251","814"};
        int nums[][] = {{1,1},{2,3},{4,2},{1,2}} ;
        int[] ints = t.smallestTrimmedNumbers1(arrs, nums);
        for(int i:ints){
            System.out.println(i);
        }

    }
}
