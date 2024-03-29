package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 23056번: 참가자 명단
 *
 * @see https://www.acmicpc.net/problem/23056
 *
 */
public class Boj23056 {

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";
    private static final String TERMINATOR = "0";

    private static int[] limit;

    private static class Student {
        private int classify;
        private String name;

        public Student(int classify, String name) {
            this.classify = classify;
            this.name = name;
        }

        public int getMod() {
            return -this.classify % 2;
        }

        public int getLength(){
            return this.name.length();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = PARSE_INT.apply(st.nextToken());
        int M = PARSE_INT.apply(st.nextToken());

        PriorityQueue<Student> pq = new PriorityQueue<>(Comparator.comparingInt(Student::getMod)
                .thenComparingInt(stu -> stu.classify)
                .thenComparingInt(Student::getLength)
                .thenComparing(stu -> stu.name));

        limit = new int[N + 1];

        while(true) {
            st = new StringTokenizer(br.readLine());
            int c = PARSE_INT.apply(st.nextToken());
            String n = st.nextToken();

            if(IS_OVER.test(c, n)) break;
            if(limit[c] >= M) continue;
            limit[c]++;

            pq.offer(new Student(c, n));
        }

        System.out.println(printing(pq));
    }

    private static String printing(PriorityQueue<Student> pq) {
        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()) {
            Student current = pq.poll();
            sb.append(current.classify).append(SPACE).append(current.name).append(NEW_LINE);
        }

        return sb.toString();
    }

    private static final Function<String, Integer> PARSE_INT = Integer::parseInt;
    private static final BiPredicate<Integer, String> IS_OVER = (x, y) -> x == 0 && y.equals(TERMINATOR);

}
