package 数组;

import com.sun.scenario.effect.Brightpass;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author zhp
 * @date 2022-07-15 15:05
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/
 *
 */
public class 数组中的第k个最大元素_lc_215 {
    /**
     * 排序题，三种解题思路
     * 1.直接调用库函数 时间开销为O(nlogn)
     * 2.实现一个类快排，采用分而治之的思想，时间开销接近O(n)
     * 3.堆排序
     */

    /**快排+分而治之
     * 找到第k大的数，我们直接利用快排的parttion函数将数组指定范围left-right的区间排序（按大到小）
     * 如果当前找到的位置index+1就是k，那么直接返回，
     * 如果index+1<k,说明要找的数在新区间index+1--right
     * 如果index+1>k,说明要找的数在区间left--index-1;
     * 直到找到指定位置的数值，才返回。
     * @param nums
     * @param k
     * @return
     */
        public int findKthLargest(int[] nums, int k) {
            if(nums.length<k){
                return -1;
            }
           return findK(nums,k,0,nums.length-1);
        }

        public int findK(int nums[],int k,int left,int right){
            if(left==right){
                return nums[left];
            }
            int find = partition(nums,left,right);
            if(find==k-1){
                return nums[find];
            }else if(find>k-1){
                return findK(nums,k,left,find-1);
            }else{
                return findK(nums,k,find+1,right);
            }
        }

        public void swap(int[] nums,int i,int j){
            int temp  = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        public int partition(int[] nums,int low,int high){
            //选定随机位置的值作为pivot
            Random random = new Random(System.currentTimeMillis());
            int randomIndex = low + random.nextInt(high-low+1);

            /**新的快排代码（相比于之前的，更简洁，更好理解）
             */

            swap(nums,randomIndex,low);//将随机选择的pivot放置在区间的第一个元素
            int pivot = nums[low];//得到该标兵数
            int index = low;
            for(int i=low+1;i<=high;i++){
                if(nums[i]>=pivot){
                        //当前遍历到的数大于pivot值，则将其与index位置上的数交换，保证大于pivot的所有数位于数组前端
                        swap(nums,index+1,i);
                        index++;
                }
            }
            //不要忘了将low位置的数值交换到数组中的index停留位置。
            swap(nums,index,low);

            return index;

            /**
             *   过去的快排代码
             */

//            int temp = nums[randomIndex];
//            nums[randomIndex] =nums[low];
//            nums[low] = temp;
//
//
//
//            int compare = nums[low];//选定最左元素为比较元素
//            int left = low;
//            int right = high+1;
//            while(true){
//                while(nums[++left]>compare) if(left==high) break;
//                while(nums[--right]<compare) if(right==low) break;
//                if(left>=right) break;
//                 temp = nums[left];
//                nums[left] = nums[right];
//                nums[right] = temp;
//            }
//             temp = nums[low];
//            nums[low] = nums[right];
//            nums[right] = temp;
//            return right;


        }



    /**小根堆
     *
     */
    public int findKthLargest1(int nums[],int k){
        PriorityQueue<Integer> min = new PriorityQueue<>(k);
        for(int i = 0;i<k;i++){
            min.offer(nums[i]);
        }

        for(int i=k;i<nums.length;i++){
            int top = min.peek();//取出堆顶元素
            if(top<nums[i]){
                min.poll();
                min.offer(nums[i]);
            }
        }
        return min.peek();
    }

    public static void main(String[] args) {
        int arrs[] ={3,2,1,5,6,4};
        数组中的第k个最大元素_lc_215 t = new 数组中的第k个最大元素_lc_215();
        System.out.println(t.findKthLargest(arrs,6));
    }
}
