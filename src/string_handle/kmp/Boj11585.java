package string_handle.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11585번: 속타는 저녁 메뉴
 *
 * @see https://www.acmicpc.net/problem/11585/
 *
 */
public class Boj11585 {
    private static final String SLASH = "/";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        char[] circle = input(N, br.readLine(), true);
        char[] menu = input(N, br.readLine(), false);

        System.out.println(KMP(circle, menu));
    }

    private static char[] input(int n, String in, boolean flag) {
        int size = flag ? n * 2 : n;
        char[] arr = new char[size];

        StringTokenizer st = new StringTokenizer(in);
        for(int i = 0; i < n; i++) {
            arr[i] = st.nextToken().charAt(0);
            if(flag) arr[i + n] = arr[i];
        }

        return arr;
    }

    private static StringBuilder KMP(char[] snk, char[] src) {
        int[] fail = failure(src);
        int count = 0;

        int j = 0;
        for(int i = 0; i < snk.length - 1; i++) {                                   // except last word in N * 2 size
            while(j > 0 && snk[i] != src[j]) {
                j = fail[j - 1];
            }

            if(snk[i] == src[j]) {
                if(j == src.length - 1) {
                    j = fail[j];
                    count++;
                }
                else {
                    j++;
                }
            }
        }

        int gcd = GCD(src.length, count);
        StringBuilder sb = new StringBuilder();

        return sb.append(count / gcd).append(SLASH).append(src.length / gcd);       // make possiblity
    }

    private static int[] failure(char[] arr) {
        int[] f = new int[arr.length];
        int j = 0;

        for(int i = 1; i < arr.length; i++) {
            while(j > 0 && arr[i] != arr[j]) {
                j = f[j - 1];
            }

            if(arr[i] == arr[j]) f[i] = ++j;
        }

        return f;
    }

    private static int GCD(int a, int b) {
        if(b == 0) return a;
        return GCD(b, b % a);
    }
}
