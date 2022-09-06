package 特殊技巧;

/**
 * @author zhp
 * @date 2022-07-02 22:23
 *
 * 给定一个函数f,可以1~5的数字等概率返回一个。请加工出1~7的数字等概率返回一个的函数g。
 * 给定一个函数f,可以a~b的数字等概率返回一个。请加工出c~d的数字等概率返回一个的函数g。
 * 给定一个函数f,以p概率返回0，以1-p概率返回1。请加工出等概率返回0和1的函数g
 */
public class _给定函数生成限定数字 {
    public static int f(){
        return (int)(Math.random()*5)+1;
    }

    /**
     * 利用上述的f函数生成一个等概率输出0，1的函数
     * @return
     */
    public static int r01(){
        int res = 0;
        //若f生成1，2,4,5跳出循环，输出0或者1，如果是3则继续循环。
        do{
            res=f();
        }while(res==3);
        //小于3输出0，大于3输出1（随意设置）
        return res<3 ? 0:1;
    }

    /**利用r01函数，生成二进制数值
     * 按照要求，g函数要生成1-7，那么就需要3为二进制
     *
     * @return
     */
    public static int g(){
        //生成0-6，返回时再加1
        int res=0;
        do{
            res = (r01()<<2) + (r01()<<1) +  r01();
        }while(res==7);

        return res+1;
    }

    /**
     * p概论返回0，1-p概论返回1
     * @return
     */
    public static int p(){
        double p=0.78;
        return Math.random()<p? 0:1;
    }

    /**
     *同样利用二进制来实现，
     * 00或者11都再次判断
     * 01，10的概论都是p*（1-p），分别输出0，1即可等概率输出
     * @return
     */
    public static int q(){
        int res=0;
        do{
            res = (p()<<1) +p();
        }while(res==0 || res==3);
        return res==1? 0:1;
    }
}
