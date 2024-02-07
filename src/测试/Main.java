package 测试;

import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-03-28 19:53
 * 6 = 1*6 2*3
 * 28 = 1*28 2*14 4*7
 * 6 = 1+2+3
 * 28 = 1 +2 +4+7+14
 * 628 = 1*628 2*314 4*157
 *
 *
 */
public class Main {
    public static void main(String[] args) {
        int a = 628;
        for(int index=1;index<100;index++)
            if(a%index==0){
                System.out.println(index+"　"+a/index);
            }
        }

}
