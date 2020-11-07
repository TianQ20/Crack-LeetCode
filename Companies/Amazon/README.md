# [Amazon Final Interview Questions | SDE1](https://leetcode.com/discuss/interview-question/488887/Amazon-Final-Interview-Questions-or-SDE1)

top list from leetcode post.

[1. Two Sum](https://leetcode.com/problems/two-sum/)

brute force `O(n^2)` time

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {};
    }
}
```

use hashmap to store index, `O(n)` time & space

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                return new int[] {i, map.get(diff)};
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }
}
```

follow up: [167. Two Sum II - Input array is sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)

two pointers: `O(n)` time

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            if (sum > target) {
                hi--;
            } else if (sum < target) {
                lo++;
            } else return new int[] {lo + 1, hi + 1};
        }
        return new int[]{};
    }
}
```

[4. Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)

naive solution: `O((m+n)log (m+n))` time, cause we use sort

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;
        int[] arr = new int[n];
        for (int i = 0; i < nums1.length; i++) {
            arr[i] = nums1[i];
        }
        for (int i = 0; i < nums2.length; i++) {
            arr[nums1.length + i] = nums2[i];
        }
        Arrays.sort(arr);
        return n % 2 == 0 ? (double)(arr[n / 2 - 1] + arr[n / 2]) / 2 : (double)arr[n / 2];
    }
}
```

naive solution: `O(m+n)` time

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int n = len1 + len2;
        int[] arr = new int[n];
        int i1 = 0, i2 = 0, index = 0;
        while (i1 < len1 && i2 < len2) {
            if (nums1[i1] < nums2[i2]) {
                arr[index++] = nums1[i1++];
            } else {
                arr[index++] = nums2[i2++];
            }
        }
        while (i1 < len1) {
            arr[index++] = nums1[i1++];
        }
        while (i2 < len2) {
            arr[index++] = nums2[i2++];
        }
        return n % 2 == 0 ? (double)(arr[n / 2 - 1] + arr[n / 2]) / 2 : (double)arr[n / 2];
    }
}
```

follow up: The overall run time complexity should be `O(log (m+n))`.

```java

```
