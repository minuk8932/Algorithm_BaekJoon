package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20126번: 교수님의 기말고사
 *
 * @see https://www.acmicpc.net/problem/20126
 *
 */
public class Boj20126 {

    private static class Time implements Comparable<Time>{
        int start;
        int end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time t) {
            return this.start - t.start;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        Time[] t = new Time[N + 2];
        t[0] = new Time(0, 0);
        int last;

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int durable = Integer.parseInt(st.nextToken());
            last = start + durable;

            t[i] = new Time(start, last);
        }

        t[N + 1] = new Time(S, S);
        Arrays.sort(t);

        System.out.println(test(t, M));
    }

    private static int test(Time[] t, int m) {
        int prev = t[0].end;

        for(int i = 1; i < t.length; i++) {
            if(t[i].start - prev >= m) return prev;     // find interval
            prev = t[i].end;
        }

        return -1;
    }
}
