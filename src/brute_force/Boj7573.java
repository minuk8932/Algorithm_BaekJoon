package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 7573번: 고기 잡이
 *
 * @see https://www.acmicpc.net/problem/7573/
 *
 */
public class Boj7573 {
    private static Coordinate[] fish;

    private static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int I = Integer.parseInt(st.nextToken()) / 2;
        int M = Integer.parseInt(st.nextToken());

        fish = new Coordinate[M];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            fish[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(selectOne(M, I));
    }

    private static int selectOne(int M, int I) {
        int result = 0;

        for(int i = 0; i < M; i++) {
            int total = fishing(M, I, i);
            if(total > result) result = total;
        }

        return result;
    }

    private static int fishing(int m, int size, int index) {
        int max = 0;

        for (int i = 1; i < size; i++) {
            Coordinate net = new Coordinate(i, size - i);

            for (int x = fish[index].x - net.x; x <= fish[index].x; x++) {          // select one
                for (int y = fish[index].y - net.y; y <= fish[index].y; y++) {
                    int count = 0;
                    int xrange = x + net.x;         // make net range
                    int yrange = y + net.y;

                    for(int j = 0; j < m; j++) {
                        if(outOfRange(fish[j], new Coordinate(x, y), new Coordinate(xrange, yrange))) continue;
                        count++;                    // counting
                    }

                    max = Math.max(max, count);
                }
            }
        }

        return max;
    }

    private static boolean outOfRange(Coordinate fish, Coordinate start, Coordinate end) {
        return fish.x < start.x || fish.x > end.x || fish.y < start.y || fish.y > end.y;
    }
}
