package fenwick_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17131번: 여우가 정보섬에 올라온 이유
 *
 * @see https://www.acmicpc.net/problem/17131/
 *
 */
public class Boj17131 {
    public static final int MOD = 1_000_000_007;
    public static final int INF = 200_001;
    private static final int SIZE = INF << 1;

    private static Star[] coordinate;
    private static long[] tree = new long[SIZE];

    private static class Star implements Comparable<Star>{
        int x;
        int y;

        public Star(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Star s) {
            if (this.y < s.y) return -1;
            else if (this.y > s.y) return 1;
            else {
                if (this.x < s.x) return -1;
                else if (this.x > s.x) return 1;
                else return 0;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        coordinate = new Star[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) + INF;
            int y = Integer.parseInt(st.nextToken()) + INF;

            coordinate[i] = new Star(x, y);
            update(coordinate[i].x, 1);             // make coordinate positive & update
        }

        Arrays.sort(coordinate);

        long result = 0;
        int target = -SIZE;

        for(int i = 0; i < N; i++) {
            if (target != coordinate[i].y) {                // pass already used
                target = coordinate[i].y;

                for (int j = i; j < N; j++){                // considering same y value
                    if(target != coordinate[j].y) break;
                    update(coordinate[j].x, -1);
                }
            }

            long prev = sum(coordinate[i].x - 1);
            long post = sum(SIZE - 1) - sum(coordinate[i].x);  // current y < another
            result += (prev % MOD) * (post % MOD);
            result %= MOD;
        }

        System.out.println(result);
    }

    private static long sum(int index) {
        long sum = 0;

        while (index > 0) {
            sum += tree[index];
            index -= (index & -index);
        }

        return sum;
    }

    private static void update(int index, int value) {
        while (index < SIZE) {
            tree[index] += value;
            index += (index & -index);
        }
    }
}
