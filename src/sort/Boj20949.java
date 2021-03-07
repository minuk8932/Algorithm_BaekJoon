package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20949번: 효정과 새 모니터
 *
 * @see https://www.acmicpc.net/problem/20949
 *
 */
public class Boj20949 {

    private static final String NEW_LINE = "\n";

    private static class Monitor implements Comparable<Monitor>{
        int size;
        int index;

        public Monitor(int size, int index) {
            this.size = size;
            this.index = index;
        }

        @Override
        public int compareTo(Monitor m) {
            if(this.size > m.size) return -1;
            else if(this.size < m.size) return 1;
            else return this.index - m.index;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Monitor> pq = new PriorityQueue<>();
        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            pq.offer(new Monitor((H * H + W * W), i));
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            sb.append(pq.poll().index).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }
}
