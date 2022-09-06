package 每日任务;

import java.util.Iterator;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-31 1:36
 * https://leetcode.cn/problems/flatten-nested-list-iterator/solution/bian-ping-hua-qian-tao-lie-biao-die-dai-ipjzq/
 */
public class 扁平化嵌套列表迭代器_lc_341 {

      interface NestedInteger {

              // @return true if this NestedInteger holds a single integer, rather than a nested list.
             public boolean isInteger();

              // @return the single integer that this NestedInteger holds, if it holds a single integer
              // Return null if this NestedInteger holds a nested list
             public Integer getInteger();

              // @return the nested list that this NestedInteger holds, if it holds a nested list
              // Return empty list if this NestedInteger holds a single integer
             public List<NestedInteger> getList();
     }

    class NestedIterator implements Iterator<Integer> {
        public List<NestedInteger> nestedIntegers;
        public int index;//当前遍历到的元素坐标
        public int size;//nestedIntegers的尺寸大小
        public NestedIterator iterator;//用来访问列表的迭代器


        public NestedIterator(List<NestedInteger> nestedList) {

                nestedIntegers = nestedList;
                size = nestedList.size();
        }

        @Override
        public Integer next() {
            NestedInteger cur = nestedIntegers.get(index);
            if(cur.isInteger()){
                index++;//指向下一个元素
                return cur.getInteger();
            }else{
                return iterator.next();//对列表递归查找整型元素
            }
        }

        @Override
        public boolean hasNext() {
            while(index<size){
                NestedInteger cur = nestedIntegers.get(index);
                if(cur.isInteger()){
                    return true;
                }else{
                    if(iterator==null){
                        iterator = new NestedIterator(cur.getList());
                    }

                    if(iterator.hasNext()){
                        return true;
                    }else{
                        iterator = null;
                        index++;//当前列表为空，跳到下一个元素
                    }
                }
            }
            return false;
        }
    }
}
