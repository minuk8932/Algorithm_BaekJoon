package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1422번: 숫자의 신
 *
 * @see https://www.acmicpc.net/problem/1422
 *
 */
public class Boj1422 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        ArrayList<String> numbers = new ArrayList<>();
        int max = 0;
        for(int i = 0; i < K; i++) {
            numbers.add(br.readLine());
            max = Math.max(Integer.parseInt(numbers.get(i)), max);
        }

        System.out.println(makeBigNumber(N, K, numbers, max));
    }

    /**
     *
     * Make big number using String comparing
     *
     * line 45: sorting numbers by dictionary ordering
     * line 53 ~ 61: if String is equals to max value, then filling (n - k) size by max
     *
     * @param n
     * @param k
     * @param numbers
     * @param max
     * @return
     */
    private static String makeBigNumber(int n, int k, ArrayList<String> numbers, int max) {
        Collections.sort(numbers, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        boolean soldout = false;

        StringBuilder sb = new StringBuilder();
        for (int i = k - 1; i >= 0; i--) {
            String data = numbers.get(i);
            sb.append(data);

            if(soldout) continue;
            if(Integer.parseInt(data) != max) continue;
            int loop = n - k;

            while(loop-- > 0){
                sb.append(max);
            }

            soldout = true;
        }

        return sb.toString();
    }
}
