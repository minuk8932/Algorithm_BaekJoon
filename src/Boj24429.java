import common.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Boj24429 {

    private static final int INF = -1_000_000_000;

    private static int[][] dp;
    private static int[][] map;

    private static List<List<Point<Integer, Integer>>> checkPoint = new ArrayList<>();
    private static Map<Integer, Integer> points = new TreeMap<>();

    private static int P;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        init(n);

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        P = Integer.parseInt(br.readLine());
        int loop = P;

        Point<Integer, Integer> start = new Point.Builder(n, n).build();
        checkPoint.get(0).add(start);
        points.put(0, 1);

        while(loop-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            Point target = new Point.Builder(row, col).build();
            int distance = manhattanDistance(target, start);
            points.merge(distance, 1, Integer::sum);

            checkPoint.get(distance).add(target);
        }

        int last = (n - 1) << 1;
        checkPoint.get(last).add(new Point.Builder(1, 1).build());
        points.put(last, 1);

        System.out.println(process(n));
    }

    private static int process(int n) {
        checkPoint.get((n - 1) << 1).add(new Point.Builder(1, 1).build());
        Point<Integer, Integer> start = checkPoint.get(0).get(0);
        int answer = map[start.getRow()][start.getCol()];
        dp[start.getRow()][start.getCol()] = answer;

        for(Map.Entry<Integer, Integer> entry: points.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            if(value >= 2) return -1;
            if(key == 0) continue;

            Point<Integer, Integer> next = checkPoint.get(key).get(0);
            Point<Integer, Integer> up =
                new Point.Builder(start.getRow() - 1, start.getCol()).build();
            Point<Integer, Integer> left =
                new Point.Builder(start.getRow(), start.getCol() - 1).build();

            int cost = Math.max(recursion(up, next), recursion(left, next));
            if(cost < 0) return -1;

            answer += cost;
            start = next;
        }

        return answer;
    }

    private static int manhattanDistance(
        Point<Integer, Integer> target
        , Point<Integer, Integer> start) {

        return Math.abs(target.getRow() - start.getRow())
            + Math.abs(target.getCol() - start.getCol());
    }

    private static int recursion(
        Point<Integer, Integer> current
        , Point<Integer, Integer> destination) {

        if(current.getRow() == destination.getRow() && current.getCol() == destination.getCol())
            return dp[current.getRow()][current.getCol()] = map[current.getRow()][current.getCol()];

        if(current.getRow() < destination.getRow() || current.getCol() < destination.getCol())
            return dp[current.getRow()][current.getCol()] = INF;

        if(dp[current.getRow()][current.getCol()] != -1)
            return dp[current.getRow()][current.getCol()];

        Point next = new Point.Builder(current.getRow() - 1, current.getCol()).build();
        int answer = recursion(next, destination) + map[current.getRow()][current.getCol()];

        next = new Point.Builder(current.getRow(), current.getCol() - 1).build();
        answer = Math.max(answer
            , recursion(next, destination) + map[current.getRow()][current.getCol()]);

        return dp[current.getRow()][current.getCol()] = answer;
    }

    private static void init(int n) {
        map = new int[n + 1][n + 1];
        dp = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        for(int i = 0; i <= (n << 1); i++) {
            checkPoint.add(i, new ArrayList<>());
        }
    }
}
