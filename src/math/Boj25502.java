package math;

import java.io.*;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 25502번: 등차수열? 등비수열?
 *
 * @see https://wwww.acmicpc.net/problem/25502
 *
 */
public class Boj25502 {

    private static final String NEW_LINE = "\n";
    private static final char ADD = '+';
    private static final char MUL = '*';
    private static final char NOTHING = '?';

    private static ArrayList<Long> convertedDiff;
    private static ArrayList<Long> convertedRat;

    private static HashMap<Long, Integer> difference = new HashMap<>();
    private static HashMap<Long, Integer> ratio = new HashMap<>();

    private static long[] A;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        A = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());

            if(i == 0) continue;
            difference.merge(A[i] - A[i - 1], 1, Integer::sum);

            long div = A[i] % A[i - 1] != 0 ? -1: A[i] / A[i - 1];
            ratio.merge(div, 1, Integer::sum);
        }

        StringBuilder sb = new StringBuilder();

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1;
            long x = Long.parseLong(st.nextToken());

            sb.append(reCalculation(i, x)).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static char reCalculation(int idx, long value) {
        int left = idx - 1;
        int right = idx + 1;

        convertedDiff = new ArrayList<>();
        convertedRat = new ArrayList<>();

        if(left >= 0) transfer(idx, left, -1);
        if(right < A.length) transfer(right, idx, -1);

        A[idx] = value;

        if(left >= 0) transfer(idx, left, 1);
        if(right < A.length) transfer(right, idx, 1);

        for(long d: convertedDiff) {
            int count = difference.get(d);
            if(count != A.length - 1 || d <= 0) continue;

            return ADD;
        }

        for(long r: convertedRat) {
            int count = ratio.get(r);
            if(count != A.length - 1 || r <= 0) continue;

            return MUL;
        }

        return NOTHING;
    }

    private static void transfer(int max, int min, int add) {
        long diff = A[max] - A[min];
        difference.merge(diff, add, Integer::sum);

        long rat = A[max] % A[min] == 0 ? A[max] / A[min]: -1;
        ratio.merge(rat, add, Integer::sum);

        if(add == -1) return;
        convertedDiff.add(diff);
        convertedRat.add(rat);
    }
}
