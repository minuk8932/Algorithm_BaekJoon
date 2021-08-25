package line_sweeping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 22942번: 데이터 체커
 *
 * @see https://www.acmicpc.net/problem/22942
 *
 */
public class Boj22942 {

    private static final String MEET = "NO";
    private static final String SEPARATE = "YES";

    private static class Circle {
        int from;
        int to;

        public Circle(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = PARSE.apply(br.readLine());

        Circle[] circles = ARRAY.apply(N);
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = PARSE.apply(st.nextToken());
            int r = PARSE.apply(st.nextToken());
            circles[i] = new Circle(x - r, x + r);
        }

        System.out.println(sweeping(circles));
    }

    /**
     *
     * Sweeping
     *
     * line 69 ~ 75: Surrounded circle save in stack & check in bound
     *
     * @param circles
     * @return
     */
    private static String sweeping(Circle[] circles) {
        Arrays.sort(circles, (c1, c2) -> {
            if(c1.from == c2.from) return c1.to - c2.to;
            return c1.from - c2.from;
        });

        Deque<Circle> stack = new ArrayDeque<>();
        Circle prev = circles[0];

        for(int i = 1; i < circles.length; i++) {
            Circle c = circles[i];

            while(!stack.isEmpty()) {
                Circle peek = stack.peek();
                if(peek.from < c.from && peek.to > c.to) break;

                if(peek.to < c.from) stack.pop();
                else return MEET;
            }

            if(prev.from < c.from && prev.to > c.to){
                stack.push(prev);
            }
            else {
                if(prev.to < c.from) continue;
                return MEET;
            }

            prev = c;
        }

        return SEPARATE;
    }

    private static Function<String, Integer> PARSE = Integer::parseInt;
    private static Function<Integer, Circle[]> ARRAY = Circle[]::new;
}
