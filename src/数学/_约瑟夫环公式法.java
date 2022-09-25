package 数学;

/**
 * @author zhp
 * @date 2022-07-09 20:07
 *
 * 公式法推导过程讲解博客：
 * https://blog.csdn.net/u011500062/article/details/72855826
 */
public class _约瑟夫环公式法 {

    //n为总人数，m为数次
    int cir(int n,int m)
    {
        //p表示总后剩下人的位置
        int p=0;
        //从i=2开始，表示从剩下两个人向n个人反推，得到最总位置p的起始位置
        for(int i=2;i<=n;i++)
        {
            p=(p+m)%i;
        }
        //从坐标0开始，所以最后要+1
        return p+1;
    }
}
