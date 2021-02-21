package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 1420번: 학교 가지마!
 *
 * @see https://www.acmicpc.net/problem/1420
 *
 */
public class Boj1420 {

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int INF = 100_000;
    private static final long CIPHER = 100_000L;

    private static final char BLOCK = '#';
    private static final char START = 'K';
    private static final char END = 'H';

    private static ArrayList<Integer>[] connection;
    private static HashMap<Long, Integer> capacity;
    private static HashMap<Long, Integer> flow;

    private static int S, T, size, src, snk;
    private static int N, M;
    private static boolean notDefense;

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

                if(map[i][j] == START) src = i * M + j;
                else if(map[i][j] == END) snk = i * M + j;
            }
        }

        graphModeling(map);
        System.out.println(networkFlow());
    }

    /**
     *
     * MFMC, Edmond-karf
     *
     * @return
     */
    private static int networkFlow(){
        if((N == 1 && M == 1) || notDefense) return -1;

        int[] prev = new int[size];
        int result = 0;

        while(true){
            Arrays.fill(prev, -1);

            Queue<Integer> q = new LinkedList<>();
            q.offer(S);

            while(!q.isEmpty()) {
                int current = q.poll();

                for(int next: connection[current]) {
                    long index = CIPHER * current + next;

                    if(capacity.get(index) <= flow.get(index)) continue;
                    if(prev[next] != -1) continue;

                    prev[next] = current;
                    q.offer(next);
                }
            }

            if(prev[T] == -1) break;

            int max = Integer.MAX_VALUE;
            for(int i = T; i != S; i = prev[i]) {
                long index = CIPHER * prev[i] + i;
                max = Math.min(max, capacity.get(index) - flow.get(index));
            }

            for(int i = T; i != S; i = prev[i]) {
                long index = CIPHER * prev[i] + i;
                long rev = CIPHER * i + prev[i];

                flow.merge(index, max, Integer::sum);
                flow.merge(rev, -max, Integer::sum);
            }

            result += max;
        }

        return result;
    }

    /**
     *
     * Graph Modeling
     * line 129 ~ line 156: ref. Boj2311.java
     *
     * @param arr
     */
    private static void graphModeling(char[][] arr) {
        int half = N * M;
        size = half * 2 + 2;
        S = size - 2;
        T = size - 1;

        capacity = new HashMap<>();
        flow = new HashMap<>();
        connection = new ArrayList[size];

        for(int i = 0; i < size; i++) {
            connection[i] = new ArrayList<>();
        }

        linker(S, src, INF);
        linker(snk + half, T, INF);
        for(int i = 0; i < half; i++) {
            linker(i + half, i, 1);
        }

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                if(arr[row][col] == BLOCK) continue;

                boolean adjacent = false;
                if(arr[row][col] == START) adjacent = true;
                int current = row * M + col;

                for(final int[] DIRECTION: DIRECTIONS) {
                    int adjRow = row + DIRECTION[ROW];
                    int adjCol = col + DIRECTION[COL];

                    if(outOfRange(adjRow, adjCol)) continue;
                    if(arr[adjRow][adjCol] == BLOCK) continue;
                    if(adjacent && arr[adjRow][adjCol] == END) notDefense = true;
                    int next = adjRow * M + adjCol;

                    linker(current, next + half, INF);
                    linker(next, current + half, INF);
                }
            }
        }
    }

    private static void linker(int from, int to, int cap) {
        long index = CIPHER * from + to;
        long rev = CIPHER * to + from;

        capacity.put(index, cap);
        capacity.put(rev, 0);
        flow.put(index, 0);
        flow.put(rev, 0);

        connection[from].add(to);
        connection[to].add(from);
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
