package disjoint_set;

import common.Coordinate;
import common.Pair;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 9001번: Rectangle coloring
 *
 * @see https://www.acmicpc.net/problem/9001
 *
 */
public class Boj9001 {

    private static final String NEW_LINE = "\n";
    private static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            init(N);

            Pair<Coordinate<Integer, Integer>>[] rectangles = new Pair[N];
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());

                rectangles[i] = new Pair.Builder(
                    new Coordinate.Builder(x1, y1).build(), new Coordinate.Builder(x2, y2).build()
                ).build();
            }

            sb.append(compression(rectangles)).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static int compression(Pair<Coordinate<Integer, Integer>>[] rectangles) {
        for(int rec1 = 0; rec1 < rectangles.length; rec1++){
            for(int rec2 = rec1 + 1; rec2 < rectangles.length; rec2++) {
                if(!isContact(rectangles[rec1], rectangles[rec2])) continue;
                merge(rec1, rec2);
            }
        }

        int count = 0;
        for(int p: parent) {
            if(p >= 0) continue;
            count++;
        }

        return count;
    }

    private static boolean isContact(Pair<Coordinate<Integer, Integer>> rec1
        , Pair<Coordinate<Integer, Integer>> rec2) {

        return (rec1.getFirst().getX() <= rec2.getSecond().getX() &&
            rec1.getFirst().getY() <= rec2.getSecond().getY() &&
            rec1.getSecond().getX() >= rec2.getFirst().getX() &&
            rec1.getSecond().getY() >= rec2.getFirst().getY()) ||
            (rec1.getSecond().getX() >= rec2.getFirst().getX() &&
            rec1.getFirst().getY() <= rec2.getSecond().getY() &&
            rec1.getFirst().getX() <= rec2.getSecond().getX() &&
            rec1.getSecond().getY() >= rec2.getFirst().getY());
    }

    private static void init(int n) {
        parent = new int[n];
        Arrays.fill(parent, -1);
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }
}
