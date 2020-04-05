import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14904 {
    private static int[][] capacity;
    private static int[][] flow;

    private static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int size = N * N + 2;

        ArrayList<Integer>[] path = new ArrayList[size];
        for(int i = 0; i < size; i++) {
            path[i] = new ArrayList<>();
        }

        capacity = new int[size][size];
        flow = new int[size][size];

        capacity[size - 2][0] = K;
        path[size - 2].add(0);
        path[0].add(size - 2);

        capacity[size - 3][size - 1] = K;
        path[size - 3].add(size - 1);
        path[size - 1].add(size - 3);

        for(int row = 0; row < N; row++){
            for(int col = 0; col < N; col++){
                int[] nextRow = {row + 1, row};
                int[] nextCol = {col, col + 1};

                for(int i = 0; i < 2; i++) {
                    if (outOfRange(nextRow[i], nextCol[i])) continue;
                    int current = row + col * N;
                    int next = nextRow[i] + nextCol[i] * N;

                    capacity[current][next] = 11;
                    path[current].add(next);
                    path[next].add(current);
                }
            }
        }

        System.out.println(networkFlow(N, path, N, 1));
    }

    private static int networkFlow(int n, ArrayList<Integer>[] list, int source, int sink) {
        int result = 0;
        int[] visit = new int[n * 2];

        while(true) {						// 일반적인 에드몬드 카프 알고리즘
            Arrays.fill(visit, -1);

            Queue<Integer> q = new LinkedList<>();
            q.offer(source);

            while(!q.isEmpty()) {
                int current = q.poll();

                for(int next: list[current]) {
                    if(visit[next] != -1) continue;

                    if(capacity[current][next] - flow[current][next] > 0) {
                        visit[next] = current;
                        if(next == sink) break;

                        q.offer(next);
                    }
                }
            }

            if(visit[sink] == -1) break;

            int minFlow = Integer.MAX_VALUE;
            for(int i = sink; i != source; i = visit[i]) {
                minFlow = Math.min(minFlow, capacity[visit[i]][i] - flow[visit[i]][i]);
            }

            for(int i = sink; i != source; i = visit[i]) {
                flow[visit[i]][i] += minFlow;
                flow[i][visit[i]] -= minFlow;
            }

            result += minFlow;
        }

        return result;
    }

    private static boolean outOfRange(int row, int col){
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}
