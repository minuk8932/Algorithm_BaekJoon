package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author exponential-e
 * 백준 14232번: 보석 도둑
 *
 * @see https://www.acmicpc.net/problem/14232
 *
 */
public class Boj14232 {

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long k = Long.parseLong(br.readLine());

        System.out.println(stole(k));
    }

    /**
     *
     * Stole
     *
     * line 39 ~ 44: Find prime factors, for get jewels as much as possible.
     *
     * @param kg
     * @return
     */
    private static String stole(long kg) {
        List<Long> jewels = new ArrayList<>();

        for(long prime = 2; prime * prime <= kg; prime++) {
            while (kg % prime == 0) {
                kg /= prime;
                jewels.add(prime);
            }
        }

        if(kg > 1) jewels.add(kg);

        StringBuilder sb = new StringBuilder();
        Collections.sort(jewels);

        sb.append(jewels.size()).append(NEW_LINE);
        for(long j: jewels) {
            sb.append(j).append(SPACE);
        }

        return sb.toString();
    }
}
