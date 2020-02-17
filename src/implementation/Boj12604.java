package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 12604번: Store Credit (Large)
 *
 * @see https://acmicpc.net/problem/12604/
 *
 */
public class Boj12604 {
    private static final int FIX = 10_000;

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";
    private static final String CASE = "Case #";
    private static final String COLON = ": ";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for(int n = 1; n <= N; n++){
            int C = Integer.parseInt(br.readLine());
            int I = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] item = new int[I];
            for(int i = 0; i < I; i++){
                item[i] = Integer.parseInt(st.nextToken());
            }

            int result = find(C, I, item);
            sb.append(CASE).append(n).append(COLON).append(result / FIX).append(SPACE).append(result % FIX).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int find(int c, int n, int[] item){
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(item[i] + item[j] == c) return (i + 1) * FIX + (j + 1);      // find result
            }
        }

        return 0;
    }
}
