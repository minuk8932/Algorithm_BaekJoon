package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3425번: 고스택
 *
 * @see https://www.acmicpc.net/problem/3425/
 *
 */
public class Boj3425 {
    private static StringBuilder sb = new StringBuilder();

    private static final String Q = "QUIT";
    private static final String NO = "NUM";
    private static final String P = "POP";
    private static final String I = "INV";
    private static final String A = "ADD";
    private static final String DU = "DUP";
    private static final String DI = "DIV";
    private static final String SW = "SWP";
    private static final String SU = "SUB";
    private static final String MU = "MUL";
    private static final String MO = "MOD";
    private static final String EN = "END";

    private static final String E = "ERROR";
    private static final String NEW_LINE = "\n";

    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String cmd = br.readLine();
            if(Q.equals(cmd)) break;

            ArrayList<String> queries = new ArrayList<>();
            queries.add(cmd);

            if(!EN.equals(cmd)) {
                String input;

                while (!(input = br.readLine()).equals(EN)) {
                    queries.add(input);
                }

                queries.add(EN);
            }

            int N = Integer.parseInt(br.readLine());
            for(int i = 0; i < N; i++) {
                execute(queries, Long.parseLong(br.readLine()));
            }

            sb.append(NEW_LINE);
            br.readLine();
        }

        System.out.println(sb.toString());
    }

    private static void execute(ArrayList<String> query, long value) {
        ArrayDeque<Long> stack = new ArrayDeque<>();
        stack.push(value);

        for(String q: query) {
            StringTokenizer st = new StringTokenizer(q);
            String cmd = st.nextToken();

            if(EN.equals(cmd)) break;

            if(NO.equals(cmd)) {
                stack.push(Long.parseLong(st.nextToken()));
                continue;
            }

            long peek;
            long sec;

            switch (cmd) {
                case P:
                    if(stack.isEmpty()) {
                        validator(INF + 1);
                        return;
                    }

                    stack.pop();
                    break;

                case I:
                    if(stack.isEmpty()) {
                        validator(INF + 1);
                        return;
                    }

                    stack.push(-stack.pop());
                    break;

                case A:
                    if(stack.size() < 2) {
                        validator(INF + 1);
                        return;
                    }

                    peek = stack.pop();
                    peek += stack.pop();

                    if(validator(peek)) return;
                    stack.push(peek);
                    break;

                case DU:
                    if(stack.isEmpty()) {
                        validator(INF + 1);
                        return;
                    }

                    peek = stack.peek();
                    stack.push(peek);
                    break;

                case DI:
                    if(stack.size() < 2) {
                        validator(INF + 1);
                        return;
                    }

                    peek = stack.pop();
                    sec = stack.pop();

                    if(peek == 0) {                                     // divide by zero
                        validator(INF + 1);
                        return;
                    }

                    long div = Math.abs(sec) / Math.abs(peek);

                    if((sec < 0 && peek >= 0) || (sec >= 0 && peek < 0)) div *= -1;
                    stack.push(div);
                    break;

                case SU:
                    if(stack.size() < 2) {
                        validator(INF + 1);
                        return;
                    }

                    peek = stack.pop();
                    sec = stack.pop();

                    sec -= peek;

                    if(validator(sec)) return;
                    stack.push(sec);
                    break;

                case SW:
                    if(stack.size() < 2) {
                        validator(INF + 1);
                        return;
                    }

                    peek = stack.pop();
                    sec = stack.pop();

                    stack.push(peek);
                    stack.push(sec);
                    break;

                case MU:
                    if(stack.size() < 2) {
                        validator(INF + 1);
                        return;
                    }

                    peek = stack.pop() * stack.pop();

                    if(validator(peek)) return;
                    stack.push(peek);
                    break;

                case MO:
                    if(stack.size() < 2) {
                        validator(INF + 1);
                        return;
                    }

                    peek = stack.pop();
                    sec = stack.pop();

                    if(peek == 0) {                                     // divide by zero
                        validator(INF + 1);
                        return;
                    }

                    long mod = Math.abs(sec) % Math.abs(peek);

                    if(sec < 0) mod *= -1;
                    stack.push(mod);
                    break;
            }
        }

        if(stack.size() != 1) {
            sb.append(E).append(NEW_LINE);
            return;
        }

        sb.append(stack.pop()).append(NEW_LINE);
    }

    private static boolean validator (long value) {
        if(value > INF || value < -INF){
            sb.append(E).append(NEW_LINE);
            return true;
        }

        return false;
    }
}
