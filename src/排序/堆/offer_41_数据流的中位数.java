package 排序.堆;

import java.util.PriorityQueue;

/**
 * @author zhp
 * @date 2022-06-12 22:56
 * 找到数据流的中位数
 */
public class offer_41_数据流的中位数 {
    /**
     * 基本思路：
     * 为了快速找到数据流的中位数，可以使用一个大根堆和一个小根堆
     * 将数据流分成两部分，最后根据数字数量去除堆中的中位数
     *
     * 具体思路：
     * 用一个最大堆，一个最小堆分别维护排序后的数据流的左右两部分，
     * 插入时如果两个堆中元素数量一样，那么我们规定始终往最大堆中添加元素（最小堆也行），
     * 但添加前我们要把该元素加入最小堆中，再从最小堆中取出堆顶元素加入最大堆中。
     * 插入时如果最大堆多一，就以相同的方法把元素加入到最小堆中
     *
     * 算法优势：
     * 主要分析一下这种方法与单纯维护一个列表来说有什么优势
     * 取中位数都是一样的O(1)
     * 插入元素，在列表中需要O(logn)（列表是有序的）来寻找插入的下标，再O(n)来插入元素。而对于堆来说只需要O(logn)即可完成插入元素
     * ————————————————
     * 版权声明：本文为CSDN博主「eyvr」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/qq_46636391/article/details/124538158
     */
    PriorityQueue<Integer> min = new PriorityQueue<>((o1, o2) -> o1-o2);//小根堆
    PriorityQueue<Integer> max = new PriorityQueue<>((o1, o2) -> o2-o1);//大根堆

    /**
     * 在大根堆内维护小于中位数的所有数
     * 小根堆内维护大于中位数的所有数
     * @param num
     */
    public void addNum(int num){
        //让大根堆数量>=小根堆数量，在输出时更方便
        if(min.size()==max.size()){
            //先将要加入的数放入小根堆，得到目前最小的数,再加入大根堆
            min.add(num);
            max.add(min.poll());
        }else{
            max.add(num);
            min.add(max.poll());
        }
    }

    public double getMiddleNum(){
        if(min.size() == max.size()){//偶数个
            return ((double)min.peek()+max.peek())/2;//保证返回的是正确的
        }else{//奇数个数
            return (double) max.peek();//按照预先设置，如果有奇数个数，那么这个数一定在大根堆内，
        }
    }

    public static void main(String[] args) {
        offer_41_数据流的中位数 test = new offer_41_数据流的中位数();
        test.addNum(2);
        test.addNum(4);
        test.addNum(1);
        test.addNum(7);
        test.addNum(8);

        System.out.println(test.getMiddleNum());
    }
}
