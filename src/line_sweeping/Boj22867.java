package line_sweeping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 22867번: 종점
 *
 * @see https://www.acmicpc.net/problem/22867
 *
 */
public class Boj22867 {

    private static final Function<String, Integer> PARSER = Integer::parseInt;
    private static PriorityQueue<Timer> times;

    private static class Timer {
        String time;
        int inOut;

        public Timer(String time, int inOut) {
            this.time = time;
            this.inOut = inOut;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = PARSER.apply(br.readLine());

        times = new PriorityQueue<>((x, y) -> {
            if(x.time.equals(y.time)) return y.inOut - x.inOut;
            return x.time.compareTo(y.time);
        });

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times.offer(new Timer(st.nextToken(), 1));
            times.offer(new Timer(st.nextToken(), -1));
        }

        System.out.println(inAndOuts());
    }

    /**
     *
     * ins & outs
     *
     * line 63: counting by in or out sorted times.
     *
     * @return
     */
    private static int inAndOuts() {
        int max = 0;
        int count = 0;

        while(!times.isEmpty()) {
            count += times.poll().inOut;
            max = Math.max(count, max);
        }

        return max;
    }
}
