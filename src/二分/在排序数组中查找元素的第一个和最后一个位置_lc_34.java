package 二分;

/**
 * @author zhp
 * @date 2022-07-20 20:58
 */
public class 在排序数组中查找元素的第一个和最后一个位置_lc_34 {

    /**二分：模板1
     * 转化问题为找到大于target-1的数的第一个数的坐标，以及大于target的第一个数的坐标
     *
     * 所以二分查找函数需要找到，第一个大于target的值
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {

        int left  = binarySearch(nums,target-1);
        int right = binarySearch(nums,target)-1;

        if(left<=right && nums[left]==target){
            return new int[]{left,right};
        }
        return  new int[] {-1,-1};

    }
    public int binarySearch(int[]num,int target){
        int m = num.length;
        int left = 0;
        int right = m-1;
        //ans也设置正确，因为如果大于target的数不存在，说明target在原数组中是最后一个值，
        //我们必须返回数组元素个数才行。
        int ans = m;
        while(left<=right){
            int mid = (right-left)/2+left;
            if(num[mid]>target){
                ans = mid;
                right = mid-1;
            }else{
                left = mid+1;

            }
        }
        return ans;
    }

    /**
     * 二分：模板2
     * 同一道可以套用两个模板，不需要拘囿与特定的情况，
     * 只要分析清楚了问题的边界问题，就可以随心使用任何模板。
     * 不过按照规定，还是按总结来写更方便。
     *
     */
    public int[] searchRange1(int[] nums, int target) {
        if(nums.length==0){
            return new int []{-1,-1};
        }
        int left  = binarySearch(nums,target-1);
        int right = binarySearch(nums,target)-1;

        if(left<=right && nums[left]==target){
            return new int[]{left,right};
        }
        return  new int[] {-1,-1};

    }

    public int binarySearch1(int[]num,int target){
        int m = num.length;

        int left = 0;
        int right = m-1;
        //int ans = m;
        while(left<right){
            int mid = (right-left)/2+left;
            if(num[mid]>target){
                //ans = mid;
                right = mid;
            }else{
                left = mid+1;

            }
        }
        return num[left]>target?left:m;
    }

    /**
     * 扎到大于等于target的最小值位置，即元素第一个位置
     * 找到小于等于target的最大值位置，即元素最后一个位置
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange3(int[] nums, int target) {
        if(nums.length==0 ) return new int[]{-1,-1};
        int l =0;
        int r =nums.length-1;
        while(l<r){
            int mid = (r-l)/2+l;
            if(nums[mid]>=target) r = mid;
            else l =mid+1;
        }
        if(nums[l]!=target) return new int[]{-1,-1};
        int first = l;
        l=0;
        r=nums.length-1;
        while(l<r){
            int mid = (r-l+1)/2+l;
            if(nums[mid]<=target) l = mid;
            else r = mid-1;
        }
        return new int[] {first,l};
    }
}