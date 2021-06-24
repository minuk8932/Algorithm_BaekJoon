package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 9706번: Find The Marbles
 *
 * @see https://www.acmicpc.net/problem/9706
 *
 */
public class Boj9706 {

    private static final String CASE = "Case #";
    private static final String COLON = ": ";
    private static final String NEW_LINE = "\n";

    private static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Equation {
        int a;
        int b;
        int c;

        public Equation(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            Coordinate[] coors = new Coordinate[N];

            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                coors[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            sb.append(CASE).append(t).append(COLON).append(countLines(N, coors)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int countLines(int n, Coordinate[] c) {
        List<Equation> eqs = makeEquationList(n, c);
        int max = 0;

        for(Equation lines: eqs) {
            int count = 0;

            for(int i = 0; i < n; i++) {
                if(constants(lines, c[i])) count++;
                else if(lines.a * c[i].x + lines.b * c[i].y + lines.c == 0) count++;
            }

            max = Math.max(max, count);
        }

        return max;
    }

    /**
     *
     * Constants
     *
     * line 93: Find constants line, such as x = 2, y = 1 so on..
     *
     * @param line
     * @param c
     * @return
     */
    private static boolean constants(Equation line, Coordinate c) {
        return (line.a == 1 && line.b == 0 && line.c == c.x)
                || (line.a == 0 && line.b == 1 && line.c == c.y);
    }

    /**
     *
     * Make equation list
     *
     * line 115 ~ 126: make line equation by 3 cases.
     *
     * @param n
     * @param c
     * @return
     */
    private static List<Equation> makeEquationList(int n, Coordinate[] c) {
        List<Equation> list = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                Coordinate c1 = c[i];
                Coordinate c2 = c[j];

                if(c1.x == c2.x) {
                    list.add(new Equation(1, 0, -c1.x));
                }
                else if(c1.y == c2.y) {
                    list.add(new Equation(0, 1, -c1.y));
                }
                else {
                    int a = c2.y - c1.y;
                    int b = c2.x - c1.x;

                    list.add(new Equation(-a, b,  c1.x * a - c1.y * b));
                }
            }
        }

        return list;
    }
}
