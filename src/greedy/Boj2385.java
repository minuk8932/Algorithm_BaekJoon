package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2385번: Secret Sharing
 *
 * @see https://www.acmicpc.net/problem/2385
 *
 */
public class Boj2385 {
    private static String zero = "";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<String> numbers = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            numbers.add(st.nextToken());
        }

        System.out.println(makeSmallNumber(N, numbers));
    }

    /**
     *
     * Small number maker, not start with zero
     *
     * line 45 ~ 51: find zero start data sequence
     * line 58 ~ 60: find another by plush with variable zero
     *
     * @param n
     * @param numbers
     * @return
     */
    private static String makeSmallNumber(int n, ArrayList<String> numbers) {
        Collections.sort(numbers, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        int count = 0;

        for (int i = 0; i < n; i++){
            String data = numbers.get(i);
            if(data.charAt(0) != '0') break;

            zero += data;
            count++;
        }

        if(count == n) return "INVALID";

        StringBuilder sb = new StringBuilder();
        ArrayList<String> remained = new ArrayList<>();
        for(int i = count; i < n; i++) {
            remained.add(numbers.get(i));
        }

        Collections.sort(remained, (s1, s2) -> (s1 + zero + s2).compareTo(s2 + zero + s1));
        sb.append(remained.get(0));
        boolean flag = false;

        for(int i = 0; i < n; i++) {
            if(numbers.get(i).equals(remained.get(0)) && !flag) {
                flag = true;
                continue;
            }

            sb.append(numbers.get(i));
        }

        return sb.toString();
    }
}
