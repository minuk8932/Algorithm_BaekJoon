package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1911번: 흙길 보수하기
 *
 * @see https://www.acmicpc.net/problem/1911/
 *
 */
public class Boj1911 {
    private static int division;

    private static class Cover implements Comparable <Cover> {
        int start;
        int end;

        public Cover(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Cover c) {
            return this.start - c.start;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Cover[] covers = new Cover[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            covers[i] = new Cover(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(covers);
        System.out.println(counting(L, covers));
    }

    private static int counting(int l, Cover[] c) {
        int count = 0;
        int covered = 0;

        for(Cover cover: c) {
            if (covered >= cover.end) continue;                         // already covered

            int org = cover.end - cover.start;
            int comp = cover.end - covered - 1;
            division = 0;

            if(org < comp) covered = covering(l, org, cover.start);     // only new cover
            else covered += covering(l, comp, 1);                 // covered + new cover

            count += division;
        }

        return count;
    }

    private static int covering(int l, int range, int start) {
        division = range / l;
        if(range % l != 0) division++;

        return start + division * l - 1;
    }
}
