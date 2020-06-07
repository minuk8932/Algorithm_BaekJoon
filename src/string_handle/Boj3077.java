package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3077번: 임진왜란
 *
 * @see https://www.acmicpc.net/problem/3077/
 *
 */
public class Boj3077 {
    private static HashMap<String, Integer> list = new HashMap<>();
    private static String[] report;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            list.put(st.nextToken(), i);
        }

        report = new String[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            report[i] = st.nextToken();
        }

        System.out.println(getScore(N));
    }

    private static String getScore(int n) {
        int count = 0;

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if (i == j) continue;
                int[] index = {list.get(report[i]), list.get(report[j])};

                if(index[0] < index[1]) count++;
            }
        }

        StringBuilder sb = new StringBuilder();
        return sb.append(count).append("/").append(n * (n - 1) / 2).toString();
    }
}
