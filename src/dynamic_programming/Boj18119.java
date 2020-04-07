package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18119번: 단어 암기
 *
 * @see https://www.acmicpc.net/problem/18119/
 *
 */
public class Boj18119 {
    private static final String NEW_LINE = "\n";

    private static int[] values;
    private static int org = (int) (Math.pow(2, 27) - 1);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        values = new int[N];
        for(int i = 0; i < N; i++){
            char[] input = br.readLine().toCharArray();

            for(int j = 0; j < input.length; j++){
                int a = input[j] - 'a';
                values[i] |= 1 << a;                    // make bit mask
            }
        }

        StringBuilder sb = new StringBuilder();
        int size = N;

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);

            if(cmd == 1) org ^= (1 << (c - 'a'));      // forget
            else org |= (1 << (c - 'a'));              // remind

            sb.append(count(N)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int count(int n){
        int count = 0;

        for(int i = 0; i < n; i++){
            if ((values[i] & org) == values[i]) count++;        // forget word?
        }

        return count;
    }
}
