package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 22238번: 가희와 btd5
 *
 * @see https://www.acmicpc.net/problem/22238
 *
 */
public class Boj22238 {

    private static final String NEW_LINE = "\n";

    private static List<Integer> points;
    private static long damage;

    private static class Coordinate {
        long x;
        long y;

        public Coordinate(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Coordinate coor = new Coordinate(0, 0);
        points = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            points.add(h);
            if(i != 0) continue;                // just one linear inputs

            int gcd = Math.abs(getGcd(x, y));
            x /= gcd;
            y /= gcd;

            coor = new Coordinate(x, y);
        }

        Collections.sort(points);
        int size = points.size();
        int flag = 0;

        StringBuilder sb = new StringBuilder();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int shot = Integer.parseInt(st.nextToken());

            int gcd = Math.abs(getGcd(x, y));
            x /= gcd;
            y /= gcd;

            if(x == coor.x && y == coor.y) flag = binarySearch(shot, size - 1);
            sb.append(size - flag).append(NEW_LINE);
        }

        System.out.print(sb.toString());
    }

    private static int binarySearch(int shot, int end) {
        damage += shot;

        int result = -1;
        int start = 0;

        while(start <= end) {
            int mid = (start + end) >> 1;

            if(points.get(mid) <= damage) {
                result = mid;
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        return result + 1;
    }

    private static int getGcd(int a, int b) {
        if(b == 0) return a;
        return getGcd(b, a % b);
    }
}
