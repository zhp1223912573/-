package test;

/**
 * @author zhp
 * @date 2022-02-22 15:02
 */
public class a {



    static class student{
        public int number;

        public student(int number) {
            this.number = number;
        }
    }

    static void set(student s){
        s.number = 2;

    }

    static student test(){
        student s = null;
        try{
             s = new student(1);
            return s;
        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            s.number  = 2;
            return s;
        }
    }
    private static int count = 1;
    public static void main(String[] args) {

        //返回Java虚拟机中的堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        //返回Java虚拟机试图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is a new method!");
            }
        }
        ).start();
        System.out.println("-Xms : " + initialMemory + "M");
        System.out.println("-Xmx : " + maxMemory + "M");

        System.out.println("系统内存大小为：" + initialMemory * 64.0 / 1024 + "G");
        System.out.println("系统内存大小为：" + maxMemory * 4.0 / 1024 + "G");

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
