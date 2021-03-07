package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20950번: 미술가 미미
 *
 * @see https://www.acmicpc.net/problem/20950
 *
 */
public class Boj20950 {

    private static boolean[] visit;
    private static ArrayList<String> permutation = new ArrayList<>();

    private static final String SPACE = " ";
    private static int N;

    private static class RGB{
        int R;
        int G;
        int B;

        public RGB(int r, int g, int b) {
            R = r;
            G = g;
            B = b;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        RGB[] candidate = new RGB[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            candidate[i] = new RGB(Integer.parseInt(st.nextToken())
                    , Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        RGB target = new RGB(Integer.parseInt(st.nextToken())
                , Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for(int i = 0; i < N; i++) {
            visit = new boolean[N];
            recursion(1, i, i + "");
        }

        System.out.println(mix(target, candidate));
    }

    /**
     *
     * Make Permutation
     *
     * nC2 ~ nCk, k at most 7
     *
     * @param count
     * @param current
     * @param value
     */
    private static void recursion(int count, int current, String value) {
        if(count >= 2) {
            permutation.add(value);
            if (count == Math.min(N, 7)) return;
        }

        visit[current] = true;

        for(int next = current + 1; next < N; next++) {
            if(visit[next]) continue;

            recursion(count + 1, next, value + SPACE + next);
            visit[next] = false;
        }
    }

    private static int mix(RGB T, RGB[] S) {
        int min = Integer.MAX_VALUE;

        for(String p: permutation) {
            StringTokenizer st = new StringTokenizer(p);
            int size = st.countTokens();

            RGB sum = new RGB(0, 0, 0);
            while(st.hasMoreTokens()) {
                int idx = Integer.parseInt(st.nextToken());
                sum.R += S[idx].R;
                sum.G += S[idx].G;
                sum.B += S[idx].B;
            }

            sum.R /= size;
            sum.G /= size;
            sum.B /= size;

            min = Math.min(min, difference(sum, T));
        }

        return min;
    }

    private static int difference(RGB s, RGB t) {
        return Math.abs(s.R - t.R) + Math.abs(s.G - t.G) + Math.abs(s.B - t.B);
    }
}
