package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18234번: 당근 훔쳐 먹기
 *
 * @see https://www.acmicpc.net/problem/18234/
 *
 */
public class Boj18234 {
    private static final long CIPHER = 1_000;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long T = Long.parseLong(st.nextToken());

        long[] carrot = new long[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long w = Long.parseLong(st.nextToken());
            long p = Long.parseLong(st.nextToken());

            carrot[i] = p * CIPHER + w;
        }

        System.out.println(steal(T - N, carrot));
    }

    private static long steal(long days, long[] carrot) {
        Arrays.sort(carrot);
        long result = 0;
        int index = 0;

        for(long c: carrot) {
            result += (days + index++) * (c / CIPHER) + c % CIPHER;     // put off as much as possible, opt
        }

        return result;
    }
}
