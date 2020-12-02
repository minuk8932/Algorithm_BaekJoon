package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20153번: 영웅이는 2의 거듭 제곱을 좋아해! 영웅이는 2의 거듭 제곱을 좋아해!
 *
 * @see https://www.acmicpc.net/problem/20153
 *
 */
public class Boj20153 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int xor = 0;
        int[] components = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            components[i] = Integer.parseInt(st.nextToken());
            xor ^= components[i];                               // 2^x data count is odd? 1: 0
        }

        System.out.println(binaryAnalysis(xor, components));
    }

    private static String binaryAnalysis(int xor, int[] compo) {
        int result = integerToBinary(xor);

        for(int i = 0; i < compo.length; i++) {                 // except at least one
            int data = integerToBinary(xor ^ compo[i]);
            result = Math.max(result, data);
        }

        StringBuilder sb = new StringBuilder();
        return sb.append(result).append(result).toString();
    }

    private static int integerToBinary(int a) {
        int value = 0;
        int index = 1;

        while (a > 0) {
            int mod = a % 2;
            if(mod == 1) value += index;

            index *= 2;
            a /= 2;
        }

        return value;
    }
}
