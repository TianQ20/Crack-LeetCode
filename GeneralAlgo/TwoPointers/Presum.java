package GeneralAlgo.TwoPointers;
import java.util.*;

public class Presum {
    int[] arr;

    public Presum(int[] arr){
        this.arr = arr;
    }
    /** 一维前缀和
     *  S[i] = a[1] + a[2] + ... a[i]
        a[l] + ... + a[r] = S[r] - S[l - 1]
    *
    */
    public int SubarraySumK(int k){
        int sum = 0, count = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1); // 初始化presum
        for(int num : arr){
            sum += num;
            if(map.containsKey(sum - k)){
                count+= map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum,0)+1);
        }
        return count;
    }
}