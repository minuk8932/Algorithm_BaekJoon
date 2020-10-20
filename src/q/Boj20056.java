package q;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20056번: 마법사 상어와 파이어볼
 *
 * @see https://www.acmicpc.net/problem/20056
 *
 */
public class Boj20056 {
    private static final int[][] DIRECTIONS = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    private static final int ROW = 0, COL = 1;

    private static int N, size;
    private static Queue<Fireball> q = new LinkedList<>();

    private static class Fireball {
        int row;
        int col;
        int mass;
        int speed;
        int dir;

        public Fireball(int row, int col, int mass, int speed, int dir) {
            this.row = row;
            this.col = col;
            this.mass = mass;
            this.speed = speed;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        size = M;

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            q.offer(new Fireball(r, c, m, s, d));
        }

        System.out.println(remained(K));
    }

    private static int remained(int k) {
        while(k-- > 0) {
            level1();
            level2();
        }

        int total = 0;
        while(!q.isEmpty()) {
            total += q.poll().mass;
        }

        return total;
    }

    private static void level1() {
        int loop = size;

        while(loop-- > 0) {                 // fire ball move
            Fireball current = q.poll();
            int nextRow = (current.row + DIRECTIONS[current.dir][ROW] * current.speed) % N;
            int nextCol = (current.col + DIRECTIONS[current.dir][COL] * current.speed) % N;

            if(nextRow < 0) nextRow += N;
            if(nextCol < 0) nextCol += N;

            q.offer(new Fireball(nextRow, nextCol, current.mass, current.speed, current.dir));
        }
    }

    private static void level2() {
        ArrayList<Fireball>[] group = new ArrayList[N * N];
        for(int i = 0; i < group.length; i++) {
            group[i] = new ArrayList<>();
        }

        int loop = size;

        while(loop-- > 0) {
            Fireball current = q.poll();
            group[current.row * N + current.col].add(current);      // ball grouping
        }

        size = 0;

        for(int i = 0; i < group.length; i++) {
            int s = group[i].size();

            if (s <= 1) {
                if (s != 0) {                                       // alone
                    size++;
                    q.offer(group[i].get(0));
                }

                continue;
            }

            Fireball sum = new Fireball(0, 0, 0, 0, 0);

            for (Fireball element : group[i]) {
                sum.row = element.row;
                sum.col = element.col;
                sum.mass += element.mass;
                sum.speed += element.speed;
                sum.dir |= (element.dir % 2 == 0 ? 2: 1);
            }

            if (sum.mass / 5 == 0) continue;
            int d = sum.dir == 3 ? -1 : -2;
            size += 4;

            for (int x = 0; x < 4; x++) {                           // remake
                q.offer(new Fireball(sum.row, sum.col, sum.mass / 5, sum.speed / s, d += 2));
            }
        }
    }
}
