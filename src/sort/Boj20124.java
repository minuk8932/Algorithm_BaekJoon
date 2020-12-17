package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20124번: 	모르고리즘 회장님 추천 받습니다
 *
 * @see https://www.acmicpc.net/problem/20124
 *
 */
public class Boj20124 {

    private static class Candidate implements Comparable<Candidate>{
        String name;
        int count;

        public Candidate(String name, int count) {
            this.name = name;
            this.count = count;
        }

        @Override
        public int compareTo(Candidate c) {
            return c.count != this.count ? c.count - this.count
                    : this.name.compareTo(c.name);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Candidate> candidates = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            candidates.offer(new Candidate(st.nextToken(), Integer.parseInt(st.nextToken())));
        }

        System.out.println(candidates.poll().name);
    }
}
