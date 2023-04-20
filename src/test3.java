import java.net.Socket;
import java.util.*;

/**
 * @author zhp
 * @date 2023-04-10 19:13
 */
public class test3 {

//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int n = scan.nextInt();
//        int k = scan.nextInt();
//        double nums [] = new double[n];
//        for(int i=0;i<n;i++){
//            nums[i] = scan.nextDouble();
//        }
//
//        Arrays.sort(nums);
//        double ans = 0;
//        for(int i=0;i<k-1;i++){
//            ans+=nums[i];
//        }
//
//        double sum = 0;
//        for(int i=k-1;i<n;i++){
//            sum+=nums[i];
//        }
//        System.out.println(ans + sum/(n-k+1));
//
//
//
//
//    }

//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int n = scan.nextInt();
//        StringBuilder sb = new StringBuilder();
//        int judge = get(n);
//        int x = judge(n);
//        for(int i=0;i<x;i++){
//            sb.append("r");
//        }
//        System.out.println(sb.toString());
//
//        if(judge==n) {
//            return;
//        }
//
//        n=n-x;
//
//
//
//        while(n!=0){
//        if(n==1) {
//            sb.append("r");
//            break;
//        }
//        else if(n==2) {
//            sb.append("re");
//            break;
//        }
//
//        else{
//            for(int i=0;i<n/3;i++){
//                sb.append("red");
//            }
//        }
//        n%=3;
//    }
//            System.out.println(sb.toString());
//}
//
//    static int judge(int target){
//        int sum =0;
//        for(int i=1;i<target;i++){
//            sum+=i;
//            if(sum==target) return i;
//            if(sum>target) return -1;
//        }
//        return -1;
//    }
//
//    static int get(int target){
//        int sum =0;
//        for(int i=1;i<target;i++){
//            if(sum+i>target) return sum;
//            sum+=i;
//        }
//        return -1;
//    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        ArrayList<String> list = new ArrayList<>();
        scan.nextLine();
        for(int i=0;i<n;i++){
            list.add(scan.nextLine());
        }
        Map<String,ArrayList<String>> map = new HashMap<>();
        for(String line : list){
            String[] split = line.split("\\(");
            String fun[]=  split[0].split("\\s+");
            String funcName = fun[1];


            String param[] = split[1].split(",");
            String ans = "";
            for(String para:param){
                if(para==")") break;
                String[] split1 = para.split("\\s+");
                 ans += split1[0];
            }
            ArrayList<String> list1 = map.get(funcName);
            if(list1==null){
                System.out.println("Yes");
                list1 = new ArrayList<>();
                list1.add(ans);
                map.put(funcName,list1);
            }

            else {
                boolean flag= false;
                for(String string: list1){
                    if(ans.equals(string)){
                        System.out.println("No");
                        flag=true;
                        break;
                    }
                }
                if(flag) continue;
                System.out.println("Yes");
                list1.add(ans);
            }

        }
    }

    static void test(){
        String ans = "int f(int x)";
        String[] split1 = ans.split("\\s+");
        ans.replace(')',' ');
        String[] split = ans.split("\\(");
        System.out.println(split1[0]+ "*"+split1[1]);

    }
}
