package GeneralAlgo.TwoPointers;

import java.util.*;

public class LongestStringWithoutDuplicate {

    String s;

    public LongestStringWithoutDuplicate(String s){
        this.s = s;
    }

    /**
     * 一般写法：
     * for (int i = 0, j = 0; i < n; i ++ ){
     *      while (j < i && check(i, j)){
     *          j ++ ;
     *          // 具体问题的逻辑
     *      }
     * }

     * 常见问题分类：
     *     (1) 对于一个序列，用两个指针维护一段区间
     *     (2) 对于两个序列，维护某种次序，比如归并排序中合并两个有序序列的操作

     * 在区间[star, end]里，end右移时，如果找到了重复字母，start右移的，直到字母不重复
     *
     * 方法1: 用set存遇见过的char，如果重复遇见则从start开始remove
     *
     * 方法2: 用128的Array记录char的次数，如果>1的则从start开始--，直到start位置的freq变为0更新res
     */

    public int longestStringWithNoDuplicate1(String s){
        int res = 0;
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0;
        while(left < right){
            if(!set.contains(s.charAt(right))){
                res = Math.max(res, right - left + 1);
                set.add(s.charAt(right++));
            }else{
                set.remove(s.charAt(left++));
            }
        }
        return res;
    }


    public int longestStringWithNoDuplicate2(String s){
        int res = 0;
        int[] chrs = new int[128];
        for(int left = 0, right = 0; right < s.length(); right++){
            chrs[s.charAt(right) -'a']++;
            while(left <= right && chrs[s.charAt(right)-'a'] > 1){
                chrs[s.charAt(left++) -'a']--;
                res = Math.max(res, right - left + 1);
            }
        }
        return res;
    }
}