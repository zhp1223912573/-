package 数学;

/**
 * @author zhp
 * @date 2022-07-05 12:15
 * 给定一个数组arr，如果通过调整可以做到arr中任意两个相邻的数字相乘是4的倍数，
 * 返回true；如果不能返回false
 */
public class _相邻两数为4的倍数的数组 {
    /**
     * 给定一个数组，数组中的数字可以分成三类。
     * A：奇数，左右的相邻数必须为4的倍数，才符合条件。
     * B: 只有一个2因子的数。
     * C: 有一个4因子的数。
     * 分类讨论：
     * 1.当B类数字数量为0时，
     *      A==1，则C>=1时，条件成立
     *      A>1,  则C>=A-1时，C的数量可以比A的最多少一个，主要出现ACACA的局面也符合条件
     *      综上，不存在B时，C+1>=A;
     * 2.当B类数字数量不为0时，我们将所有B类数字放置在一起，他们相邻两数一定可以构成一个4的倍数，
     *   但与奇数A必须间隔一个C数。
     *      A==0，则C>=0
     *      A==1,则C>=1;
     *      A>1, 则C>=A;
     *      综上，存在B时，C>=A
     *
     *  根据上述分析即可编码
     */
    public static boolean nearMultiple4time(int arr[]){
        if(arr==null || arr.length==0){
            return false;
        }
        int odd =0;
        int even4 = 0;
        int even2 = 0;
        for(int i=0;i<arr.length;i++){
            if((arr[i]&1)==1){
                odd++;
            }else{
                if(arr[i]%4==0){
                    even4++;
                }else{
                    even2++;
                }
            }
        }
        return even2==0 ? ((even4+1)>=odd) : (even4>=odd);
    }

    public static void main(String[] args) {
        int arr[] = {2,1,1,0,1,4,16};
        System.out.println(nearMultiple4time(arr));
    }
}
