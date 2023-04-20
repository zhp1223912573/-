package 二分;

/**
 * @author zhp
 * @date 2023-04-14 16:20
 * https://leetcode.cn/problems/koko-eating-bananas/submissions/
 */
public class 爱吃香蕉的珂珂_lc_875 {
    /**
     * 对可能的吃香蕉时间进行二分查找
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        int l=1;
        int r=(int)1e9;
        while(l<r){
            int mid = (r-l)/2+l;
            if(check(piles,h,mid)) r = mid;
            else l = mid+1;
        }

        return r;
    }

    boolean check(int []piles,int h,int mid){
        int ans = 0;
        for(int num:piles){
            ans+=(num/mid);
            if(num%mid>0) ans++;
            if(ans>h) return false;
        }
        return true;
    }
}
