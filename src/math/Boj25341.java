package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25341번: 인공 신경망
 *
 * @see https://www.acmicpc.net/problem/25341
 *
 */
public class Boj25341 {

    private static final String NEW_LINE = "\n";
    private static long[] convolution;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        convolution = new long[N + 1];
        long[][] w = new long[M][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());

            int[] p = new int[c + 1];
            for (int j = 0; j < c; j++) {
                p[j] = Integer.parseInt(st.nextToken()) - 1;
            }

            p[c] = N;

            for (int j = 0; j < c + 1; j++) {
                w[i][p[j]] = Integer.parseInt(st.nextToken());
            }
        }

        makeConvolution(br.readLine(), w);

        long[] A = new long[N + 1];
        A[N] = 1;

        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < N; i++){
                A[i] = Long.parseLong(st.nextToken());
            }

            sb.append(neuralNetwrok(A)).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static void makeConvolution(String input, long[][] w) {
        StringTokenizer st = new StringTokenizer(input);
        int M = w.length;
        int N = w[0].length;

        for (int i = 0; i < M; i++) {
            int wm = Integer.parseInt(st.nextToken());

            for (int j = 0; j < N; j++) {
                convolution[j] += wm * w[i][j];
            }
        }

        convolution[N - 1] += Integer.parseInt(st.nextToken());
    }

    private static long neuralNetwrok(long[] A) {
        long answer = 0;

        for(int i = 0; i < A.length; i++){
            answer += convolution[i] * A[i];
        }

        return answer;
    }
}