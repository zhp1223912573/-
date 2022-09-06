package 贪心;

/**
 * @author zhp
 * @date 2022-07-02 16:00
 * 有两种袋子，分别能装6个，8个（不能多不能少），
 * 如果要买n个苹果，输出最少需要的袋子数量，不存在则输出-1。
 *
 */
public class _买苹果 {
    /**
     * 贪心
     * 找出全用8个的袋子总数，再挨个减少，用6个的袋来买，直到符合条件。
     *
     * 优化：只要出现剩余奇数个苹果，那么直接返回，说明不存在解法，直接返回-1
     * O(n)
     */
    public static int minbag(int apple){
        if(apple<=0){
            return -1;
        }

        int bag6 = -1;
        int bag8 = apple/8;
        int res  = apple-bag8*8;

        while(bag8>=0&&res<24){
            int resusebag6 = (res%6 == 0 ?(res/6):-1);
            if(resusebag6!=-1){
                bag6 = resusebag6;
                break;
            }
            res = apple - 8 *(--bag8);
        }
        return bag6==-1 ? -1 : bag6 + bag8;
    }

    /**
     *打表法规律总结，通过上述贪心思路写出的方法进行数据测试并打表，可以发现一定的规律，
     * 苹果数量大于18时，每8位为一组，奇数数量的苹果都没解答，偶数数量的苹果的最少袋子数都一样
     * 直接按照规律输出每个数值的最少袋子数
     * 达到了O（1）
     *
     */

    public static void main(String[] args) {
        for (int i = 0; i <10000 ; i++) {
            int bagnum = minbag(i);
            System.out.println(i+" :"+ bagnum);
        }
    }
}
