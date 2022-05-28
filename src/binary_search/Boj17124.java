package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17124번: 두개의 배열
 *
 * @see https://www.acmicpc.net/problem/17124
 *
 */
public class Boj17124 {

    private static final String NEW_LINE = "\n";
    private static final long INF = 1_000_000_001;

    private static long[] A;
    private static long[] B;

    private static long prev, candidate;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            A = inputs(N, br.readLine(), false);
            B = inputs(M, br.readLine(), true);

            sb.append(binarySearch()).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    /**
     *
     * upperbound: check range which bigger than target
     * lowerbound: check range which smaller than target
     *
     * @return
     */
    private static long binarySearch() {
        long answer = 0;

        for(long target: A) {
            prev = INF;
            candidate = INF;

            upperbound(0, B.length - 1, target);
            lowerbound(0, B.length - 1, target);

            answer += candidate;
        }

        return answer;
    }

    private static void lowerbound(int start, int end, long target) {
        while(start <= end) {
            int mid = (start + end) >> 1;

            long current = target - B[mid];
            if(current < 0) {
                end = mid - 1;
            }
            else {
                start = mid + 1;

                if(current <= prev) {
                    candidate = B[mid];
                    prev = current;
                }
            }
        }
    }

    private static void upperbound(int start, int end, long target) {
        while(start <= end) {
            int mid = (start + end) >> 1;

            long current = B[mid] - target;
            if(current < 0) {
                start = mid + 1;
            }
            else {
                end = mid - 1;

                if(current <= prev) {
                    candidate = B[mid];
                    prev = current;
                }
            }
        }
    }

    private static long[] inputs(int len, String input, boolean sorting) {
        StringTokenizer st = new StringTokenizer(input);
        long[] arr = new long[len];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        if(sorting) Arrays.sort(arr);
        return arr;
    }
}
