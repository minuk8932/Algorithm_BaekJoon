package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20956번: 아이스크림 도둑 지호
 *
 * @see https://www.acmicpc.net/problem/20956
 *
 */
public class Boj20956 {

    private static PriorityQueue<Icecream> increment;
    private static PriorityQueue<Icecream> decrement;

    private static boolean[] out;
    private static final String NEW_LINE = "\n";

    private static class Icecream{
        int amount;
        int index;

        public Icecream(int amount, int index) {
            this.amount = amount;
            this.index = index;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        out = new boolean[N + 1];

        increment = new PriorityQueue<>((i1, i2) -> {
            if(i1.amount > i2.amount) return -1;
            else if(i1.amount < i2.amount) return 1;
            return i1.index - i2.index;
        });

        decrement = new PriorityQueue<>((i1, i2) -> {
            if(i1.amount > i2.amount) return -1;
            else if(i1.amount < i2.amount) return 1;
            return i2.index - i1.index;
        });

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            int ice = Integer.parseInt(st.nextToken());
            increment.offer(new Icecream(ice, i));
            decrement.offer(new Icecream(ice, i));
        }

        System.out.println(sequence(M));
    }

    /**
     *
     * Sequence
     *
     * line 79 ~ 81: remove already checked
     *
     * @param m
     * @return
     */
    private static String sequence(int m) {
        StringBuilder sb = new StringBuilder();

        boolean flag = true;

        while(m-- > 0) {
            Icecream current = flag ? increment.poll(): decrement.poll();

            while(out[current.index]) {
                current = flag ? increment.poll(): decrement.poll();
            }

            out[current.index] = true;
            sb.append(current.index).append(NEW_LINE);

            if(current.amount % 7 == 0) flag = !flag;
        }

        return sb.toString();
    }
}
