package 字符串;

/**
 * @author zhp
 * @date 2021-11-08 12:13
 */
/*https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/*/
public class offer_58 {

    public String reverseLeftWords(String s, int n) {

        /*一，字符串切片 直接将字符串进行反转即可*/
        return s.substring(n,s.length())+s.substring(0,n);

        /*二，列表遍历拼接*/
        
    }


}
