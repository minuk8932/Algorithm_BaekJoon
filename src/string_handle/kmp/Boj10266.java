package string_handle.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 10266번: 시계 사진들
 *
 * @see https://www.acmicpc.net/problem/10266/
 *
 */
public class Boj10266 {
    private static final String I = "impossible";
    private static final String P = "possible";

    private static final int MAX = 360_000;
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        boolean[] src = new boolean[MAX * 2];
        boolean[] snk = new boolean[MAX];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int idx = Integer.parseInt(st.nextToken());             // make 2 * length picture
            src[idx] = src[MAX + idx] = true;
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            snk[Integer.parseInt(st.nextToken())] = true;
        }

        System.out.println(KMP(src, snk) ? P: I);
    }

    private static boolean KMP(boolean[] S, boolean[] T) {
        int[] fail = failure(T);

        int j = 0;
        for(int i = 0; i < MAX * 2; i++) {          // do KMP
            if(j > 0 && S[i] != T[j]) {
                j = fail[j - 1];
            }

            if(S[i] == T[j]) {
                if(j == MAX - 1) return true;
                else j++;
            }
        }

        return false;
    }

    private static int[] failure(boolean[] arr) {
        int[] f = new int[MAX];
        int j = 0;

        for(int i = 1; i < MAX; i++) {
            while(j > 0 && arr[i] != arr[j]) {
                j = f[j - 1];
            }

            if(arr[i] == arr[j]) f[i] = ++j;
        }

        return f;
    }
}
