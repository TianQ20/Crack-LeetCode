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

[1272. Remove Interval](https://leetcode.com/problems/remove-interval/)

Naive solution, covered all 6 situations.

```java
class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[1] < toBeRemoved[0] || interval[0] > toBeRemoved[1]) { // no overlap
                List<Integer> tmpList = new ArrayList<>();
                tmpList.add(interval[0]);
                tmpList.add(interval[1]);
                res.add(tmpList);
            } else if (interval[0] >= toBeRemoved[0] && interval[1] <= toBeRemoved[1]) { // fully overlap
                continue;
            } else if (interval[0] < toBeRemoved[0] && interval[1] > toBeRemoved[0] && interval[1] <= toBeRemoved[1]) { // right overlap
                interval[1] = Math.min(interval[1], toBeRemoved[0]);
                List<Integer> tmpList = new ArrayList<>();
                tmpList.add(interval[0]);
                tmpList.add(interval[1]);
                res.add(tmpList);
            } else if (interval[0] >= toBeRemoved[0] && interval[0] < toBeRemoved[1] && interval[1] > toBeRemoved[1]) { // left overlap
                interval[0] = Math.max(interval[0], toBeRemoved[1]);
                List<Integer> tmpList = new ArrayList<>();
                tmpList.add(interval[0]);
                tmpList.add(interval[1]);
                res.add(tmpList);
            } else { // in the middle
                List<Integer> tmpList1 = new ArrayList<>();
                List<Integer> tmpList2 = new ArrayList<>();
                tmpList1.add(interval[0]);
                tmpList1.add(toBeRemoved[0]);
                tmpList2.add(toBeRemoved[1]);
                tmpList2.add(interval[1]);
                res.add(tmpList1);
                res.add(tmpList2);
            }
        }
        return res;
    }
}
```

Optimization: we only need to consider the begin and end of each interval

```java
class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();
        for (int[] i : intervals) {
            if (i[0] >= toBeRemoved[1] || i[1] <= toBeRemoved[0]) {
                res.add(Arrays.asList(i[0], i[1]));
            } else { // i[1] >= toBeRemoved[0] && i[0] <= toBeRemoved[1]
                if (i[0] < toBeRemoved[0]) {
                    res.add(Arrays.asList(i[0], toBeRemoved[0]));
                }
                if (i[1] > toBeRemoved[1]) {
                    res.add(Arrays.asList(toBeRemoved[1], i[1]));
                }
            }
        }
        return res;
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
