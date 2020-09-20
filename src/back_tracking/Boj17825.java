package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


/**
 *
 * @author exponential-e
 * 백준 17825번: 주사위 윷놀이
 *
 * @see https://www.acmicpc.net/problem/17825
 *
 */
public class Boj17825 {
    private static int[][] board = new int[4][30];
    private static int[] dice = new int[10];
    private static ArrayList<Long> permutation = new ArrayList<>();

    private static int[] visit;
    private static boolean[][] remained;
    private static Status[] unit = new Status[4];

    private static class Status {
        int position;
        int count;

        public Status(int position, int count) {
            this.position = position;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        init();

        for(int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        visit = new int[5];
        backTracking(1, 0, 1L);     // make permutation

        System.out.println(play());
    }

    private static int play() {
        int result = 0;

        for(Long p: permutation) {
            for(int i = 0; i < unit.length; i++) {
                unit[i] = new Status(0, 0);
            }

            int[] data = new int[4];
            remained = new boolean[4][30];

            long loop = p;
            boolean invalid = false;
            int seq = -1;

            while(loop > 0) {
                int user = (int) (loop % 10) - 1;
                loop /= 10L;
                seq++;

                if(board[unit[user].position][unit[user].count] == -1) continue;
                int move = dice[seq] + unit[user].count;

                Status current = unit[user];
                Status next;

                if(current.position == 0) next = rotate(move);              // can move shortest path ?
                else next = new Status(current.position, move);

                if(board[next.position][next.count] != -1) {                // already another here
                    if(invalid = (remained[next.position][next.count] || covered(next))) break;
                    remained[current.position][current.count] = false;
                    remained[next.position][next.count] = true;

                    unit[user] = next;
                    data[user] += board[next.position][next.count];         // move
                }

                remained[current.position][current.count] = false;
                unit[user] = next;
            }

            if(invalid) continue;               // not affected to result

            int total = 0;
            for(int d: data) {
                total += d;
            }

            result = Math.max(total, result);
        }

        return result;
    }

    private static boolean covered(Status s) {
        switch (s.position) {                                   // check covered path
            case 0:
                if(s.count == 5) return remained[1][0];
                else if(s.count == 10) return remained[2][0];
                else if(s.count == 15) return remained[3][0];
                else if(s.count == 20) return remained[1][7] || remained[2][6] || remained[3][7];
                break;

            case 1:
                if(s.count == 0) return remained[0][5];

                if(s.count == 4)
                    return remained[2][3] || remained[3][4];
                else if(s.count == 5)
                    return remained[2][4] || remained[3][5];
                else if(s.count == 6)
                    return remained[2][5] || remained[3][6];
                else if(s.count == 7)
                    return remained[0][20] || remained[2][6] || remained[3][7];
                break;

            case 2:
                if(s.count == 0) return remained[0][10];

                if(s.count == 3)
                    return remained[1][4] || remained[3][4];
                else if(s.count == 4)
                    return remained[1][5] || remained[3][5];
                else if(s.count == 5)
                    return remained[1][6] || remained[3][6];
                else if(s.count == 6)
                    return remained[0][20] || remained[1][7] || remained[3][7];
                break;

            case 3:
                if(s.count == 0) return remained[0][15];

                if(s.count == 4)
                    return remained[2][3] || remained[1][4];
                else if(s.count == 5)
                    return remained[2][4] || remained[1][5];
                else if(s.count == 6)
                    return remained[2][5] || remained[1][6];
                else if(s.count == 7)
                    return remained[0][20] || remained[2][6] || remained[1][7];
                break;
        }

        return false;
    }

    private static Status rotate(int push) {
        if(push == 5) return new Status(1, 0);
        else if(push == 10) return new Status(2, 0);
        else if(push == 15) return new Status(3, 0);
        else return new Status(0, push);
    }

    private static void backTracking(int current, int count, long value) {
        if(count == 9) {
            permutation.add(value);
            return;
        }

        visit[current]++;

        for(int next = 1; next < 5; next++) {
            if(visit[next] == 10) continue;

            backTracking(next, count + 1, value * 10 + next);
            visit[next]--;
        }
    }

    private static void init() {
        for(int i = 0; i < 4; i++) {
            Arrays.fill(board[i], -1);
        }

        board[0][0] = 0;
        filling(0, 1, 21, 2);

        board[1][0] = 10;
        filling(1, 1, 4, 3);
        board[1][4] = 25;
        filling(1, 5, 8, 5);

        board[2][0] = 20;
        filling(2, 1, 3, 2);
        board[2][3] = 25;
        filling(2, 4, 7, 5);

        board[3][0] = 30;
        board[3][1] = 28;
        filling(3, 2, 5, -1);
        filling(3, 5, 8, 5);
    }

    private static void filling(int target, int start, int end, int add) {
        for(int i = start; i < end; i++) {
            board[target][i] = board[target][i - 1] + add;
        }
    }
}
