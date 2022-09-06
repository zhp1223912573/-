package 位运算;

/**
 * @author zhp
 * @date 2022-06-15 21:16
 */
public class _实现a与b的加减乘除 {

    /**
     * 计组原理
     * 将a+b理解为两个数值
     * 本位值A： a^b
     * 进位值B： a&b <<1
     * A&B ,直到进位为0
     *
     * 不对溢出情况进行处理，要求出入的数值计算后不会溢出
     * @param a
     * @param b
     * @return
     */
    public static int add(int a,int b){
        int sum = a;//先设置一个值，当b为0时直接返回sum
        while(b!=0){
            sum = a^b;//本位值
            b = (a&b)<<1;//用b来保存进位值
            a = sum ;//用a来保存本位值
        }
        return sum;
    }

    /**
     * 取反一个数
     * @param num
     * @return
     */
    public static int negnum(int num){
        return (~num + 1);//补码表示
    }

    /**
     * 将减法理解为加法
     * a-b == a + (-b)
     * @param a
     * @param b
     * @return
     */
    public static int minus(int a,int b){
        return add(a,negnum(b));
    }

    /**
     * 模拟二进制计算过程
     *
     *       010101
     * x     101010
     * ---------------
     *       000000
     *      010101
     *     000000
     *    010101
     *   000000
     *  010101
     * --------------
     *
     * @param a
     * @param b
     * @return
     */
    public static int multi(int a,int b){
       int sum = 0;

       //b是负数时无法正常处理，可以先将b转化为正数，在返回结果时取结果的相反数即可
       boolean isBneg = false;
        if(isneg(b)) {
           b=-b;
            isBneg = true;
        }
       while(b!=0){

           if((b&1)!=0){//第一位为0，则要加一次被乘数
               sum = add(a,sum);
           }
           b>>=1;//右移b，让每一位与被乘数相乘，转化为了加操作
           a<<=1;//每乘完一次都要移动被乘数，保证数值位数正确
       }

       return isBneg ? -sum:sum;
    }

    /**
     * 21/7=3
     * 转化为二进制进行理解：
     *                     11
     *       ➗ 0111 |  10101
     *                  0111
     *                  -----
     *                  00111
     *                  00111
     *                  -----
     *                  00000
     *   结果为（11）2 = 3
     *   在进行除操作时，需要左移b数n为找到小于a的，可以进行相减操作的数，
     *   循环进行直到余数为0或者被除数小于除数，结果就出来了
     *
     * @param a
     * @param b
     * @return
     */
    public static int div(int a,int b){
        //保证进行除法的两个数都是正数，输出结果时再根据符号进行处理
        int x = isneg(a) ? negnum(a) : a;
        int y= isneg(b) ? negnum(b) : b;

        int res = 0;
        for(int i=32;i>-1;i=minus(i,1)){
            if((x>>i)>=y){//a右移与b左移等价，但是b左移可能会溢出，处于安全，这里右移a
                res |= (1<<i);//置1表示当前位可以相除
                x = minus(x,y<<i);//a减去b左移i位，实现当前位置上的除法
            }
        }

        //两数符号不同，需要取反返回结果
        return isneg(a)^isneg(b) ? negnum(res):res;
    }

    /**判断数值是否为负数
     *
     * @param num
     * @return
     */
    public static boolean isneg(int num){

        return num<0;
    }

    public static void main(String[] args) {
        System.out.println(2+" "+((~2)));
        System.out.println(  div(21, -7));
    }



}
