package shiyan.Sort;

import shiyan.In;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author zhp
 * @date 2021-12-21 21:12
 */
public class Sort {

    public static void main(String[] args) {
       testSortAlg("Heap");
    }
    /**
     * 选择排序
     * 找到最小的元素 将该元素和未排序好的数组的最左端元素进行交换,直到数组末尾。
     * @param a
     */
    public static void Selection(Comparable[] a){
        for(int i=0;i<a.length;i++){
            //选择数组最左为最小元素
            int  min=i;
            for(int j=i+1;j<a.length;j++){
                //若出现小于以min为坐标的元素 则更新min
                if(less(a[j],a[min])) min=j;
            }
            exchange(a,i,min);
            show(a);
        }

    }

    /**冒泡排序
     *将数组中相邻元素从前往后依次进行比较，如果前一个元素比后一个元素大，则交换，一趟下来后最大元素就在数组的末尾。
     */
    public static void Bubblesort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = 0;
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 插入排序
     * 为了将元素放置在正确位置 需要把其他元素在插入之前都向右移动一位
     */
    public static void Insertion(Comparable[]a){
        for(int i=0;i<a.length-1;i++){
            for(int j=i+1;j>0&&less(a[j],a[j-1]);j--){
                exchange(a,j,j-1);
                show(a);
            }
        }
    }

    /**
     * 希尔排序
     * 使数组中任意间隔为h的元素都是有序的 这样的数组被成为h有序数组
     * 如果h很大 就能将元素移动到很远的地方
     */
    public static void Shell(Comparable[]a){
        int N=a.length;
        int h=1;
        while(h<N/3) h=3*h+1;
        while(h>=1){
            for(int i=h;i<N;i++){
                for(int j=i;j>=h&&less(a[j],a[j-h]);j-=h){
                    exchange(a,j,j-h);
                    show(a);
                }
            }
            h/=3;
        }

    }

    /**
     * 快速排序
     * @param a
     */
    public static void Quick(Comparable[]a){
        QuickSort(a,0,a.length-1);
    }

    /**
     * 快速排序
     * 思路：
     * 在数组中选定一个切分元素
     * 在数组中用左右指针分别指向未排序数组的左右端点
     * 若左指针遇到大于切分元素 则记录下来
     * 若右指针遇到小于切分元素 则记录下来
     * 交换两者
     * 重复上述操作
     * 直到两指针相遇 则将切分元素与相遇坐标的元素进行交换
     * 从而使切分元素左侧元素都小于他 右侧元素都大于他
     * 接着递归处理左侧数组，右侧数组
     *
     * @param a
     * @param low
     * @param high
     */
    private static void QuickSort(Comparable[]a,int low,int high){
        if(low>=high) return;
        int j=partition(a,low,high);
        QuickSort(a,low,j-1);
        QuickSort(a,j+1,high);
    }

    private static int partition(Comparable[]a,int low,int high){
        Comparable element=a[low];//选定最左端元素为切分元素
        //左右指针找符合条件的元素
        int left=low;
        int right=high+1;
        while(true){
            while(less(a[++left],element)) if(left==high) break;//限定范围
            while(less(element,a[--right])) if(right==low) break;
            //左指针大于右指针 说明已经将所有元元素放置在他应该在的一侧 直接退出循环
            if(left>=right) break;
            //找到了需要交换的元素
            exchange(a,left,right);
            show(a);
        }
        //right永远小于等于left
        exchange(a,low,right);
        show(a);
        return right;
    }

    public int partition(int[] nums,int low,int high){
        //选定随机位置的值作为pivot
        Random random = new Random(System.currentTimeMillis());
        int randomIndex = low + random.nextInt(high-low+1);

        /**新的快排代码（相比于之前的，更简洁，更好理解）
         */

        swap(nums,randomIndex,low);//将随机选择的pivot放置在区间的第一个元素
        int pivot = nums[low];//得到该标兵数
        int index = low;
        for(int i=low+1;i<=high;i++){
            if(nums[i]>=pivot){
                //当前遍历到的数大于pivot值，则将其与index位置上的数交换，保证大于pivot的所有数位于数组前端
                swap(nums,index+1,i);
                index++;
            }
        }
        //不要忘了将low位置的数值交换到数组中的index停留位置。
        swap(nums,index,low);

        return index;


    }
    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;

    }


    /**
     * 归并排序参数及函数
     */
    static Comparable[] temp;//merge函数内做辅组数组

    public static void Merge(Comparable[]a){
       temp=new Comparable[a.length];
       MergeSort(a,0,a.length-1);
       // MergeSortBU(a);
    }

    /**
     *归并排序
     * 将数组从待排序数组的中点分割
     * 不断递归 直到最小数组（只有一个元素）
     * 此时将数组进行合并操作
     * 保证每个递归的数组都是有序的
     * 不断向上返回有序的子数组
     * 再通过子数组归并成大数组 直到完全排序完成
     *
     * @param a
     * @param low
     * @param high
     */
    private static void MergeSort(Comparable[] a,int low,int high){
        if(low>=high) return;   //分裂完成
        int mid=low+(high-low)/2;
        MergeSort(a,low,mid);  //向左分
        MergeSort(a,mid+1,high);   //向右分
        merge(a,low,mid,high);
        show(a);
    }

    /**
     * 自顶向下的归并排序
     * 两两归并 四四归并 。。。直到最后第二个数组大小小于第一个数组
     * @param a
     */
    private static void MergeSortBU(Comparable[]a){
        int N=a.length;
        for(int size=1;size<N;size=size+size){//size代表子数组的大小
            for(int i=0;i<N-size;i=i+size+size){
                merge(a,i,i+size-1,Math.min(i+size+size-1,N-1));
                show(a);
            }
        }

    }

    /**
     * 依据辅助数组temp将low-mid，mid+1-high的数值进行排序合并
     * @param a
     * @param low
     * @param mid
     * @param high
     */
    private static void merge(Comparable[]a,int low,int mid,int high){
        //将数组a中要进行合并的元素复制到temp中
        for(int i=low;i<=high;i++){
            temp[i]=a[i];
        }
        //left,right分别指向要合并的两个数组的最左端元素
        int left=low;
        int right=mid+1;

        for(int i=low;i<=high;i++){
            if(left>mid)   a[i]=temp[right++];//左半边元素用尽
            else if(right>high) a[i]=temp[left++];//右半边元素用尽
            else if(less(a[left],a[right])) a[i]=temp[left++];//左指针指向的元素更小
            else       a[i]=temp[right++];
        }
    }

    private static void exchange(Comparable[] a, int i,int j){
        Comparable temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }

    private static boolean less(Comparable a,Comparable b){
        return a.compareTo(b)<0;
    }

    private static void show(Comparable[]a){
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();

    }

    private static Integer[] randint(int N){
        Integer[] d= new Integer[N];
        Random random=new Random();
        for(int i=0;i<N;i++){
            d[i]=random.nextInt(1000);
        }

        return d;
    }


    /**
     * 堆排序
     * @param a
     */
    private static void Heap(Comparable[]a){
        //根据传入的数组a构建堆
        int n=a.length;
        //从数组的一半开始构建堆即可
        for(int i=n/2;i>=1;i--){
            sink(a,i,n);
        }

        while(n>1){
            //将堆顶的最大元素与最后元素进行交换  同时减少n 表示堆中元素减少
            exchangeForHeap(a,1,n--);
            //将上述操作中放置在堆顶的元素向下sink 重新获得堆顶元素为最大元素的堆
            sink(a,1,n);
            show(a);
        }
    }

    /**
     * 下沉操作 将某一节点下沉到比他大的元素下方
     * @param a
     * @param parent
     * @param num
     */
    private static void sink(Comparable[]a,int parent,int num){
        //要下沉的节点有子节点
        while(parent*2<=num){
            int child=2*parent;
            //如果右子节点比左子节点更大 则将其与父子节点进行比较 判断是否下沉
            if(child+1<=num&& less(a,child,child+1)) child++;
            //父节点大于左右子节点 无需下沉
            if(less(a,child,parent)) break;
            exchangeForHeap(a,child,parent);
            parent=child;
        }
    }

    private static boolean less(Comparable[]a,int i,int j){
        return a[i-1].compareTo(a[j-1])<0;
    }

    private static void exchangeForHeap(Comparable[] a, int i, int j) {
        Comparable temp=a[i-1];
        a[i-1]=a[j-1];
        a[j-1]=temp;
    }


    /**
     * 选择要使用的排序方法 alg排序方法的名称
     * @param alg
     */
    public static void testSortAlg(String alg){
        Integer[] randint = randint(10);
        if(alg.equals("Selection")) Selection(randint);
        else if(alg.equals("Insertion")) Insertion(randint);
        else if(alg.equals("Shell")) Shell(randint);
        else if(alg.equals("Merge")) Merge(randint);
        else if(alg.equals("Quick"))Quick(randint);
        else if(alg.equals("Heap")) Heap(randint);
    }


}
