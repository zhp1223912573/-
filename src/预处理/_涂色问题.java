package 预处理;

/**
 * @author zhp
 * @date 2022-07-02 21:09
 *
 * 给定一个有多个正方形组成的长方形矩形，
 * 该矩形有两种颜色绿色G或者红色R,
 * 现在给定一个矩形RGGRGG，给出使左边全是绿色右边全是红色的涂色方法
 */
public class _涂色问题 {
    /**
     * 枚举
     * 从左往右依次枚举每个方块，判断当以当前方块为界限，左边和右边分别需要染色的数量
     * 统计最小值并返回
     *
     * 时间复杂度是O（n^2)
     */
    public static int minPaintNum(String juxing){
        /*
        *for（int i=0;i<=N;i++){
        *   //N为右边界
        *       if(i==0){//左半部分为空，找出右半部分需要染色的部分，即颜色为G的方块
        *         }
        *       else if(i==N){//右半部分为空，找出左半部分需要染色的部分，即颜色为R的方块
        *       }
        *       else{//中间情况
        *           统计arr【0-l】颜色为R的方块，以及arr【l-n-1】颜色为G的方块数
        *       }
        * }
        * */

        return -1;
    }

    /**预处理
     * 上述方法的主要时间开销在对于数组中的相同状态的重复查询，
     * 导致每选定一个方块为界限进行染色讨论时，都需要对剩下的子情况迭代查询情况，
     * 我们可以通过两个数组，分别记录从左往右的R的数量，以及从右往左的G的数量，
     * 从而避免对子状态的查询。
     * 这样我们通过牺牲一定空间得到复杂度为O(n)的算法
     *
     * 实例演示：
     * GRRGRG
     *
     * R数组：012233
     * G数组：322211
     * 累加： 334444
     * 最小染3个
     *
     * @param juxing
     * @return
     */
    public static int minPaintNum1(String juxing){
        char[] chars = juxing.toCharArray();
        int R[] = new int[chars.length];
        int G[] = new int[chars.length];

        if(chars[0]=='R'){
            R[0]=1;
        }
        if(chars[chars.length-1]=='G'){
            R[chars.length-1]=1;
        }

        for(int i=1;i<chars.length;i++){

            R[i] = R[i-1];
            if(chars[i]=='R'){
                R[i]++;
            }
        }

        for(int i=chars.length-2;i>=0;i--){
            G[i] = G[i+1];
            if(chars[i]=='G'){
                G[i]++;
            }
        }

        //分别求和两数组，找到最小值
        int min=Integer.MAX_VALUE;
        for(int i=0;i<chars.length-1;i++){
            if(min>G[i]+R[i]){
                min = G[i]+R[i];
            }
        }
        return min;
    }


}
