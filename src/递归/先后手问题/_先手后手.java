package 递归.先后手问题;

/**
 * @author zhp
 * @date 2022-06-13 16:37
 */
public class _先手后手 {
    /**
     * 先手选择数组内最大的数
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int first(int arr[],int left,int right){
        if(left == right){//只剩下一个数未选择
            return arr[left];
        }

        return Math.max(arr[left]+second(arr,left+1,right),
                arr[right]+second(arr,left,right-1));
    }

    /**
     * 后手选择
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int second(int arr[],int left,int right) {
        if (left == right) {//后手选择，当前只剩下一个，肯定被对手选走，直接返回0
            return 0;
        }
        //后手需要等待对手的先手选择，对手是”绝顶聪明“的，肯定选择了较大的情况，我们只能选择较小的
        return Math.min(first(arr, left + 1, right),
                first(arr, left, right-1));
    }

    public static int process(int arr[]){
        if(arr==null || arr.length==0){
            return 0;
        }

        //此处先手后手就是a和b
        return Math.max(first(arr,0,arr.length-1), second(arr,0,arr.length-1));
    }

    /**将递归过程转化为dp方法
     * 将先手后手递归函数等效理解位两个状态数组，递归过程就是在求解状态数组某个位置上的数值，
     * 而该数值需要依赖于另一个数组，我们可以通过观察状态数组的变化来列出状态方程。
     *
     *具体数组见图片
     * @param arr
     */
    public static int fs(int arr[]){
        if(arr==null || arr.length<1){
            return -1;
        }

        int f[][] = new int[arr.length][arr.length] ;
        int s[][] = new int[arr.length][arr.length] ;
        for(int i=0;i<arr.length;i++){
            f[i][i] = arr[i];
        }

        int row = 0;
        int col = 1;
        while(col<arr.length){
            int i = row;
            int j = col;
            while(i<arr.length && j<arr.length){
                f[i][j] = Math.max(arr[i]+s[i+1][j],arr[j]+s[i][j-1]);
                s[i][j] = Math.min(f[i+1][j],f[i][j-1]);
                i++;
                j++;
            }
            col++;
        }
        return Math.max(f[0][arr.length-1],s[0][arr.length-1]);
    }

    public static void main(String[] args) {
        int arr[] = {1,100,4,200,14};
        System.out.println(fs(arr));
    }

}
