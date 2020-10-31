# Meeting Room

this doc including similar questions of **Meeting Rooms** and follow up questions.

[252. Meeting Rooms](https://leetcode.com/problems/meeting-rooms/)

just sort `O(nlogn)`

```java
class Solution {
    public boolean canAttendMeetings(int[][] A) {
        if (intervals == null) return false;

        Arrays.sort(A, (a, b) -> (a[0] - b[0])); // sort by start time
        for (int i = 0; i + 1 < A.length; i++) {
            if (A[i][1] > A[i + 1][0]) {
                return false;
            }
        }
        return true;
    }
}
```

[56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)

just sort `O(nlogn)`

**overlap:** `newIntervals[start, end]`, `i[start, end]`, if `newIntervals[end] >= i[start]`, newIntervals can including a part of (or whole) of i, then we need to update the value of `newIntervals[end]`

**non-overlap:** just update newIntervals and add it into res

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0])); // sort by start in ascending order
        List<int[]> res = new ArrayList<>();
        int[] newIntervals = intervals[0];
        res.add(newIntervals);

        for (int[] i : intervals) {
            if (newIntervals[1] >= i[0]) {
                newIntervals[1] = Math.max(newIntervals[1], i[1]);
            } else {
                newIntervals = i;
                res.add(newIntervals);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
```
