package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25206번: 너의 평점은
 *
 * @see https://www.acmicpc.net/problem/25206
 *
 */
public class Boj25206 {

    private static final HashMap<String, Integer> GRADE = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = 20;

        init();

        double total = 0;
        double sum = 0;

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            int score = (int) (Double.parseDouble(st.nextToken()) * 10);
            String g = st.nextToken();

            total += score * GRADE.get(g);
            if(!g.equals("P")) sum += score;
        }

        System.out.printf("%.6f", total / sum / 10);
    }

    private static void init() {
        GRADE.put("A+", 45); GRADE.put("A0", 40);
        GRADE.put("B+", 35); GRADE.put("B0", 30);
        GRADE.put("C+", 25); GRADE.put("C0", 20);
        GRADE.put("D+", 15); GRADE.put("D0", 10);
        GRADE.put("F", 0); GRADE.put("P", 0);
    }
}
