package heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 19640번: 화장실의 규칙
 *
 * @see https://www.acmicpc.net/problem/19640
 *
 */
public class Boj19640 {
    private static ArrayList<Empolyee>[] emps;

    private static class Empolyee implements Comparable<Empolyee>{
        int date;
        int hurry;
        int index;
        int rank;
        boolean isdeck;

        public Empolyee(int date, int hurry, int index, int rank, boolean isdeck) {
            this.date = date;
            this.hurry = hurry;
            this.index = index;
            this.rank = rank;
            this.isdeck = isdeck;
        }

        @Override
        public int compareTo(Empolyee e) {
            if(this.date > e.date) return -1;
            else if(this.date < e.date) return 1;
            else {
                if(this.hurry > e.hurry) return -1;
                else if(this.hurry < e.hurry) return 1;
                else {
                    if(this.index < e.index) return -1;
                    else if(this.index > e.index) return 1;
                    else return 0;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int len = N / M + 1;                // rank count
        emps = new ArrayList[len];
        for(int i = 0; i < len; i++) {
            emps[i] = new ArrayList<>();
        }

        int rank = 0;
        int line = 0;

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            emps[rank].add(new Empolyee(d, h, line, rank, K == 0)); // save line, rank
            line++;
            line %= M;

            if (line == 0) rank++;
            K--;
        }

        System.out.println(deckaTurn());
    }

    private static int deckaTurn() {
        PriorityQueue<Empolyee> pq = new PriorityQueue<>();
        int count = 0;

        for(Empolyee current: emps[0]) {                        // The most Prior ranker
            pq.offer(current);
        }

        while(!pq.isEmpty()) {
            Empolyee current = pq.poll();

            if(current.isdeck) return count;
            count++;

            int next = current.rank + 1;
            if(next == emps.length) continue;
            if(emps[next].size() <= current.index) continue;

            pq.offer(emps[next].get(current.index));            // next prior ranker of current line's
        }

        return count;
    }
}
