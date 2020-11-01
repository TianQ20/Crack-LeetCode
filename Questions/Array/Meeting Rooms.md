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

Two pointers solution

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);

        List<int[]> res = new ArrayList<>();
        int endIndex = 0, startIndex = 0;
        while (endIndex < n) {
            if (endIndex == n - 1 || starts[endIndex + 1] > ends[endIndex]) {
                res.add(new int[] {starts[startIndex], ends[endIndex]});
                startIndex = endIndex + 1;
            }
            endIndex++;
        }
        return res.toArray(new int[res.size()][]);
    }
}
```

[435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/)

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;

        int n = intervals.length, count = 1;
        Arrays.sort(intervals, (a, b) -> (a[1] - b[1])); // sort by end
        int end = intervals[0][1];
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
                count++;
            }
        }
        return n - count;
    }
}
```

[253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)

```java
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);

        int rooms = 0, endIndex = 0;
        for (int i = 0; i < n; i++) {
            if (starts[i] < ends[endIndex]) {
                rooms++;
            } else {
                endIndex++;
            }
        }
        return rooms;
    }
}
```
