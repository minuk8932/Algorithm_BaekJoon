package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18870번: 좌표 압축
 *
 * @see https://www.acmicpc.net/problem/18870/
 *
 */
public class Boj18870 {
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();
    private static HashMap<Integer, Integer> index = new HashMap<>();
    private static int[] src;

    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        src = new int[N];

        for(int i = 0; i < N; i++) {
            src[i] = Integer.parseInt(st.nextToken());
            pq.offer(src[i]);
        }

        System.out.println(getStream(N));
    }

    private static String getStream(int n) {
        int idx = 0;
        while(!pq.isEmpty()){
            int current = pq.poll();

            if(index.containsKey(current)) continue;            // remake coordinates
            index.put(current, idx);
            idx++;
        }

        int[] result = new int[n];
        for(int i = 0; i < src.length; i++) {
            result[i] = index.get(src[i]);
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            sb.append(result[i]).append(SPACE);
        }

        return sb.toString();
    }
}
