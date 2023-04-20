import 链表.ListNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @author zhp
 * @date 2023-04-15 10:30
 */
public class test4 {
    public static void main(String[] args) {
        test4 test4 = new test4();
//        printTable();
//        int[] process = test4.process(new int[]{2, 7, 11, 15}, 9);
//        for (int num: process) {
//            System.out.println(num);
//
//        }
        boolean check = test4.check(010);
        System.out.println(check);
    }

    public String longestCommonPrefix(String[] strs){
        if(strs==null || strs.length<1){
            return null;
        }
        Arrays.sort(strs);
        String first = strs[0];
        String end = strs[strs.length-1];
        int min = Math.min(first.length(),end.length());
        int i ;
        for(i=0;i<min;i++){
            if(first.charAt(i)!=end.charAt(i)){
                break;
            }
        }
        return first.substring(0,i);
    }

     class ListNode {

        int val;
        ListNode next;
       ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }

    public int mySqrt(int x){
        if(x==0) return 0;
        int l =1;
        int r = x;
        while(l<r){
            int mid = (r-l+1)/2+l;
            if(mid>x/mid){
                r=mid-1;
            }
            else{
                l=mid;
            }
        }
        return l;
    }
    public int getMaxSum(int nums[]){
        int maxSum = nums[0],sum=0;
        for(int num:nums){
            sum+=num;
            maxSum = Math.max(maxSum,sum);
            sum = Math.max(sum,0);
        }
        return maxSum;
    }

    public int search(int []nums,int target){
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(nums[mid]==target){
                return mid;
            }
            else if(nums[mid]>=target){
                right = mid - 1;
            }
            else{
                left = mid+1;
            }
        }
        return left;
    }
    public ListNode merge(ListNode l1,ListNode l2){
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2)-> o1.val-o2.val);
        pq.add(l1);
        pq.add(l2);
        ListNode preHead = new ListNode(-1);
        ListNode pre = preHead;
        while(!pq.isEmpty()){
            ListNode poll = pq.poll();
            pre.next = poll;
            if(poll.next!=null){
                poll = poll.next;
                pq.add(poll);
            }
            pre = pre.next;
        }
        return preHead.next;
    }
    public boolean check(String str){
        Stack<Character> stack  = new Stack<>();
        for(char ch : str.toCharArray()){
            if(ch=='('||ch=='['||ch=='{') {
                stack.push(ch);
                continue;
            }
            else if(ch==')'){
                if(stack.peek()!='(') return false;
            }
            else if(ch==']'){
                if(stack.peek()!='[') return false;
            }
            else if(ch=='}'){
                if(stack.peek()!='}') return false;
            }
            stack.pop();
        }
        return true;
    }
    public boolean check(int val){

        String s = String.valueOf(val);
        int l =0;
        int r = s.length()-1;
        while(l<=r){
            if(s.charAt(l++)!=s.charAt(r--)) return false;
        }
        return true;
    }


    public int[] process(int []nums,int target){
        Arrays.sort(nums);
        int l=0,r=nums.length-1;
        while(l<r){
            int sum =nums[l]+nums[r];
            if(sum==target) return new int[]{l,r};
            if(sum<target) l++;
            else if(sum>target) r--;
        }
        return new int[]{-1,-1};
    }

    public static void printTable(){
        for(int i=1;i<=9;i++){
            for(int j=1;j<=i;j++){
                int k =i*j;
                System.out.print(j+"*"+i+"="+k+"\t");
            }
            System.out.println();
        }
    }
}
