package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 22113번: 창영이와 버스
 *
 * @see https://www.acmicpc.net/problem/22113
 *
 */
public class Boj22113 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> buses = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        while(M-- > 0) {
            buses.add(Integer.parseInt(st.nextToken()) - 1);
        }

        int[][] A = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(transfer(A, buses));
    }

    private static int transfer(int[][] a, List<Integer> query) {
        int cost = 0;
        int from = query.remove(0);

        while(!query.isEmpty()) {
            int to = query.remove(0);
            cost += a[from][to];

            from = to;
        }

        return cost;
    }
}
