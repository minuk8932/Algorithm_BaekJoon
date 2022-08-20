package divide_and_conquer;

import common.Pair;
import common.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 24460번: 특별상이라도 받고 싶어
 *
 * @see https://www.acmicpc.net/problem/24460
 *
 */
public class Boj24460 {

    private static int[][] festival;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        festival = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= N; j++) {
                festival[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int size = N * N;
        System.out.println(divideAndConquer(
            new Point.Builder<Integer, Integer>(1, 1).build(),
            new Point.Builder<Integer, Integer>(N, N).build(),
            size));
    }

    private static int divideAndConquer(
        Point<Integer, Integer> start,
        Point<Integer, Integer> end,
        int range) {

        if(range == 1) return festival[end.getRow()][end.getCol()];

        int divide = range >> 2;
        Point<Integer, Integer> endHalf = new Point.Builder<Integer, Integer>(
            (end.getRow() >> 1) + (start.getRow() >> 1),
            (end.getCol() >> 1) + (start.getCol() >> 1))
            .build();

        Point<Integer, Integer> endHalf1 = new Point.Builder<Integer, Integer>(
            endHalf.getRow() + 1,
            endHalf.getCol() + 1)
            .build();

        ArrayList<Pair<Point<Integer, Integer>>> quadrants = new ArrayList<>();
        quadrants.add(new Pair.Builder<>(
            new Point.Builder<Integer, Integer>(start.getRow(), endHalf1.getCol()).build(),
            new Point.Builder<Integer, Integer>(endHalf.getRow(), end.getCol()).build())
            .build());
        quadrants.add(new Pair.Builder<>(start, endHalf).build());
        quadrants.add(new Pair.Builder<>(
            new Point.Builder<Integer, Integer>(endHalf1.getRow(), start.getCol()).build(),
            new Point.Builder<Integer, Integer>(end.getRow(), endHalf.getCol()).build())
            .build());
        quadrants.add(new Pair.Builder<>(
            new Point.Builder<Integer, Integer>(endHalf1.getRow(), endHalf1.getCol()).build(),
            end)
            .build());

        ArrayList<Integer> candidate = new ArrayList<>();
        for(Pair<Point<Integer, Integer>> quadrant: quadrants) {
            candidate.add(divideAndConquer(quadrant.getFirst(), quadrant.getSecond(), divide));
        }

        Collections.sort(candidate);
        return candidate.get(1);
    }
}
