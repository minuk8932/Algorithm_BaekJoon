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
    private static int[][] candies;

    private static ArrayList<Node>[] connected;

    private static int N;

    private static class Node {
        int node;
        int candy;

        public Node(int node, int candy) {
            this.node = node;
            this.candy = candy;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        candies = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                candies[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int size = graphModeling(K);
        System.out.println(networkFlow(size - 2,  size - 1));
    }

    private static int graphModeling(int k) {           // mcmf
        int size = N * N + 2;

        connected = new ArrayList[size];
        for(int i = 0; i < size; i++) {
            connected[i] = new ArrayList<>();
        }

        capacity = new int[size][size];
        flow = new int[size][size];

        capacity[size - 2][0] = k;
        capacity[size - 3][size - 1] = k;

        connected[size - 2].add(new Node(0, 0));
        connected[0].add(new Node(size - 2, 0));
        connected[size - 3].add(new Node(size - 1, 0));
        connected[size - 1].add(new Node(size - 3, 0));

        for(int row = 0; row < N; row++){
            for(int col = 0; col < N; col++){
                int[] nextRow = {row + 1, row};
                int[] nextCol = {col, col + 1};

                for(int i = 0; i < 2; i++) {
                    if (outOfRange(nextRow[i], nextCol[i])) continue;
                    int current = row * N + col;
                    int next = nextRow[i] * N + nextCol[i];

                    capacity[current][next] = k;
                    connected[current].add(new Node(next, candies[nextRow[i]][nextCol[i]]));
                    connected[next].add(new Node(current, 0));
                }
            }
        }

        return size;
    }

    private static int networkFlow(int src, int snk) {
        int result = 0;

        int[] visit = new int[N * N + 2];
        boolean[] empty = new boolean[N * N + 2];

        while(true) {
            Arrays.fill(visit, -1);

            Queue<Node> q = new LinkedList<>();
            q.offer(new Node(src, 0));

            int candy = 0;

            while(!q.isEmpty()) {
                Node current = q.poll();

                for(Node next: connected[current.node]) {
                    if(visit[next.node] != -1) continue;
                    if(capacity[current.node][next.node] <= flow[current.node][next.node]) continue;
                    visit[next.node] = current.node;
                    if(next.node == snk) break;

                    if(!empty[next.node]){
                        empty[next.node] = true;
                        candy += next.candy;
                    }

                    q.offer(next);
                }
            }

            if(visit[snk] == -1) break;

            int minFlow = Integer.MAX_VALUE;
            for(int i = snk; i != src; i = visit[i]) {
                minFlow = Math.min(minFlow, capacity[visit[i]][i] - flow[visit[i]][i]);
            }

            for(int i = snk; i != src; i = visit[i]) {
                flow[visit[i]][i] += minFlow;
                flow[i][visit[i]] -= minFlow;
            }

            result += candy;
        }

        return result;
    }

    private static boolean outOfRange(int row, int col){
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}
