package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 1070번: 김지민의 침략
 *
 * @see https://www.acmicpc.net/problem/1070
 *
 */
public class Boj1070 {

    private static int[] alpha = new int[26];
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int INF = 1_000_000_000;
    private static final int CIPHER = 10_000_000;

    private static final char CAPITAL = '*';
    private static final char BLOCK = '-';

    private static ArrayList<Integer>[] connection;
    private static int[][] capacity;
    private static int[][] flow;

    private static int N, M, S, T, size;
    private static ArrayList<Integer> enemy = new ArrayList<>();
    private static int capital = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        for(int i = 0; i < N; i++) {
            String input = br.readLine();

            for(int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);

                if(map[i][j] == CAPITAL) capital = i * M + j;
                else if(isEnemy(i, j, map[i][j])) enemy.add(i * M + j);
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < alpha.length; i++) {
            alpha[i] = Integer.parseInt(st.nextToken());
        }

        graphModeling(map);
        System.out.println(networkFlow());
    }

    private static boolean isEnemy(int row, int col, char c) {
        return (row == 0 || col == 0 || row == N - 1 || col == M - 1) && c != BLOCK;
    }

    /**
     *
     * Graph Modeling
     *
     * line 86 ~ 113: ref. Boj2311
     * Make a dependency in capacity, 1000000X -> X is alphabet cost :: key point
     *
     * @param arr
     */
    private static void graphModeling(char[][] arr) {
        int half = N * M;
        size = half * 2 + 2;

        T = size - 1;
        S = size - 2;

        capacity = new int[size][size];
        flow = new int[size][size];
        connection = new ArrayList[size];
        for(int i = 0; i < size; i++) {
            connection[i] = new ArrayList<>();
        }

        linker(capital + half, T, INF);

        for(int e: enemy) {
            linker(S, e + half, CIPHER + alpha[arr[e / M][e % M] - 'A']);
        }

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                if(arr[row][col] == BLOCK) continue;
                int current = row * M + col;

                if(arr[row][col] != CAPITAL) {
                    linker(current + half, current, CIPHER + alpha[arr[row][col] - 'A']);
                }

                for(final int[] DIRECTION: DIRECTIONS) {
                    int adjRow = row + DIRECTION[ROW];
                    int adjCol = col + DIRECTION[COL];

                    if(outOfRange(adjRow, adjCol)) continue;
                    if(arr[adjRow][adjCol] == BLOCK) continue;
                    int next = adjRow * M + adjCol;

                    linker(current, next + half, INF);
                    linker(next, current + half, INF);
                }
            }
        }
    }

    /**
     *
     * MFMC, Edmond Karf
     *
     * @return
     */
    private static int networkFlow() {
        int[] prev = new int[size];
        int cut = 0;

        while(true) {
            Arrays.fill(prev, -1);

            Queue<Integer> q = new LinkedList<>();
            q.offer(S);

            while(!q.isEmpty()) {
                int current = q.poll();

                for(int next: connection[current]) {
                    if(capacity[current][next] <= flow[current][next]) continue;
                    if(prev[next] != -1) continue;
                    prev[next] = current;

                    q.offer(next);
                }
            }

            if(prev[T] == -1) break;
            int mFlow = INF;

            for(int i = T; i != S; i = prev[i]) {
                mFlow = Math.min(mFlow, capacity[prev[i]][i] - flow[prev[i]][i]);
            }

            for(int i = T; i != S; i = prev[i]) {
                flow[prev[i]][i] += mFlow;
                flow[i][prev[i]] -= mFlow;
            }

            cut += mFlow;
        }

        return cut % CIPHER;
    }

    private static void linker(int from, int to, int cap) {
        capacity[from][to] = cap;

        connection[from].add(to);
        connection[to].add(from);
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= M;
    }
}
