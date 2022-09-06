package 二分;

/**
 * @author zhp
 * @date 2022-07-20 16:11
 * https://leetcode.cn/problems/sqrtx/
 */
public class x的平方根_lc_69 {
    public int mySqrt(int x) {
        int l = 1;
        int r =x;
        int ans = 0;
        while(l<=r){
            int mid = (r-l)/2+l;
            if(mid==x/mid){
                return mid;
            }
            if(mid<x/mid){
                ans = mid;
                l = mid+1;
            }else{
                r = mid-1;
            }
        }
        return ans;
    }

    public static int mySqrt1(int x) {
        if(x==0) return 0;
        int l = 1;
        int r =x;

        while(l<r){
            int mid = (r-l+1)/2+l;
            if(mid>x/mid){
                r= mid-1;
            }else{
                l = mid;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        mySqrt1(8);
    }
}
