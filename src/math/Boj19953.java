package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19953번: 영재의 산책
 *
 * @see https://www.acmicpc.net/problem/19953
 *
 */
public class Boj19953 {
    private static ArrayList<Integer> period = new ArrayList<>();
    private static boolean[] visit = new boolean[10];

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        getPeriod(v, m);
        Point result = coordinate(v, t - 1);
        System.out.println(result.x + " " + result.y);
    }

    private static Point coordinate(int v, int time) {
        int lcm = period.size() * 4 / getGcd(period.size(), 4);

        Point p = rotating(new Point(0, 0), lcm);

        int value = time / lcm;
        int mod = time % lcm;

        return rotating(new Point(p.x * value, p.y * value + v), mod);
    }

    private static int getGcd (int a, int b) {
        if(b == 0) return a;
        return getGcd(b, a % b);
    }

    private static Point rotating(Point p, int loop) {          // find position by (directions, velocity) period
        int size = period.size();
        int add = 0, index = 0;

        while(loop-- > 0) {
            if(add % 2 == 1) p.y += (add < 2 ? -1: 1) * period.get(index);
            else p.x += (add < 2 ? 1: -1) * period.get(index);

            index++;
            index %= size;
            add++;
            add %= 4;
        }

        return p;
    }

    private static void getPeriod(int v, int m) {
        int start = v;
        start *= m;
        start %= 10;

        while(!visit[start]) {                          // velocity period
            visit[start] = true;
            period.add(start);

            start *= m;
            start %= 10;
        }
    }
}
