package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14571번: 모래시계
 *
 * @see https://www.acmicpc.net/problem/14571
 *
 */
public class Boj14571 {
    private static boolean[][] graph;
    private static long[] triangle;
    private static long[][] overlapped;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new boolean[N][N];
        triangle = new long[N];
        overlapped = new long[N][N];

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            graph[node1][node2] = graph[node2][node1] = true;
        }

        findTriangle(N);
        System.out.println(hourglasses(N));
    }

    private static void findTriangle(int n) {
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(!graph[i][j]) continue;

                for(int k = j + 1; k < n; k++) {
                    if(!graph[k][i] || !graph[j][k]) continue;
                    int[] index = {i, j, k};

                    for(int x = 0; x < 3; x++) {
                        int y = (x + 1) % 3;

                        triangle[index[x]]++;                       // triangle counting
                        overlapped[index[x]][index[y]]++;           // overlapped lines ---> it's not hour grass
                        overlapped[index[y]][index[x]]++;
                    }
                }
            }
        }
    }

    private static long combination(long x) {
        return x * (x - 1) / 2;
    }

    private static long hourglasses(int n) {
        long result = 0;

        for (int i = 0; i < n; i++) {
            result += combination(triangle[i]);

            for (int j = 0; j < n; j++){
                result -= combination(overlapped[i][j]);            // except '<|>'
            }
        }

        return result;
    }
}
