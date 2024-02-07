package shiyan.LinkedList;


/**
 * @author zhp
 * @date 2021-12-16 15:02
 * 顺序表
 */
public class SeqList<T>  implements SeqListInterface<T> {

    public static void main(String[] args) {
        /*插入数据*/
        SeqList<Integer> s=new SeqList<Integer>();
        for(int i=0;i<65;i++){
           s.insert(i);
        }
        System.out.println(s.toString());
        System.out.println("元素个数为:"+s.length);
        System.out.println("容量位:"+s.capacity());
        System.out.println("数据1的位置:"+s.indexOf(1));
        s.set(0,200);
        System.out.println("将第0位设置为200"+s.toString());
        s.set(64,1);
        System.out.println("将第64位设置为1"+s.toString());
        System.out.println("是否包含数字1:"+s.contains(1));
        System.out.println("最后一个为数值为1的元素坐标为:"+s.lastIndexOf(1));
        s.remove(2);
        System.out.println("删除第2位的元素"+s.toString());
        s.removeAll(1);
        System.out.println("删除所有数值为1的元素"+s.toString());
        s.clear();
        System.out.println("清除所有元素：");
        System.out.println(s.toString());

    }


    //参数
    private Object[] data;//存储数据
    private int length;//包含数据个数


    public SeqList(int capacity){
        if(capacity<=0) {
            System.out.println("创建失败");
            return;
        }
        this.data=new Object[capacity];
        this.length=0;
    }

    /**
     * 传入一个数组
     * @param array
     */
    public SeqList(T[] array) {
        if(array==null){
            throw new NullPointerException("数组为空，创建失败");
        }

        this.data=new Object[array.length];
        //将array内数据写入
        for(int i=0;i<array.length;i++){
            this.data[i]=array[i];
        }
        this.length=array.length;


    }

    /**
     * 默认情况创建大小为64
     */
    public SeqList(){
        this(64);
    }

    @Override
    public int capacity() {
        return this.data.length;
    }

    /**
     * 判断是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return 0==length;
    }

    /**
     * 返回长度
     * @return
     */
    @Override
    public int length() {
        return length;
    }

    /**
     * 获取index位置上的数据
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        if(index>=0&&index<=length){
            return (T)data[index];
        }
        return null;
    }


    /**
     * 替换index位置的数值为data 返回旧值
     * @param index
     * @param data
     * @return
     */
    @Override
    public T set(int index, T data) {
        if(data!=null&&index>=0&&index<=length){
            T old= (T)this.data[index];
            this.data[index]=data;
            return old;
        }
        return null;
    }

    /**
     * 在index位置插入数据 其他index位置后的数据后移
     * 数组在满时进行二倍扩容
     * @param index
     * @param data
     * @return
     */
    @Override
    public boolean insert(int index, T data) {
        //数据为空直接返回
        if(data==null) return false;

        //index小于默认插入第一个元素位置
        if(index<0){
            index=0;
        }

        //index大于数组长度 则插入扩容后的第一个位置
        if(index>this.data.length){
            index=this.data.length;
        }

        //若顺序表满了
        if(this.length==this.data.length){
            //保存旧数组
           Object[] old=this.data;
           //扩容两倍
            this.data= new Object[this.length*2];
            //把index前的数据装入
            for(int i=0;i<index;i++){
                this.data[i]=old[i];
            }
        }

        //把index位置上的数据后移
        for(int i=this.length-1;i>=index;i--){
            this.data[i+1]=this.data[i];
        }
        //插入数据
        this.data[index]=data;

        this.length++;

        return true;
    }


    /**
     * 在顺序表尾端加入数值
     * @param data
     * @return
     */
    @Override
    public boolean insert(T data) {
       return  insert(this.length,data);
    }

    /**
     * 删除index位置的数据
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        if(index>=0&&index<=this.length){
            //记录被删除的元素 后续返回
            T toBedeleted=(T)this.data[index];
            /*从index开始 使用后一个元素的值取代当前元素 达到删除的效果*/
            for(int i=index;i<this.length-1;i++){
                this.data[i]=this.data[i+1];
            }
            //将最后一个元素指向null 完成删除操作
            this.data[this.length-1]=null;
            this.length--;

            return toBedeleted;
        }
        return null;
    }

    /**
     * 删除数据data
     * @param data
     * @return
     */
    @Override
    public boolean removeOne(T data) {
        if(data!=null&&this.length!=0){
            //获取data位置
            int index=indexOf(data);
            //删除成功
            if(remove(index)!=null){
                return true;
            }
        }

       //删除失败
        return false;
    }

    /**
     * 删除所有数据data
     * @param data
     * @return
     */
    @Override
    public boolean removeAll(T data) {
        //使用count记录删除掉的值为data的个数
        int count=0;
        if(data!=null&&this.length!=0){
            //循环获取data位置
            while(indexOf(data)!=-1){
                remove(indexOf(data));
                count++;
            }

        }
        //若删除过数据 则返回true
        return count!=0;
    }

    /**
     *清空
     */
    @Override
    public void clear() {
        //直接将数据length设置为0
        this.length=0;
    }

    /**
     * 调用indexof（）函数 若坐标值为-1则返回false
     * @param data
     * @return
     */
    @Override
    public boolean contains(T data) {
        return indexOf(data)>=0;
    }

    /**
     * 返回数据data坐标值
     * @param data
     * @return data的坐标  -1表示无该元素
     */
    @Override
    public int indexOf(T data) {
        if(data!=null){
            for(int i=0;i<this.length;i++){
                if(this.data[i]==data){
                    //返回坐标值
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 返回data最后一次出现的坐标值
     * @param data
     * @return
     */
    @Override
    public int lastIndexOf(T data) {
        //从后往前找
        if(data!=null){
            for(int i=this.length-1;i>=0;i--){
                if(this.data[i]==data){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 输出元素
     * @return
     */
    public String toString(){
        String out="[";

        if(this.length!=0){
            for(int i=0;i<this.length-1;i++){
                out+=this.data[i].toString()+",";
            }
            out+=this.data[this.length-1].toString();

        }


        return out+="]";
    }
}
