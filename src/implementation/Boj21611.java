package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 21611번: 마법사 상어와 블리자드
 *
 * @see https://www.acmicpc.net/problem/21611
 *
 */
public class Boj21611 {

    private static int[] beads = new int[3];
    private static int[] link = new int[10_000];
    private static int[][] map;
    private static int[] linear;
    private static int N;
    private static int nPow;

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int[][] ROTATING = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    private static final int ROW = 0, COL = 1;
    private static final int CIPHER = 100;

    private static Point magician;

    private static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nPow = N * N;
        map = new int[N][N];
        linear = new int[nPow];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        magician = new Point(N >> 1, N >> 1);
        map[magician.row][magician.col] = -1;
        Arrays.fill(link, -1);

        blockLink();

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            blizzard(d, s);
            squareToLinear();

            while(true) {
                pulling();
                if(!explosion()) break;
            }

            exchange();
            linearToSquare();
        }

        System.out.println(beads[0] + (beads[1] << 1) + beads[2] * 3);
    }

    /**
     *
     * Linear to square
     *
     * line 95 ~ 100: data convert linear to square
     *
     */
    private static void linearToSquare() {
        Point p = magician;

        int key = p.row * CIPHER + p.col;
        int index = 0;

        while(link[key] != -1) {
            map[p.row][p.col] = linear[index++];

            key = link[key];
            p = new Point(key / CIPHER, key % CIPHER);
        }
    }

    /**
     *
     * Square to linear
     *
     * line 115 ~ 120: data convert square to linear
     *
     */
    private static void squareToLinear() {
        Point p = magician;
        int key = p.row * CIPHER + p.col;
        int index = 0;

        while(link[key] != -1) {
            linear[index++] = map[p.row][p.col];

            key = link[key];
            p = new Point(key / CIPHER, key % CIPHER);
        }
    }

    private static void exchange() {
        Queue<Integer> q = new ArrayDeque<>();
        int count = 1;
        int value = linear[1];

        for(int i = 2; i < linear.length; i++) {
            if(value == 0) break;

            if(value == linear[i]) {
                count++;
            }
            else {
                q.offer(count);
                q.offer(value);

                value = linear[i];
                count = 1;
            }
        }

        if(count != 1) {
            q.offer(count);
            q.offer(value);
        }

        int index = 1;
        while(index < linear.length && !q.isEmpty()) {
            linear[index++] = q.poll();
        }
    }

    /**
     *
     * Explosion
     *
     * index * CIPHER + count => save place, amount, value.
     *
     * @return
     */
    private static boolean explosion() {
        List<Integer>[] candidate = new LinkedList[3];
        for(int i = 0; i < candidate.length; i++) {
            candidate[i] = new LinkedList<>();
        }

        int count = 1;
        int index = 1;
        int value = linear[1];

        for(int i = 2; i < linear.length; i++) {
            if(value == 0) continue;

            if(value == linear[i]) {
                count++;
            }
            else {
                if(count >= 4) candidate[value - 1].add(index * CIPHER + count);

                count = 1;
                index = i;
                value = linear[i];
            }
        }

        if (count >= 4) candidate[value - 1].add(index * CIPHER + count);

        boolean boomed = false;

        for(int i = 0; i < candidate.length; i++) {
            while(!candidate[i].isEmpty()) {
                int key = candidate[i].remove(0);
                int start = key / CIPHER;
                int size = key % CIPHER;

                beads[i] += size;
                for(int j = start; j < start + size; j++) {
                    linear[j] = 0;
                }

                boomed = true;
            }
        }

        return boomed;
    }

    private static void pulling() {
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < linear.length; i++) {
            if(linear[i] == 0) continue;
            q.offer(linear[i]);
        }

        linear = new int[nPow];

        int index = 0;
        while(!q.isEmpty()) {
            linear[index++] = q.poll();
        }
    }

    /**
     *
     * BlockLink
     *
     * line 242 ~ 251: make LinkedList by array each blocks.
     *
     */
    private static void blockLink() {
        int row = magician.row;
        int col = magician.col;

        int jumping = 1;
        int index = 0;

        while(true) {
            int loop = 2;

            while(loop-- > 0) {
                for(int i = 0; i < jumping; i++) {
                    int prev = row * CIPHER + col;

                    row += ROTATING[index][ROW];
                    col += ROTATING[index][COL];

                    if (outOfRange(row, col)) return;
                    int current = row * CIPHER + col;
                    link[prev] = current;
                }

                index++;
                index %= 4;
            }

            jumping++;
        }
    }

    private static void blizzard(int dir, int separate) {
        int row = magician.row;
        int col = magician.col;

        while(!outOfRange(row, col) && separate-- > 0) {
            row = row + DIRECTIONS[dir][ROW];
            col = col + DIRECTIONS[dir][COL];

            map[row][col] = 0;
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= N;
    }
}
