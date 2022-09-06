package 数组;

/**
 * @author zhp
 * @date 2022-07-27 20:42
 */
public class 除自身以外数组的乘积_lc_238 {
    /**
     * 不能使用除法，又要使算法时间开销为O(n),那么牺牲一定空间，保存一个数的左右位置的所有乘积，
     * 这样就可以在O(n)内完成计算。
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int left[] = new int[n];
        int right[] = new int[n];
        left[0]=1;
        right[n-1]=1;

        for(int i=1;i<n;i++){
            left[i] = left[i-1]*nums[i-1];
        }
        for(int i=n-2;i>=0;i--){
            right[i] = right[i+1]*nums[i+1];
        }

        int ans[] = new int[n];
        for(int i=0;i<n;i++){
            ans[i] = left[i]*right[i];
        }

        return  ans;
    }

    /**
     * 上述方法的空间优化
     * 使用要返回的ans数组来充当索引左侧数值乘积，
     * 而R右侧索引乘积在计算过程中计算。
     */
    public int[] productExceptSelf1(int[] nums) {
        int n = nums.length;
        int left[] = new int[n];
        left[0] = 1;


        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        int R = 1;
        for(int i=n-1;i>=0;i++){
            left[i] = left[i]*R;
            R*=nums[i];
        }
        return left;
    }

}
