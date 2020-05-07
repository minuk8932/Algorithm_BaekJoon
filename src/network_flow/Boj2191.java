package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 2191번: 들쥐의 탈출
 *
 * @see https://www.acmicpc.net/problem/2191/
 *
 */
public class Boj2191 {
    private static ArrayList<Integer>[] connected;
    private static int[] home;
    private static boolean[] visit;

    private static class Coordinate {
        double x;
        double y;

        public Coordinate(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int length = N + M + 2;

        connected = new ArrayList[length];

        Coordinate[] mice = new Coordinate[N];
        Coordinate[] home = new Coordinate[M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            mice[i] = new Coordinate(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            home[i] = new Coordinate(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }

        int spend = V * S;
        spend *= spend;

        for(int i = 0; i < N; i++) {
            connected[i] = new ArrayList<>();

            for(int j = 0; j < M; j++) {
                double distance = getDistance(mice[i], home[j]);
                if(distance > spend) continue;                          // can reach?

                connected[i].add(j);
            }
        }

        System.out.println(bipartiteMatch(N));
    }

    private static double getDistance(Coordinate c1, Coordinate c2) {
        return (c1.x - c2.x) * (c1.x - c2.x) + (c1.y - c2.y) * (c1.y - c2.y);
    }

    private static int bipartiteMatch(int n) {
        int survived = 0;

        home = new int[101];
        Arrays.fill(home, -1);

        for(int start = 0; start < n; start++) {
            visit = new boolean[101];
            if(dfs(start)) survived++;                  // find survived mice
        }

        return n - survived;                            // count dead
    }

    private static boolean dfs(int current) {
        if(visit[current]) return false;
        visit[current] = true;

        for(int next: connected[current]) {
            if(home[next] == -1 || dfs(home[next])) {
                home[next] = current;
                return true;
            }
        }

        return false;
    }
}
