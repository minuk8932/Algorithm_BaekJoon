package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1106번: 호텔
 *
 * @see https://www.acmicpc.net/problem/1106/
 *
 */
public class Boj1106 {

    private static class Pair{
        int cost;
        int cust;

        public Pair(int cost, int cust){
            this.cost = cost;
            this.cust = cust;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        Pair[] in = new Pair[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            in[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(search(C, in));
    }

    private static int search(int c, Pair[] p){
        int[] visit = new int[2_001];
        int result = Integer.MAX_VALUE;

        Arrays.fill(visit, result);

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);

        visit[0] = 1;

        while(!q.isEmpty()){
            int current = q.poll();

            for(Pair add: p){
                int next = current + add.cust;

                if(next >= visit.length || visit[next] <= visit[current] + add.cost) continue;
                visit[next] = visit[current] + add.cost;                // invest

                if(next >= c) result = Math.min(result, visit[next]);
                q.offer(next);
            }
        }

        return result - 1;
    }
}
