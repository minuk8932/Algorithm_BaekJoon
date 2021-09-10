package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 22993번: 서든어택3
 *
 * @see https://www.acmicpc.net/problem/22993
 *
 */
public class Boj22993 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = PARSE.apply(br.readLine());

        StringTokenizer token = new StringTokenizer(br.readLine());
        long A = PARSE.apply(token.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        while(N-- > 1) {
            pq.offer(PARSE.apply(token.nextToken()));
        }

        System.out.println(suddenAttack(A, pq));
    }

    private static String suddenAttack(long target, PriorityQueue<Long> pq) {

        while(!pq.isEmpty()) {
            long current = pq.poll();

            if(current >= target) return "No";
            target += current;
        }

        return "Yes";
    }

    private static final Function<String, Long> PARSE = Long::parseLong;
}
