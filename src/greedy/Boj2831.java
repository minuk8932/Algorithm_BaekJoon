package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2831번: 댄스 파티
 *
 * @see https://www.acmicpc.net/problem/2831
 *
 */
public class Boj2831 {
    private static PriorityQueue<Integer> pm = new PriorityQueue<>();
    private static PriorityQueue<Integer> nm = new PriorityQueue<>();
    private static PriorityQueue<Integer> pf = new PriorityQueue<>();
    private static PriorityQueue<Integer> nf = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        input(br.readLine(), pm, nm);
        input(br.readLine(), pf, nf);

        System.out.println(pairing(pm, nf) + pairing(pf, nm));
    }

    private static int pairing(PriorityQueue<Integer> positive, PriorityQueue<Integer> negative) {
        int count = 0;

        while(!positive.isEmpty() && !negative.isEmpty()) {
            int p = positive.peek();
            int n = negative.poll();

            if (p < n) {                // find matched pair & remove
                positive.poll();
                count++;
            }
        }

        return count;
    }

    private static void input(String in, PriorityQueue<Integer> positive, PriorityQueue<Integer> negative) {
        StringTokenizer st = new StringTokenizer(in);

        while(st.hasMoreTokens()) {
            int data = Integer.parseInt(st.nextToken());

            if(data > 0) positive.offer(data);
            else negative.offer(-data);
        }
    }
}
