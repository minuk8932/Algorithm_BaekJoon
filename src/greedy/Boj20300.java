package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20300번: 서강 근육맨
 *
 * @see https://www.acmicpc.net/problem/20300
 *
 */
public class Boj20300 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] protein = new long[N];
        for(int i = 0; i < N; i++) {
            protein[i] = Long.parseLong(st.nextToken());
        }

        System.out.println(muscleLoss(protein));
    }

    private static long muscleLoss(long[] protein) {
        Arrays.sort(protein);

        boolean even = protein.length % 2 == 0;
        long result = even ? protein[0]: 0;
        result += protein[protein.length - 1];

        for(int i = even ? 1: 0; i < protein.length / 2; i++) {
            int end = even ? protein.length - 1 - i: protein.length - 2 - i;
            result = Math.max(result, protein[i] + protein[end]);
        }

        return result;
    }
}
