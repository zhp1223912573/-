package 递归;

/**
 * @author zhp
 * @date 2022-06-13 15:24
 * 经典递归问题
 */
public class hanobi_汉诺比 {

    public static void hanobi(int n){
        if(n<1){
            return ;
        }

        fun(n,"左","右","中");

    }

    /**
     * 分析该问题过程：
     * 1.将1——n-1的盘从start移动到other，
     * 2.将第n个盘从start移动到end
     * 3.将步骤1中移动到other的所有盘再移动end
     *
     * @param n
     */
    private static void fun(int n,String start,String end,String other) {
        if(n==1)    {
            System.out.println("移动盘子"+n+" from:"+start+" to:"+end);
        }else{
            //1.
            fun(n-1,start,other,end);
            //2.
            //解决完1-i-1的所有盘的移动，并将一个盘移动到目标位置end时，需要输出
            System.out.println("移动盘子"+n+" from:"+start+" to:"+end);
            //3.
            //下面该解决在other上的1-i-1的所有盘的移动问题
            fun(n-1,other,end,start);
        }
    }

    public static void main(String[] args) {
        hanobi(3);
    }
}
