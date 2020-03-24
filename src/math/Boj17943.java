package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17943번: 도미노 예측
 *
 * @see https://www.acmicpc.net/problem/17943/
 *
 */
public class Boj17943 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] xored = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N - 1; i++){
            xored[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < N - 1; i++){
            xored[i] = xored[i - 1] ^ xored[i];                 // make a1 ^ a2, a1 ^ a3 ..... a1 ^ an
        }

        StringBuilder sb = new StringBuilder();

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            if(cmd == 0){
                int res = 0;

                if(x != y) {
                    if(x == 0) res = xored[y - 1];
                    else res = xored[x - 1] ^ xored[y - 1];     // am ^ ak == (a1 ^ am) ^ (a1 ^ ak)
                }

                sb.append(res).append(NEW_LINE);
            }
            else {
                int res = Integer.parseInt(st.nextToken());

                if(x != y) {
                    if (x == 0) res ^= xored[y - 1];
                    else res ^= xored[x - 1] ^ xored[y - 1];    // a ^ x ^ a == x
                }

                sb.append(res).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }
}
