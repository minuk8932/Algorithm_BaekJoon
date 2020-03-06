package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2233번: 사과 나무
 *
 * @see https://www.acmicpc.net/problem/2233/
 *
 */
public class Boj2233 {
    private static int[][] parent;
    private static int[] deep;
    private static int[] index;

    private static int N;
    private static int CIPHER = 10_000;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        char[] input = br.readLine().toCharArray();

        parent = new int[N + 1][21];
        deep = new int[N + 1];
        index = new int[N * 2];

        int current = 0;
        int next = 0;

        for (int i = 0; i < index.length; i++) {
            if (input[i] == '0'){                   // in
                parent[++next][0] = current;
                deep[next] = deep[current] + 1;
                current = next;
                index[i] = next;
            }
            else {                                  // out
                index[i] = current;
                current = parent[current][0];
            }
        }

        connecting();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken()) - 1;
        int Y = Integer.parseInt(st.nextToken()) - 1;

        int lca = LCA(index[X], index[Y]);
        int res = 0;

        for(int i = 0; i < index.length; i++){
            if(index[i] == lca){
                if(input[i] == '0') res += (i + 1) * CIPHER;        // in
                else res += (i + 1);                                // out
            }
        }

        System.out.println(res / CIPHER + " " + (res % CIPHER));
    }

    private static void connecting(){
        for(int p = 1; p < 21; p++){
            for(int cur = 0; cur <= N; cur++){
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }

    private static int LCA(int x, int y){
        if(deep[x] > deep[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--){                       // matching level
            int jump = 1 << i;
            if(deep[y] - deep[x] >= jump) y = parent[y][i];
        }

        if(x == y) return x;

        for(int i = 20; i >= 0; i--){                       // find lca
            if(parent[x][i] == parent[y][i]) continue;

            x = parent[x][i];
            y = parent[y][i];
        }

        return parent[x][0];
    }
}
