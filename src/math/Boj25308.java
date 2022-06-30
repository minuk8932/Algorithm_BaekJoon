package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25308번: 방사형 그래프
 *
 * @see https://www.acmicpc.net/problem/25308
 *
 */
public class Boj25308 {

    private static int[] points = new int[8];

    private static boolean[] visit;
    private static ArrayList<Integer> permutation = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < points.length; i++) {
            points[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= points.length; i++) {
            visit = new boolean[8];
            backTracking(i, i, 1);
        }

        System.out.println(makeConvexGraph());
    }

    private static int makeConvexGraph() {
        int count = 0;

        for(int p: permutation) {
            count += rotating(p);
        }

        return count;
    }

    private static int rotating(int loop) {
        ArrayList<Double> coors = new ArrayList<>();

        while (loop > 0) {
            int current = loop % 10;
            loop /= 10;

            coors.add((double) points[current - 1]);
        }

        for(int i = 0; i < 8; i++) {
            int p1 = (i + 1) % 8;
            int p2 = (i + 2) % 8;

            if(convexValid(coors.get(i), coors.get(p1), coors.get(p2))) return 0;
        }

        return 1;
    }

    private static boolean convexValid(double a, double b, double c) {
        return a * c * Math.sqrt(2) > b * (a + c);
    }

    private static void backTracking(int current, int value, int count) {
        if(count == 8) {
            permutation.add(value);
            return;
        }

        visit[current - 1] = true;

        for(int next = 1; next <= visit.length; next++) {
            if(visit[next - 1]) continue;

            backTracking(next, value * 10 + next, count + 1);
            visit[next - 1] = false;
        }
    }
}
