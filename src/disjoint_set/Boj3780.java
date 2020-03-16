package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3780번: 네트워크 연결
 *
 * @see https://www.acmicpc.net/problem/3780/
 *
 */
public class Boj3780 {
    private static int[] parent;
    private static int[] cost;
    private static final int MOD = 1_000;

    private static final String ESTIMATE = "E";
    private static final String OVER = "O";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            int N = Integer.parseInt(br.readLine());
            init(N);

            while(true){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                if(cmd.equals(OVER)) break;

                int I = Integer.parseInt(st.nextToken()) - 1;

                if(cmd.equals(ESTIMATE)){
                    find(I);
                    sb.append(cost[I]).append(NEW_LINE);
                }
                else{
                    int J = Integer.parseInt(st.nextToken()) - 1;

                    I = find(I);
                    cost[I] = Math.abs(I - J) % MOD;
                    merge(I, J);
                }
            }
        }

        System.out.println(sb.toString());
    }

    private static void init(int n){
        parent = new int[n];
        cost = new int[n];

        for(int i = 0; i < n; i++){
            parent[i] = -1;
        }
    }

    private static int find(int x){
        if(parent[x] < 0) return x;
        int res = find(parent[x]);
        cost[x] += cost[parent[x]];         // cost compress

        return parent[x] = res;
    }

    private static void merge(int x, int y){
        if(x == y) return;
        parent[x] = y;
    }
}
