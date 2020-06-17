package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16945번: 매직 스퀘어로 변경하기
 *
 * @see https://www.acmicpc.net/problem/16945/
 *
 */
public class Boj16945 {
    private static ArrayList<Integer> permutation = new ArrayList<>();
    private static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] square = new int[3][3];

        for(int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 3; j++) {
                square[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i < 10; i++) {
            visit = new boolean[10];
            backTracking(i, 1, i);              // find all cases
        }

        System.out.println(makeMagicSquare(square));
    }

    private static int makeMagicSquare(int[][] s) {
        int min = 100;

        for(int p: permutation) {
            int[][] target = new int[3][3];
            int[] idx = {0, 0};

            int loop = p;
            while(loop > 0) {
                target[idx[0]][idx[1]] = loop % 10;

                loop /= 10;
                idx[1]++;

                if(idx[1] == 3) {
                    idx[1] = 0;
                    idx[0]++;
                }
            }

            if(!judgement(target)) continue;                        // is magic square?

            int differ = 0;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    differ += Math.abs(s[i][j] - target[i][j]);     // then compare
                }
            }

            min = Math.min(min, differ);
        }

        return min;
    }

    private static boolean judgement(int[][] t) {
        int sum = makeSum(t, 0, 1, 1, 1, 2, 1);

        if (sum != makeSum(t, 0, 0, 1, 1, 2, 2)) return false;
        if (sum != makeSum(t, 0, 0, 0, 1, 0, 2)) return false;
        if (sum != makeSum(t, 1, 0, 1, 1, 1, 2)) return false;
        if (sum != makeSum(t, 2, 0, 2, 1, 2, 2)) return false;
        if (sum != makeSum(t, 2, 0, 1, 1, 0, 2)) return false;
        if (sum != makeSum(t, 0, 0, 1, 0, 2, 0)) return false;
        if (sum != makeSum(t, 0, 2, 1, 2, 2, 2)) return false;

        return true;
    }

    private static int makeSum (int[][] t, int x1, int y1, int x2, int y2, int x3, int y3) {
        return t[x1][y1] + t[x2][y2] + t[x3][y3];
    }

    private static void backTracking(int current, int count, int value) {
        if(count == 9) {
            permutation.add(value);
            return;
        }

        visit[current] = true;

        for(int next = 1; next < 10; next++) {
            if(visit[next]) continue;

            backTracking(next, count + 1, value * 10 + next);
            visit[next] = false;
        }
    }
}
