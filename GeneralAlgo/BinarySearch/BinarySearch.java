package GeneralAlgo.BinarySearch;

public class BinarySearch {

    int[] arr;
    int len;
    public BinarySearch(int[] arr, int len){
        this.arr = arr;
        this.len = len;
    }

    /**
     * 模版1：find the smallest numebr that arr[i] >= target ｜｜ arr[i] > target
     * 这种情况下，区间[l,r] 可以被分成[l, mid] 和 [mid+1, r]
     * 此时更新的时候，我们要么l = mid + 1 或者 r = mid;
     */
    public int search_larger(int target){
        int left = 0;
        int right = len-1;
        while(left < right){
            int mid = left + ( right - left )/ 2;
            if(arr[mid] >= target)right = mid;  // > 也通用
            else left = mid + 1;
        }
        return arr[left] >= target ? left : -1;
    }

    /**
     * 模版2： find the largest number that arr[i] <= target  || arr[i] < target
     * 这种情况下，区间[l,r] 可以被分成[l, mid-1] 和 [mid, r]
     * 此时更新的时候，我们要么l = mid  或者 r = mid + 1;
     *
     *
     * PS: !!!!!! 为了防止死循环，此时mid 要变成 left + ( right - left + 1 ) / 2.
     *
     */
    public int search_smaller(int target){
        int left = 0;
        int right = len - 1;
        while(left < right){
            int mid = left + ( right - left + 1 ) / 2;
            if(arr[mid] <= target)left = mid; // < 也通用
            else right = mid - 1;
        }
        return arr[left] <= target ? left : -1;
    }

    /**
     * 模版3 find the target value in the array that arr[i] == target
     * 这种情况下，将区间[l,r] 分成 [l, mid-1] , mid, [mid+1, r]
     * 此时while里的条件是l<=r， 而不是单纯的 l<r了
     * 如果arr[mid] != target, 按照arr[i] 和 target的关系移动l = mid + 1 或者 r = mid - 1
     */

    public int search_equal(int target){
        int left = 0, right = len-1;
        while(left <= right){
            int mid = left + ( right - left )/2;
            if(arr[mid] == target)return mid;
            else if( arr[mid] < target){
                left = mid + 1;
            }else right = mid - 1;

        }
        return -1;
    }
}
