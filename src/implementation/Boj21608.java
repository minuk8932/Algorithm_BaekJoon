package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 21608번: 상어 초등학교
 *
 * @see https://www.acmicpc.net/problem/21608
 *
 */
public class Boj21608 {
    private static List<Seat> inputs = new ArrayList<>();
    private static int[][] seats;
    private static int[][] favor;

    private static int N;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int CIPHER = 1_000;
    private static final int[] SCORE = {0, 1, 10, 100, 1_000};

    private static class Seat {
        int src;
        int[] snk;

        public Seat(int src, int[] snk) {
            this.src = src;
            this.snk = snk;
        }
    }

    private static class Pair implements Comparable<Pair>{
        int index;
        int count;

        public Pair(int index, int count) {
            this.index = index;
            this.count = count;
        }

        @Override
        public int compareTo(Pair p) {
            if(this.count == p.count) return this.index - p.index;
            return p.count - this.count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int loop = N * N;
        favor = new int[loop][loop];

        while(loop-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken()) - 1;

            int[] fav = new int[4];
            for(int i = 0; i < 4; i++) {
                fav[i] = Integer.parseInt(st.nextToken()) - 1;
                favor[student][fav[i]] = 1;
            }

            inputs.add(new Seat(student, fav));
        }

        System.out.println(positioning());
    }

    private static int positioning() {
        seats = new int[N][N];

        for(int i = 0; i < N; i++) {
            Arrays.fill(seats[i], -1);
        }

        for(Seat in: inputs) {
            int index = getUnique(findFavorite(in.snk));

            int row = index / CIPHER;
            int col = index % CIPHER;
            seats[row][col] = in.src;
        }

        return satisfied();
    }

    private static int satisfied() {
        int result = 0;

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                int target = seats[row][col];
                int count = 0;

                for (final int[] DIRECTION: DIRECTIONS) {
                    int nextRow = row + DIRECTION[ROW];
                    int nextCol = col + DIRECTION[COL];

                    if (outOfRange(nextRow, nextCol)) continue;
                    count += favor[target][seats[nextRow][nextCol]];
                }

                result += SCORE[count];
            }
        }

        return result;
    }

    /**
     *
     * Find unique coordinate
     *
     * @param arr
     * @return
     */
    private static int getUnique(int[][] arr) {
        List<Pair> candidate = new ArrayList<>();
        int max = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                max = Math.max(max, arr[i][j]);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++ ){
                if(max != arr[i][j] || seats[i][j] != -1) continue;
                candidate.add(new Pair(i * CIPHER + j, emptySize(i, j)));
            }
        }

        Collections.sort(candidate);
        return candidate.get(0).index;
    }

    private static int emptySize(int row, int col) {
        int size = 0;

        for(final int[] DIRECTION: DIRECTIONS) {
            int nextRow = row + DIRECTION[ROW];
            int nextCol = col + DIRECTION[COL];

            if(outOfRange(nextRow, nextCol) || seats[nextRow][nextCol] != -1) continue;
            size++;
        }

        return size;
    }

    /**
     *
     * Find friends
     *
     * @param fav
     * @return
     */
    private static int[][] findFavorite(int[] fav) {
        int[][] friends = new int[N][N];

        for(int f: fav) {
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (seats[row][col] != f) continue;

                    for (final int[] DIRECTION : DIRECTIONS) {
                        int nextRow = row + DIRECTION[ROW];
                        int nextCol = col + DIRECTION[COL];

                        if (outOfRange(nextRow, nextCol) || seats[nextRow][nextCol] != -1) continue;
                        friends[nextRow][nextCol]++;
                    }
                }
            }
        }

        return friends;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}