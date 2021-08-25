package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 22935번: 이진 딸기
 *
 * @see https://www.acmicpc.net/problem/22935
 *
 */
public class Boj22935 {

    private static final String NEW_LINE = "\n";
    private static final String STRAWBERRY = "딸기";
    private static final String EMPTY = "V";

    private static int[][] bin;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = PARSE.apply(br.readLine());

        StringBuilder sb = new StringBuilder();
        init();

        for(int n = 1; n < 71; n++) {

            while (T-- > 0) {
                int N = PARSE.apply(br.readLine()) % 28;

                if(N == 0) N = 2;
                if(N > 15) N = 15 - (N % 15);

                for (int i = 0; i < bin[N].length; i++) {
                    sb.append(bin[N][i] == 1 ? STRAWBERRY : EMPTY);
                }

                sb.append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());

    }

    private static void init() {
         bin = ARRAY.apply(4);

         for(int i = 1; i < 16; i++) {
             int loop = i;
             int index = 3;

             while(loop > 0) {
                 bin[i][index--] = loop % 2;
                 loop /= 2;
             }
         }
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;
    private static final Function<Integer, int[][]> ARRAY = x -> new int[16][x];
}
