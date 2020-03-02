package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17435번: 합성 함수와 쿼리
 *
 * @see https://www.acmicpc.net/problem/17435/
 *
 */
public class Boj17435 {
    private static int[][] parent;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());

        parent = new int[m][21];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            parent[i][0] = Integer.parseInt(st.nextToken()) - 1;
        }

        connecting(m);

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());

        while(Q-- > 0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1;

            sb.append(find(n, x) + 1).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void connecting(int n){
        for(int p = 1; p < 21; p++){
            for(int cur = 0; cur < n; cur++){                   // make sparse table
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }

    private static int find(int n, int x){
        for(int i = 20; i >= 0; i--){                           // find f_x(n)
            int jump = 1 << i;
            if(n < jump) continue;

            n -= jump;
            x = parent[x][i];
        }

        return x;
    }
}
