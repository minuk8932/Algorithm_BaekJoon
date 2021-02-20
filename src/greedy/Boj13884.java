package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13884번: 삭삽 정렬
 *
 * @see https://www.acmicpc.net/problem/13884
 *
 */
public class Boj13884 {

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int P = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(P-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            int[] sorted = new int[N];
            int loop = N % 10 == 0 ? N: ((N / 10) + 1) * 10;
            int i = 0;

            while(loop > 0) {
                st = new StringTokenizer(br.readLine());

                while(st.hasMoreTokens()) {
                    arr[i] = Integer.parseInt(st.nextToken());
                    sorted[i] = arr[i];
                    i++;
                }

                loop -= 10;
            }

            Arrays.sort(sorted);
            sb.append(K).append(SPACE).append(process(arr, sorted)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int process(int[] a, int[] s) {
        int count = 0;
        int idx = 0;

        for(int data: a) {
            if(data == s[idx]) idx++;
            else count++;
        }

        return count;
    }
}
