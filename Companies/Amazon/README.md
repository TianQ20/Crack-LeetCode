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
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length, n = B.length;
        if (m > n) return findMedianSortedArrays(B, A);

        int i = 0, j = 0, imin = 0, imax = m, half_len = (m + n + 1) / 2;
        double max_left = 0, min_right = 0;
        while (imin <= imax) {
            i = (imin + imax) / 2;
            j = half_len - i;
            if (j > 0 && i < m && B[j - 1] > A[i]) {
                imin = i + 1;
            } else if (i > 0 && j < n && A[i - 1] > B[j]) {
                imax = i - 1;
            } else {
                if (i == 0) {
                    max_left = (double)B[j - 1];
                } else if (j == 0) {
                    max_left = (double)A[i - 1];
                } else {
                    max_left = (double)Math.max(A[i - 1], B[j - 1]);
                }
                break;
            }
        }

        if ((m + n) % 2 == 1) {
            return max_left;
        }

        if (i == m) {
            min_right = (double)B[j];
        } else if (j == n) {
            min_right = (double)A[i];
        } else {
            min_right = (double)Math.min(A[i], B[j]);
        }
        return (double)(max_left + min_right) / 2;
    }
}
```

[5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)

`dp(i, j)` represents whether `s(i ... j)` can form a palindromic substring, `dp(i, j)` is true when `s(i)` equals to `s(j)` and `s(i+1 ... j-1)` is a palindromic substring. When we found a palindrome, check if it's the longest one. Time complexity `O(n^2)`.

```java
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        String res = "";
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i < 3 || dp[i + 1][j - 1]);

                if (dp[i][j] && (j - i + 1 > res.length())) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }
}
```

[8. String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi/)

```java
class Solution {
    public int myAtoi(String s) {
        s = s.trim();
        int sign = 1, i = 0, res = 0;
        if (s.isEmpty()) {
            return res;
        } else if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (s.charAt(i) == '+') {
            i++;
        }

        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            if (res > (Integer.MAX_VALUE - (s.charAt(i) - '0')) / 10) {
                return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + s.charAt(i) - '0';
            i++;
        }
        return sign * res;
    }
}
```

[12. Integer to Roman](https://leetcode.com/problems/integer-to-roman/)

```java
class Solution {
    public String intToRoman(int num) {
        String[] M = new String[] {"", "M", "MM", "MMM"};
        String[] C = new String[] {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] X = new String[] {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] I = new String[] {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }
}
```

```java
class Solution {
    public String intToRoman(int num) {
        if (num < 1 || num > 3999) return "";
        int[] values = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int i = 0;
        StringBuilder res = new StringBuilder();
        while (num > 0) {
            while (num >= values[i]) {
                num -= values[i];
                res.append(roman[i]);
            }
            i++;
        }
        return res.toString();
    }
}
```

[13. Roman to Integer](https://leetcode.com/problems/roman-to-integer/)

```java
class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int len = s.length();
        int sum = map.get(s.charAt(len - 1));
        for (int i = len - 2; i >= 0; i--) {
            if (map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                sum -= map.get(s.charAt(i));
            } else sum += map.get(s.charAt(i));
        }
        return sum;
    }
}
```

[20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)

```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
            if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            }
            if (c == '}') {
                if (stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
```

[21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)

recursion

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
```

iteration

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }
        return dummy.next;
    }
}
```

[23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)

using priority queue, every time we compare the head node value of each list.

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        // sort by value in ascending order
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        while (!pq.isEmpty()) {
            tail.next = pq.poll();
            tail = tail.next;
            if (tail.next != null) {
                pq.add(tail.next);
            }
        }
        return dummy.next;
    }
}
```

reusing merge two sorted list solution

the progress of combination is like a full binary tree, from bottom to top. So on every level of tree, the combination complexity is n, beacause every level have all n numbers without repetition. The level of tree is x, ie logk. So the complexity is O(nlogk).

```java
for example, 8 ListNode, and the length of every ListNode is x1, x2,
x3, x4, x5, x6, x7, x8, total is n.

on level 3: x1+x2, x3+x4, x5+x6, x7+x8 sum: n

on level 2: x1+x2+x3+x4, x5+x6+x7+x8 sum: n

on level 1: x1+x2+x3+x4+x5+x6+x7+x8 sum: n

total 3n, nlog8
```

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        if (lists.length == 2) return mergeTwoLists(lists[0], lists[1]);
        ListNode[] left = Arrays.copyOfRange(lists, 0, lists.length / 2);
        ListNode[] right = Arrays.copyOfRange(lists, lists.length / 2, lists.length);
        return mergeTwoLists(mergeKLists(left), mergeKLists(right));
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }
        return dummy.next;
    }
}
```

credit to [leetcode post wksora comment](https://leetcode.com/problems/merge-k-sorted-lists/discuss/10528/A-java-solution-based-on-Priority-Queue)
