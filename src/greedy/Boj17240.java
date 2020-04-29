package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17240번: Team Selection
 *
 * @see https://www.acmicpc.net/problem/17240/
 *
 */
public class Boj17240 {
    private static boolean[] visit;
    private static ArrayList<Long> permutation = new ArrayList<>();

    private static class Info implements Comparable<Info>{
        int value;
        int row;
        int col;

        public Info (int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Info i) {
            if (this.value < i.value) return -1;
            else if (this.value > i.value) return 1;
            else return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Info[][] cand = new Info[5][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 5; j++) {
                cand[j][i] = new Info(-Integer.parseInt(st.nextToken()), i, j);
            }
        }

        for(int i = 0; i < 25; i++) {
            if(i < 5) Arrays.sort(cand[i]);
            visit = new boolean[25];
            backTracking(i, i, 0);                  // make cases
        }

        System.out.println(teamSelection(cand));
    }

    private static void backTracking(int current, long val, int count) {
        visit[current] = true;

        if(count == 4) {
            permutation.add(val);
            return;
        }

        for (int next = current; next < 25; next++){
            if(visit[next]) continue;

            backTracking(next, val * 100 + next, count + 1);
            visit[next] = false;
        }
    }

    private static int teamSelection(Info[][] ranker) {
        int result = 0;

        for(long p: permutation) {
            HashSet<Integer> user =  new HashSet<>();
            boolean[] role = new boolean[5];
            boolean flag = false;
            int total = 0;

            int loop = 5;

            while(loop-- > 0) {                      // check user & role allocated
                int index = (int) (p % 100);
                int row = index / 5;
                int col = index % 5;

                if(flag = (user.contains(ranker[row][col].row) || role[ranker[row][col].col])) break;
                user.add(ranker[row][col].row);
                role[ranker[row][col].col] = true;

                total -= ranker[row][col].value;

                p /= 100;
            }

            if (flag) continue;
            if (result < total) result = total;
        }

        return result;
    }
}
