package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1703번: 생장점
 *
 * @see https://www.acmicpc.net/problem/1703/
 *
 */
public class Boj1703 {
    private static final String NEW_LINE = "\n";

    private static class Pair {
        int split;
        int cutted;

        public Pair(int split, int cutted) {
            this.split = split;
            this.cutted = cutted;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if(a == 0) break;

            Pair[] branch = new Pair[a];
            for(int i = 0; i < a; i++) {
                branch[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            sb.append(branchchorama(branch)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int branchchorama(Pair[] pairs) {
        int size = 1;

        for(Pair p: pairs) {
            size *= p.split;
            size -= p.cutted;
        }

        return size;
    }
}
