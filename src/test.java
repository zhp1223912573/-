import org.junit.Test;
import 树.TreeNode;
import 测试.In;

import java.util.*;

/**
 * @author zhp
 * @date 2022-07-17 10:39
 */
public class test {

    public static void main(String[] args) {
        System.out.println(count(8));
    }
    public static int count(int n){
        if(n<2){
            return n;
        }else{
            return n%2==0?count(n/2)+2:count(n-1)+1;
        }
    }
}