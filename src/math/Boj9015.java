package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 9015번: 정사각형
 *
 * @see https://www.acmicpc.net/problem/9015/
 *
 */
public class Boj9015 {
    private static final String NEW_LINE = "\n";

    private static class Pair implements Comparable<Pair>{
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair p) {
            if(this.x < p.x) {
                return -1;
            }
            else if(this.x > p.x) {
                return 1;
            }
            else {
                if(this.y < p.y) return -1;
                else if(this.y > p.y) return 1;
                else return 0;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            Pair[] coor = new Pair[N];
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                coor[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            Arrays.sort(coor);
            sb.append(make(coor)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int make (Pair[] p) {
        int max = 0;

        for(int i = 0; i < p.length; i++) {
            for(int j = i + 1; j < p.length; j++) {
                int d = getDistance(p[i], p[j]);
                if(d <= max) continue;

                int x = p[j].x - p[i].x;
                int y = p[j].y - p[i].y;

                if(!binarySearch(p, new Pair(p[i].x - y, p[i].y + x)) ||
                        !binarySearch(p, new Pair(p[j].x - y, p[j].y + x))) continue;       // candidate
                max = Math.max(getDistance(p[i], p[j]), max);
            }
        }

        return max;
    }

    private static boolean binarySearch(Pair[] pairs, Pair p) {
        int start = 0, end = pairs.length - 1;

        while(start <= end) {
            int mid = (start + end) / 2;

            if(p.x == pairs[mid].x && p.y == pairs[mid].y) return true;         // can make

            if(p.x == pairs[mid].x) {
                if(pairs[mid].y < p.y) start = mid + 1;
                else end = mid - 1;
            }
            else {
                if(pairs[mid].x < p.x) start = mid + 1;
                else end = mid - 1;
            }
        }

        return false;
    }

    private static int getDistance(Pair p1, Pair p2) {
        int x = Math.abs(p1.x - p2.x);
        int y = Math.abs(p1.y - p2.y);

        return x * x + y * y;
    }
}
