package coordinate_compression;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18869번: 멀티버스 II
 *
 * @see https://www.acmicpc.net/problem/18869
 *
 */
public class Boj18869 {

    private static List<String> sequence = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int[] planet = new int[N];
            int[] input = new int[N];

            for(int j = 0; j < N; j++) {
                planet[j] = Integer.parseInt(st.nextToken());
                input[j] = planet[j];
            }

            codeBuilder(planet, input);
        }

        System.out.println(makeDataSetAndCount());
    }

    private static int makeDataSetAndCount() {
        Collections.sort(sequence);

        Map<String, Integer> set = new HashMap<>();
        for(String c: sequence) {
            set.merge(c, 1, Integer::sum);
        }

        int total = 0;
        for(int value: set.values()) {
            total += ((value - 1) * value) >> 1;
        }

        return total;
    }


    private static void codeBuilder(int[] planet, int[] input) {
        Arrays.sort(planet);
        Map<Integer, Integer> indexMapper = new HashMap<>();

        int index = 0;
        indexMapper.put(planet[0], index++);

        for (int i = 1; i < planet.length; ++i) {
            if (planet[i - 1] == planet[i]) continue;
            indexMapper.put(planet[i], index++);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < planet.length; ++i) {
            sb.append(makeSequence(indexMapper.get(input[i])));
        }

        sequence.add(sb.toString());
    }

    private static String makeSequence(int number) {
        StringBuilder sb = new StringBuilder();
        String target = number + "";

        int len = target.length();
        for(int i = 0; i < 4 - len; i++) {
            sb.append(0);
        }

        sb.append(target);
        return sb.toString();
    }
}
