package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author exponential-e
 * 백준 16877번: 핌버
 *
 * @see https://www.acmicpc.net/problem/16877/
 *
 */

public class Boj16877 {
    private static ArrayList<Integer> fibonacci = new ArrayList<>();
    private static int[] grundy;
    private static int size;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        makeFibonacci();

        int N = Integer.parseInt(br.readLine());
        int result = 0;

        size = fibonacci.size();

        StringTokenizer st = new StringTokenizer(br.readLine());
        while(N-- > 0) {
            result ^= fimbo(Integer.parseInt(st.nextToken()));
        }

        System.out.println(result > 0 ? "koosaga": "cubelover");
    }

    private static int fimbo(int current) {
        if(current == 0) return 0;

        if(grundy[current] != -1) return grundy[current];
        int result = 0;

        for(int i = size - 1; i >= 0; i--) {            // find appropriate fibonacci number
            int f = fibonacci.get(i);
            if(f > current) continue;

            result |= 1 << fimbo(current - f);  // find value records
        }

        for(int i = 0; i < 20; i++) {
            if ((result & (1 << i)) != 0) continue;     // value's appearance
            return grundy[current] = i;
        }

        return -1;
    }

    private static void makeFibonacci() {
        grundy = new int[3_000_001];
        Arrays.fill(grundy, -1);

        fibonacci.add(1);
        fibonacci.add(2);

        int loop;
        int idx = 2;

        while (true) {
            loop = fibonacci.get(idx - 1) + fibonacci.get(idx - 2);
            if(loop > 3_000_000) break;

            fibonacci.add(loop);
            idx++;
        }
    }
}


