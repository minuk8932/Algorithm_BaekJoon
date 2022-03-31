package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 24891번: 단어 마방진
 *
 * @see https://www.acmicpc.net/problem/24891
 *
 */
public class Boj24891 {

    private static int[][] combinations;
    private static int[][] permutation;
    private static int[] values;
    private static boolean[] visit;

    private static String[] words;

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private static final String WRONG = "NONE\n";

    private static List<String> answer = new ArrayList<>();

    private static int N;
    private static int R;
    private static int count;
    private static int SIZE = 15_504;
    private static int P_SIZE = 120;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        words = new String[N];
        for(int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        init();

        magicSquareOperation();
        System.out.print(answer.size() == 0 ? WRONG: printer());
    }

    private static String printer() {
        Collections.sort(answer);

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(answer.get(0));

        while(st.hasMoreTokens()) {
            sb.append(st.nextToken()).append(NEW_LINE);
        }

        return sb.toString();
    }

    private static void magicSquareOperation() {
        for(int n = 0; n < combinations.length; n++) {
            if(combinations[n][0] == -1) break;

            for (int i = 0; i < permutation.length; i++) {
                if(permutation[i][0] == -1) break;
                List<Integer> sequence = new LinkedList<>();

                for (int j = 0; j < R; j++) {
                    sequence.add(combinations[n][permutation[i][j]]);
                }

                String[] map = new String[R];
                int x = 0;

                while (!sequence.isEmpty()) {
                    int seq = sequence.remove(0);
                    map[x++] = words[seq];
                }

                StringBuilder sb = new StringBuilder();
                boolean flag = true;
                for(int row = 0; row < R; row++) {
                    sb.append(map[row]);

                    for(int col = 0; col < R; col++) {
                        if(map[row].charAt(col) == map[col].charAt(row)) continue;
                        flag = false;
                        break;
                    }

                    sb.append(SPACE);
                }

                if(!flag) continue;
                answer.add(sb.toString());
            }
        }
    }

    private static void init() {
        combinations = new int[SIZE][R + 1];
        values = new int[R + 1];
        Arrays.fill(values, -1);

        for(int i = 0; i < SIZE; i++){
            Arrays.fill(combinations[i], -1);
        }

        visit = new boolean[N];
        for(int i = 0; i <= N - R; i++) {
            combination(i, 0);
        }

        permutation = new int[P_SIZE][R + 1];
        values = new int[R + 1];
        Arrays.fill(values, -1);

        for(int i = 0; i < P_SIZE; i++){
            Arrays.fill(permutation[i], -1);
        }

        count = 0;
        for(int i = 0; i < R; i++) {
            visit = new boolean[N];
            recursion(i, 0);
        }
    }

    private static void combination(int n, int r) {
        values[r] = n;
        visit[n] = true;

        if(r == R - 1) {
            for(int i = 0; i < values.length; i++){
                combinations[count][i] = values[i];
            }

            count++;
            return;
        }

        for(int next = n + 1; next < N; next++) {
            if(visit[next]) continue;

            combination(next, r + 1);
            values[r + 1] = -1;
            visit[next] = false;
        }
    }

    private static void recursion(int current, int index) {
        values[index] = current;
        visit[current] = true;

        if(index == R - 1){
            for(int i = 0; i < values.length; i++){
                permutation[count][i] = values[i];
            }

            count++;
            return;
        }

        for(int next = 0; next < R; next++) {
            if(visit[next]) continue;

            recursion(next, index + 1);
            values[index + 1] = -1;
            visit[next] = false;
        }
    }
}
