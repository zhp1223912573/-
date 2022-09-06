package shiyan;

import java.io.*;
import java.util.Scanner;

/**
 * @author zhp
 * @date 2021-12-20 11:34
 * 作为输入流传入文件中的图
 */
public class In {
    private Scanner scanner;

    /**
     * 标准输入流
     */
    public In(){
        scanner=new Scanner(System.in);
    }

    /**
     * 从文件中创建输入流
     * @param filename
     */
    public In(String filename) {
        assert filename!=null;
        File file = new File(filename);
        try{
            if(file.exists()){
                FileInputStream fileInputStream = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fileInputStream),"UTF-8");
            }
            else{
                throw new IllegalArgumentException("该文件不存在！");
            }
        }
        catch (IOException exception){
                throw new IllegalArgumentException();
        }

    }

    /**
     * 返回一个int型数值
     * @return
     */
    public int readInt(){
        return scanner.nextInt();
    }

    /**
     * 返回一个double型数值
     * @return
     */
    public double readDouble(){
        return scanner.nextDouble();
    }


}
