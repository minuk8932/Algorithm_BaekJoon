package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 22254번: 공정 컨설턴트 호석
 *
 * @see https://www.acmicpc.net/problem/22254
 *
 */
public class Boj22254 {

    private static int X;
    private static int[] spend;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        spend = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            spend[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(binarySearch(N));
    }

    private static int binarySearch(int end) {
        int start = 1;
        int result = 0;

        while(start <= end) {
            int mid = (start + end) >> 1;

            if(processing(mid)) {
                result = mid;
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        return result;
    }

    /**
     *
     * Processing
     *
     * line 67 ~ 68: separate can, cannot
     *
     * @param time
     * @return
     */
    private static boolean processing(int time) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int size = 0;

        for(int i = 0; i < spend.length; i++) {
            if(size < time) pq.offer(spend[i]);
            else pq.offer(pq.poll() + spend[i]);

            size++;
        }

        int target = 0;
        while(!pq.isEmpty()) target = pq.poll();

        return target <= X;
    }
}
