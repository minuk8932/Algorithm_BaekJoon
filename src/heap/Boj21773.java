package heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 *
 * @author exponential-e
 * 백준 21773번: 가희와 프로세스1
 *
 * @see https://www.acmicpc.net/problem/21773
 *
 */
public class Boj21773 {

    private static final String NEW_LINE = "\n";

    private static class Process {
        int id;
        int time;
        int prior;

        public Process(int id, int time, int prior) {
            this.id = id;
            this.time = time;
            this.prior = prior;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        PriorityQueue<Process> processes = new PriorityQueue<>((p1, p2) -> {
            if(p1.prior == p2.prior) return p1.id - p2.id;
            return p2.prior - p1.prior;
        });

        while(n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            processes.offer(new Process(A, B, C));
        }

        System.out.println(scheduling(T, processes));
    }

    private static String scheduling(int t, PriorityQueue<Process> pq) {
        StringBuilder sb = new StringBuilder();

        UnaryOperator<Integer> operator = value -> value - 1;
        Predicate<Integer> isOver = time -> time == 0;

        while(!isOver.test(t--)) {
            Process current = pq.poll();
            current.prior = operator.apply(current.prior);
            current.time = operator.apply(current.time);

            sb.append(current.id).append(NEW_LINE);

            if(isOver.test(current.time)) continue;
            pq.offer(current);
        }

        return sb.toString();
    }
}
