
# OA problems

## Coloring a grid

[DP solution](https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/discuss/574923/JavaC%2B%2BPython-DP-O(1)-Space)

```java
class Solution {
    public int numOfWays(int n) {
        long a121 = 6, a123 = 6, b121, b123, mod = (long)1e9 + 7;
        for (int i = 1; i < n; i++) {
            b121 = a121 * 3 + a123 * 2;
            b123 = a121 * 2 + a123 * 2;
            a121 = b121 % mod;
            a123 = b123 % mod;
        }
        return (int)((a121 + a123) % mod);
    }
}
```

## 1370. Increasing Decreasing String

```java
class Solution {
    public String sortString(String s) {
        StringBuilder res = new StringBuilder();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        while (res.length() < s.length()) {
            helper(res, count, true);
            helper(res, count, false);
        }
        return res.toString();
    }

    private void helper(StringBuilder res, int[] count, boolean flag) {
        for (int i = 0; i < 26; i++) {
            int j = flag ? i : 25 - i;
            if (count[j]-- > 0) {
                res.append((char)(j + 'a'));
            }
        }
    }
}
```

## Grouping Options

```java
public static int calculate(int pos, int prev, int left, int k) {
        if (pos == k) {
            if (left == 0) {
                return 1;
            } else return 0;
        }

        if (left == 0) return 0;

        int ans = 0;
        // Put all possible values greater equal to prev
        for (int i = prev; i <= left; i++) {
            ans += calculate(pos + 1, i, left - i, k);
        }
        return ans;
    }

    public static int countWaystoDivide(int n, int k) {
        return calculate(0, 1, n, k);
    }
```

# Binary Autocomplete

```java
public class Trie {
    private static class TrieNode {
        TrieNode[] children = new TrieNode[2];
        int prefix = -1;

        public TrieNode(int prefix) {
            this.prefix = prefix;
        }
    }


    public static TrieNode root = new TrieNode(-1);
    public static List<Integer> autoComplete(List<String> commands) {
        List<Integer> output = new ArrayList<>();
        int index = 1, res = 0;
        for (String com : commands) {
            TrieNode node = root;
            for (char c : com.toCharArray()) {
                int tmp = c - '0';
                if (node.children[tmp] == null) {
                    node.children[tmp] = new TrieNode(index);
                    node = node.children[tmp];
                    continue;
                }

                res = node.children[tmp].prefix;
                node.children[tmp].prefix = index;
                node = node.children[tmp];
            }

            output.add(res);
            res = index;
            index++;
        }
        return output;
    }

    public static void main(String[] args) { // [0, 1, 1, 1, 2, 5]
        List<String> commands = new ArrayList<>();
        commands.addAll(Arrays.asList("000", "1101", "01", "001", "110", "11"));
        List<Integer> res = autoComplete(commands);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
```

## Divisibility of Strings

```java
public class Divisibility {
    public static int getLength(String s, String t) { // s big, t small
        if (s.length() % t.length() > 0) {
            return -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i * t.length() < s.length(); i++) {
            sb.append(t);
        }
        if (!sb.toString().equals(s)) {
            return -1;
        }

        int divisible = 1;
        for (int i = 1; i <= t.length(); i++) {
            if (t.length() % divisible != 0) {
                divisible++;
                continue;
            }

            sb = new StringBuilder();
            String subStr = t.substring(0, i);
            while (sb.length() < t.length()) {
                sb.append(subStr);
            }
            if (sb.toString().equals(t)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) { // 3, -1
        String s1 = "bcdbcdbcdbcd";
        String t1 = "bcdbcd";
        int res1 = getLength(s1, t1);
        System.out.println(res1);
        String s2 = "bcdbcdbcd";
        String t2 = "bcdbcd";
        int res2 = getLength(s2, t2);
        System.out.println(res2);
    }
}
```

## Factors of 3 and 5

```java
public class Factor {
    public static int nthUglyNumber(int n) {
        int[] nums = new int[n];
        nums[0] = 1;
        int index3 = 0, index5 = 0;

        for (int i = 1; i < n; i++) {
            nums[i] = Math.min(nums[index3] * 3, nums[index5] * 5);

            if (nums[i] == nums[index3] * 3) {
                index3++;
            }
            if (nums[i] == nums[index5] * 5) {
                index5++;
            }
        }
        return nums[n - 1];
    }

    public static int getNumbers(int low, int high) {
        int num = 0;
        int n = 0;
        int res = 0;
        while (num <= high) {
            if (num >= low) {
                res++;
            }
            n++;
            num = nthUglyNumber(n);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getNumbers(200, 405));
        System.out.println(getNumbers(400000, 500000));
        System.out.println(getNumbers(1, 10));
    }
}
```

## [Circular Array](https://gist.github.com/arazmj/c45a450e31f340e968947c564a9771e8)

```java
public class CircularArray {
    public static int circularArrayBruteForce(int n, int[] endNode) {
        int[] count = new int[n + 1];
        for (int i = 0; i < endNode.length - 1; i++) {
            int start = endNode[i];
            int end = endNode[i + 1];

            //if the range is in middle of the array
            if (start <= end) { //[start, end]
                for (int j = start; j <= end; j++)
                    count[j]++;
            } else {  // if the range is composed of the tail and head
                // [start, n]
                for (int j = start; j <= n; j++)
                    count[j]++;
                // [1, end]
                for (int j = 1; j <= end; j++)
                    count[j]++;
            }
        }

        int max = 0, most = 0;
        for (int i = 1; i <= n; i++) {
            if (max < count[i]) {
                max = count[i];
                most = i;
            }
        }
        return most;
    }

    public static int circularArray2(int n, int[] endNode) {
        int m = endNode.length;
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();
        for (int i = 0; i < m - 1; i++) {
            int start = endNode[i];
            int end = endNode[i + 1];
            starts.add(start);
            ends.add(end);
        }

        Collections.sort(starts);
        Collections.sort(ends);

        int most = 1;
        for (int left = 0, right = 0; right < starts.size(); right++) {
            if (starts.get(right) <= ends.get(left)) {
                most = starts.get(right);
            } else {
                left++;
            }
        }

        return most;
    }

    public static void main(String[] args) {
        System.out.println(circularArrayBruteForce(3, new int[]{1, 3, 2, 3}));
        System.out.println(circularArrayBruteForce(10, new int[]{1, 5, 10, 5}));
        System.out.println("-----------");
        System.out.println(circularArray2(3, new int[]{1, 3, 2, 3}));
        System.out.println(circularArray2(10, new int[]{1, 5, 10, 5}));
        System.out.println("-----------");

    }
}
```

## Bucket Fill

```java
public class BucketFill {
    public static int find(String[] board){
        boolean[][] visited = new boolean[board.length][board[0].length()];
        int res = 0;
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (!visited[i][j]) {
                    res++;
                    expand(board, visited, i, j);
                }
            }
        }
        return res;
    }

    public static void expand(String[] board, boolean[][] visited, int i, int j) {
        if (i > 0) {
            if (!visited[i - 1][j] && board[i - 1].charAt(j) == board[i].charAt(j)){
                visited[i - 1][j] = true;
                expand(board, visited, i - 1, j);
            }
        }
        if (j > 0) {
            if (!visited[i][j - 1] && board[i].charAt(j - 1) == board[i].charAt(j)){
                visited[i][j - 1] = true;
                expand(board, visited, i, j - 1);
            }
        }
        if (i < board.length - 1) {
            if (!visited[i + 1][j] && board[i + 1].charAt(j) == board[i].charAt(j)){
                visited[i + 1][j] = true;
                expand(board, visited, i + 1, j);
            }
        }
        if (j < board[0].length() - 1) {
            if (!visited[i][j + 1] && board[i].charAt(j + 1) == board[i].charAt(j)){
                visited[i][j + 1] = true;
                expand(board, visited, i, j + 1);
            }
        }
    }

    public static void main(String[] args) {
        String[] board = new String[] {"aaaba", "ababa", "aaaca"};
        System.out.println(find(board));
        System.out.println(find(new String[] {"bbba", "abba", "acaa", "aaac"}));
    }
}
```

## Account Number Validation

```java
public class AccountNumber {

    // Complete the validateAccountNumber function below
    static String validateAccountNumber(String accountNumber) {

        if (accountNumber.length() != 8) return "INVALID";

        String checkSum = accountNumber.substring(0, 2);

        int checker = 0;

        try {
            checker = Integer.parseInt(checkSum, 16);
        } catch (Exception e) {
            return "INVALID";
        }

        String remainCheck = accountNumber.substring(2);

        int number = 0;
        try{
            number = Integer.parseInt(remainCheck, 16);
        } catch (Exception e) {
            return "INVALID";
        }

        int numberSum = getNumberSum(number);

        return checker == numberSum ? "VALID": "INVALID";
    }

    private static int getNumberSum(int n) {
        int sum = 0;
        while (n != 0) {
            sum += n % 10;
            n /= 10;
        }

        return sum;
    }

    // diver method

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        while (scanner.hasNextLine()) {
            // bufferedWriter.write(validateAccountNumber(scanner.nextLine()));
            // bufferedWriter.newLine();
            System.out.println(validateAccountNumber(scanner.nextLine()));
        }

        // bufferedWriter.close();
        // scanner.close();
    }
}
```

## Statistics

```java
class StatisticsCalculator {
    private HashMap<String, Integer> city2freq = new HashMap<>();
    private int maxFreq = 0;
    private String mostBusyCity = "";
    private int totalMile = 0;
    private HashMap<String, Integer> account2Miles = new HashMap<>();
    private String busiestAccount = "";
    private int totalDis = 0;

    public String Process(String line) {
        String[] frags = line.split("\\:");
        String account = frags[0];
        String fromCity = frags[1];
        String toCity = frags[2];
        int distance = Integer.parseInt(frags[3]);
        updateCityInfo(fromCity);
        updateCityInfo(toCity);
        updateAccountInfo(account, distance);
        totalMile += distance;

        return String.format("%d:%s:%s", totalMile, busiestAccount, mostBusyCity);
    }

    private void updateAccountInfo(String account, int dis) {
        int curDis = account2Miles.getOrDefault(account, 0) + dis;
        account2Miles.put(account, curDis);

        if(curDis == totalDis) {
            busiestAccount = busiestAccount.compareTo(account) > 0 ? account : busiestAccount;
        }

        if(totalDis < curDis) {
            totalDis = curDis;
            busiestAccount = account;
        }
    }

    private void updateCityInfo (String city) {
        int freq = city2freq.getOrDefault(city, 0) + 1;
        city2freq.put(city, freq);
        if (freq == maxFreq) {
            mostBusyCity = mostBusyCity.compareTo(city) > 0? city: mostBusyCity;
        }
        if (freq > maxFreq) {
            maxFreq = freq;
            mostBusyCity = city;
        }
    }
}

public class Statistics {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StatisticsCalculator calculator = new StatisticsCalculator();

    public static void main(String[] args) throws IOException {
        // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        while (scanner.hasNextLine()) {
            // bufferedWriter.write(calculator.Process(scanner.nextLine()));
            // bufferedWriter.newLine();
            System.out.println(calculator.Process(scanner.nextLine()));
        }

        // bufferedWriter.close();
        // scanner.close();
    }
}
```

## Travel Distance

```java
class Location {
    float lat;
    float lon;

    Location(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
class DestinationCalculator {
    private static final int RadiusMiles = 3963;
    private HashMap<String, Location> city2Location= new HashMap<>();

    public String Process(String line) {
        String[] parts = line.split("\\:");

        if(parts[0].equals("TRIP")) {
            long dis = calculateDistance(parts[2], parts[3]);
            return String.format("%s:%s:%s:%d", parts[1], parts[2], parts[3], dis);
        }

        String city = parts[1];
        float lat = Float.parseFloat(parts[2]);
        float lon = Float.parseFloat(parts[3]);
        Location location = new Location(lat, lon);
        city2Location.put(city, location);

        return city;
    }

    private long calculateDistance (String from, String to) {
        Location fromLocation = city2Location.get(from);
        double fromLat = Math.toRadians(fromLocation.lat);
        double fromLon = Math.toRadians(fromLocation.lon);

        Location toLocation = city2Location.get(to);
        double toLat = Math.toRadians(toLocation.lat);
        double toLon = Math.toRadians(toLocation.lon);

        double diff = Math.abs(fromLon - toLon);
        double angle = Math.acos(
                Math.sin(fromLat) * Math.sin(toLat) +
                        Math.cos(fromLat) * Math.cos(toLat) * Math.cos(diff)
        );

        return (long)Math.floor(angle * RadiusMiles);

    }
}

public class Destination {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DestinationCalculator calculator = new DestinationCalculator();

    public static void main(String[] args) throws IOException {
        // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        while (scanner.hasNextLine()) {
            // bufferedWriter.write(calculator.Process(scanner.nextLine()));
            // bufferedWriter.newLine();
            System.out.println(calculator.Process(scanner.nextLine()));
        }

        // bufferedWriter.close();
        // scanner.close();
    }
}
```
