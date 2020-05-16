package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17281번: 야구 ⚾
 *
 * @see https://www.acmicpc.net/problem/17281/
 *
 */
public class boj17281 {
    private static ArrayList<Integer> permutation = new ArrayList<>();
    private static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] inning = new ArrayList[N];
        int[] one = new int[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            inning[i] = new ArrayList<>();

            for(int j = 0; j < 9; j++) {
                if(j == 0) one[i] = Integer.parseInt(st.nextToken());
                else inning[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i = 1; i <= 8; i++) {
            visit = new boolean[8];
            backTracking(i, i, 0);
        }

        System.out.println(playBall(N, inning, one));
    }

    private static int playBall(int n, ArrayList[] inning, int[] one) {
        int score = 0;

        for(int p: permutation) {
            int seq = 0;
            int total = 0;

            for(int i = 0; i < n; i++) {
                int[] records = new int[9];
                ArrayList<Integer> current = inning[i];

                int loop = p;
                int idx = 0;

                while(idx < 3) {
                    records[idx++] = current.get((loop % 10) - 1);
                    loop /= 10;
                }

                records[idx++] = one[i];

                while(loop > 0) {
                    records[idx++] = current.get((loop % 10) - 1);
                    loop /= 10;
                }

                int[] base = new int[3];
                int outCount = 0;

                while(outCount < 3) {                       // inning change
                    switch (records[seq]) {
                        case 1:
                            total += base[2];

                            for(int b = 2; b > 0; b--) {    // 1 base
                                base[b] = base[b - 1];
                            }

                            base[0] = 1;
                            break;

                        case 2:
                            total += base[1] + base[2];     // 2 base
                            base[2] = base[0];
                            base[1] = 1;
                            base[0] = 0;
                            break;

                        case 3:                             // 3 base
                            total += base[0] + base[1] + base[2];
                            base = new int[3];
                            base[2] = 1;
                            break;

                        case 4:                             // homerun
                            total += base[0] + base[1] + base[2] + 1;
                            base = new int[3];
                            break;

                        case 0:                             // out
                            outCount++;
                            break;
                    }

                    seq++;
                    seq %= 9;
                }
            }

            score = Math.max(score, total);
        }

        return score;
    }

    private static void backTracking(int current, int val, int count) {
        if(count == 7) {
            permutation.add(val);
            return;
        }

        visit[current - 1] = true;

        for(int next = 1; next <= 8; next++) {
            if(visit[next - 1]) continue;

            backTracking(next, val * 10 + next, count + 1);
            visit[next - 1] = false;
        }
    }
}
