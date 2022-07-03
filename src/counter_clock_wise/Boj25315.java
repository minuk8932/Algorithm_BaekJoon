package counter_clock_wise;

import common.Coordinate;
import common.Pair;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25315번: N수매화검법
 *
 * @see https://www.acmicpc.net/problem/25315
 *
 */
public class Boj25315 {

    private static long answer;
    private static ArrayList<Pair<Coordinate<Long, Long>>> points = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x1 = Long.parseLong(st.nextToken());
            long y1 = Long.parseLong(st.nextToken());
            long x2 = Long.parseLong(st.nextToken());
            long y2 = Long.parseLong(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            points.add(new Pair.Builder(
                new Coordinate.Builder(x1, y1).r(w).build(),
                new Coordinate.Builder(x2, y2).build()
            ).build());
        }

        intersectingJudgement(N);
        System.out.println(answer);
    }

    private static void intersectingJudgement(int n) {
        Collections.sort(points, (p1, p2) -> {
            if(p1.getFirst().getR() < p2.getFirst().getR()) return -1;
            else if(p1.getFirst().getR() > p2.getFirst().getR()) return 1;
            else return 0;
        });

        for(int p1 = 0; p1 < n; p1++) {
            int count = 1;

            for(int p2 = p1 + 1; p2 < n; p2++) {
                Coordinate<Long, Long> A = points.get(p1).getFirst();
                Coordinate<Long, Long> B = points.get(p1).getSecond();
                Coordinate<Long, Long> C = points.get(p2).getFirst();
                Coordinate<Long, Long> D = points.get(p2).getSecond();

                if(CCW(A, B, C) * CCW(A, B, D) > 0 || CCW(C, D, A) * CCW(C, D, B) > 0) continue;
                count++;
            }

            answer += points.get(p1).getFirst().getR() * count;
        }
    }

    private static long CCW(Coordinate<Long, Long> a, Coordinate<Long, Long> b,
        Coordinate<Long, Long> c) {

        long op = a.getX() * b.getY() + b.getX() * c.getY() + c.getX() * a.getY();
        op -= (a.getY() * b.getX() + b.getY() * c.getX() + c.getY() * a.getX());

        if (op > 0) return 1;
        else if (op == 0) return 0;
        else return -1;
    }
}
