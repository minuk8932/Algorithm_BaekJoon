package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20117번: 호반우의 이상한 품질 계산법
 *
 * @see https://www.acmicpc.net/problem/20117
 *
 */
public class Boj20117 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] hobanwoo = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            hobanwoo[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(maxCost(hobanwoo));
    }

    private static int maxCost(int[] arr) {
        int value = 0;
        Arrays.sort(arr);

        int div = arr.length / 2;
        int half = arr.length % 2 == 1 ? div + 1: div;

        for(int i = arr.length - 1; i >= half; i--) {       // tied with the smallest & largest
            value += arr[i] * 2;
        }

        return value + (arr.length % 2 == 1 ? arr[div]: 0);
    }
}
