package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 24912번: 카드 색칠하기
 *
 * @see https://www.acmicpc.net/problem/24912
 *
 */
public class Boj24912 {

    private static final String SPACE = " ";
    private static int[] a;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        a = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(coloring());
    }

    private static String coloring() {

        for(int i = 1; i < a.length - 1; i++) {
            if(a[i] != 0) continue;
            int val = 1 << a[i - 1];
            val |= 1 << a[i + 1];

            int[] shifts = {1 << 1, 1 << 2, 1 << 3};
            for(int x = 1; x <= 3; x++) {
                if((val & shifts[x - 1]) != 0) continue;
                a[i] = x;
            }
        }

        a[0] = filler(0, 1);
        a[a.length - 1] = filler(a.length - 1, a.length - 2);

        StringBuilder sb = new StringBuilder();
        sb.append(a[0]).append(SPACE);
        for(int i = 1; i < a.length; i++) {
            if(a[i - 1] == a[i]) return "-1";
            sb.append(a[i]).append(SPACE);
        }

        return sb.toString();
    }

    private static int filler(int target, int idx) {
        if(a[target] != 0) return a[target];
        if(a.length == 1) return 1;

        for(int i = 1; i <= 3; i++) {
            if(a[idx] == i) continue;
            return i;
        }

        return 0;
    }
}
