package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20551번: Sort 마스터 배지훈의 후계자
 *
 * @see https://www.acmicpc.net/problem/20551
 *
 */
public class Boj20551 {

    private static final String NEW_LINE = "\n";
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        Arrays.sort(A);

        while(M-- > 0) {
            int D = Integer.parseInt(br.readLine());
            sb.append(binarySearch(D, A)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int binarySearch(int d, int[] a) {
        int result = INF;
        int start = 0, end = a.length - 1;

       while(start <= end) {
           int mid = (start + end) / 2;

           if(a[mid] < d) {
               start = mid + 1;
           }
           else {                                               // find same and minimum idx
               if(a[mid] == d) result = Math.min(result, mid);
               end = mid - 1;
           }
       }

       return result == INF ? -1: result;
    }
}
