package sparse_table;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3117번: YouTube
 *
 * @see https://www.acmicpc.net/problem/3117
 *
 */
public class Boj3117 {
    private static int[][] parent;
    private static int[] video;

    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[K][31];

        int[] student = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            student[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        video = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            video[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        connecting(K);
        System.out.println(getResult(M - 1, student));
    }
    private static void connecting(int n) {
        for(int i = 0; i < n; i++) {
            parent[i][0] = video[i];
        }

        for(int p = 1; p < 31; p++) {                               // video sparse table
            for(int cur = 0; cur < n; cur++) {
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }

    private static String getResult(int m, int[] stud) {
        StringBuilder sb = new StringBuilder();

        for(int s: stud) {
            int loop = m;

            for (int i = 30; i >= 0; i--) {
                int jump = 1 << i;
                if (loop < jump) continue;

                loop -= jump;                   // jumping nodes
                s = parent[s][i];
            }

            sb.append(s + 1).append(SPACE);
        }

        return sb.toString();
    }
}
