package GeneralAlgo.TwoPointers;

import java.util.Map;
import java.util.HashMap;

public class At_Most_K_or_K_distinct_characters {
    String s;

    public At_Most_K_or_K_distinct_characters(String s){
        this.s = s;
    }
    // using map to store distinct chars number
    public int atMostKDistinct(int k){
        if(s == null || s.length() ==0)return 0;
        int res = 0;
        Map<Character,Integer> map = new HashMap<>();
        for(int left=0, right =0; right < s.length(); right++){
            char temp = s.charAt(right);
            map.put(temp, map.getOrDefault(temp,0)+1); // increase current char's count
            while(map.size() > k){ // more than k distinct chars
                char leftchar = s.charAt(left);
                map.put(leftchar,map.get(leftchar)-1); // shrink left char's count
                if(map.get(leftchar) == 0){
                    map.remove(leftchar);// add more slots for another char
                }
                left++; // move left pointer
            }
            res = Math.max(res, right - left+1);
        }
        return res; //return max length
    }
    /**
     * 如果要找到正好K个distinct char的max length，可以用 atMost(k)-atMost(k-1), 因为两者是包含的关系。
     * atMost(k)里面包含了atMost(k-1)的情况。
     */
    public int KDistinctChar(int k){
        return atMostKDistinct(k) - atMostKDistinct(k-1);
    }
}