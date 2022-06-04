package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25138번: 순열
 *
 * @see https://www.acmicpc.net/problem/25138
 *
 */
public class Boj25138 {

    private static final String SPACE = " ";
    private static final String EQUAL = " = ";
    private static final String NEW_LINE = "\n";
    private static final String NO = "No permutation\n";

    private static int size;

    private static boolean[] visit;
    private static char[] permutation;
    private static char[] words;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);
            String w = st.nextToken();
            words = w.toCharArray();
            size = Integer.parseInt(st.nextToken());

            int factorial = getFactorial(words.length);
            sb.append(w).append(SPACE).append(size).append(EQUAL);
            if(size > factorial) {
                sb.append(NO);
                continue;
            }

            permutation = new char[words.length];
            for(int i = 0; i < words.length; i++) {
                visit = new boolean[words.length];
                backTracking(i, 0);
            }

            sb.append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static void backTracking(int current, int idx) {
        permutation[idx] = words[current];
        visit[current] = true;

        if(idx == permutation.length - 1) {
            size--;

            if(size != 0) return;
            for (char p: permutation) {
                sb.append(p);
            }

            return;
        }

        for(int next = 0; next < permutation.length; next++){
            if(visit[next]) continue;

            backTracking(next, idx + 1);
            visit[next] = false;
        }
    }

    private static int getFactorial(int n) {
        int f = n;

        while(n > 1) {
            f *= --n;
        }

        return f;
    }
}
