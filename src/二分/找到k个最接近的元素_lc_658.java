package 二分;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhp
 * @date 2022-07-20 21:47
 * https://leetcode.cn/problems/find-k-closest-elements/solution/zhao-dao-kge-zui-jie-jin-de-yuan-su-by-leetcode/
 */
public class 找到k个最接近的元素_lc_658 {
    /**
     * 将数组中的元素按照与x的差值进行排序，最后得到的数组的前k元素就是最接近的k个元素
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ret = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.sort(ret, (a, b) -> a == b ? a - b : Math.abs(a-x) - Math.abs(b-x));
        ret = ret.subList(0, k);
        Collections.sort(ret);
        return ret;
    }

    /**
     * 二分查找+数组.双指针
     * 当x小于数组最小值时，最接近的k个元素是数组前k个元素
     * 当x大于数组最大值时，最接近的k个元素是数组的末尾k个元素
     * 当x位于数组内部位置i时，则最接近x的k个元素一定位于[i-k-1,i+k-1]区间内
     *      此时我们双指针指向区间两端，直到区间内元素等于k个元素，则区间内的元素就是答案。
     */
    public List<Integer> findClosestElements1(int[] arr, int k, int x){

        List<Integer> ret = Arrays.stream(arr).boxed().collect(Collectors.toList());
        int n = arr.length;
        if(x<=ret.get(0)){
            return ret.subList(0,k);
        }else if(x>=ret.get(n-1)){
            return ret.subList(n-k,n);
        }else{
            //查找x位于ret内的位置
            int index = Collections.binarySearch(ret,x);
            if(index<0){
                index = -index-1;
            }
            //得到有效区间左右边界，开始双指针比较缩小窗口
            int left = Math.max(0,index-k-1);
            int right = Math.min(n-1,index+k-1);
            while(right-left>k-1){
                if(x-ret.get(left)<=ret.get(right)-x){
                    right--;
                }else{
                    left++;
                }
            }
            return ret.subList(left,right+1);
        }
    }
}
