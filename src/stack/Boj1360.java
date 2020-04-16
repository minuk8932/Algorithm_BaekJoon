package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1360번: 되돌리기
 *
 * @see https://www.acmicpc.net/problem/1360/
 *
 */
public class Boj1360 {
    public static final String UNDO = "undo";

    private static class Query {
        String cmd;
        String value;
        int timer;

        public Query(String cmd, String value, int timer) {
            this.cmd = cmd;
            this.value = value;
            this.timer = timer;
        }
    }

    public static void main(String[] args )throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayDeque<Query> stack = new ArrayDeque<>();
        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String c = st.nextToken();
            String v = st.nextToken();
            int t = Integer.parseInt(st.nextToken());

            stack.push(new Query(c, v, t));                 // make offline reversed query
        }

        System.out.println(getResult(stack));
    }

    private static String getResult(ArrayDeque<Query> stack) {
        ArrayDeque<String> result = new ArrayDeque<>();

        Query start = stack.pop();
        int undo = -1;

        if(start.cmd.equals(UNDO)) undo = Integer.parseInt(start.value);
        else result.push(start.value);

        int prev = start.timer;

        while (!stack.isEmpty()) {
            Query q = stack.pop();
            int diff = undo - (prev - q.timer);
            prev = q.timer;

            if(diff >= 0) {                                 // ignored
                undo = diff;
                continue;
            }

            if (q.cmd.equals(UNDO)) {                       // executed
                int value = Integer.parseInt(q.value);
                undo = value;
            }
            else {
                result.push(q.value);
                undo = -1;
            }
        }

        StringBuilder sb = new StringBuilder();

        while(!result.isEmpty()) {
            sb.append(result.pop());
        }

        return sb.toString();
    }
}
