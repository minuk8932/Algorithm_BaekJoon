package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 19237번: 어른 상어
 *
 * @see https://www.acmicpc.net/problem/19237
 *
 */
public class Boj19237 {
    private static Shark[] sharks;
    private static boolean[] removed;
    private static int[][][] currentByNext;
    private static Queue<Pair> smells = new LinkedList<>();

    private static int N, K;

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int ROW = 0, COL = 1;

    private static class Shark{
        int row;
        int col;
        int dir;

        public Shark(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }
    }

    private static class Pair {
        int value;
        int count;

        public Pair(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        sharks = new Shark[M];
        removed = new boolean[M];
        currentByNext = new int[M][4][4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                int srk = Integer.parseInt(st.nextToken()) - 1;
                if(srk == -1) continue;

                sharks[srk] = new Shark(i, j, -1);
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken()) - 1;
        }

        for(int i = 0; i < M; i++) {
            for(int d = 0; d < 4; d++) {                    // save next direction prior by current direction
                st = new StringTokenizer(br.readLine());

                for(int j = 0; j < 4; j++) {
                    currentByNext[i][d][j] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        System.out.println(move(M));
    }

    private static int move(int m) {
        int count = 1, over = m;

        while(count <= 1000) {
            HashMap<Integer, Integer> hmap = new HashMap<>();

            for(int idx = 0; idx < m; idx++){               // remained smell
                if(removed[idx]) continue;
                Shark current = sharks[idx];

                int sInfo = makeInfo(current.row, current.col, idx);
                smells.offer(new Pair(sInfo, K));
            }

            while(!smells.isEmpty()) {                      // record
                Pair current = smells.poll();
                hmap.put(current.value, current.count);
            }

            for(int idx = 0; idx < m; idx++){
                if(removed[idx]) continue;
                Shark current = sharks[idx];

                boolean moved = false;

                for(int r: currentByNext[idx][current.dir]) {                                // find by priority
                    int nextRow = current.row + DIRECTIONS[r][ROW];
                    int nextCol = current.col + DIRECTIONS[r][COL];

                    if (outOfRange(nextRow, nextCol)) continue;

                    boolean smellRemained = isRemained(m, nextRow, nextCol, hmap);
                    if(smellRemained) continue;

                    sharks[idx] = new Shark(nextRow, nextCol, r);
                    moved = true;
                    break;
                }

                if (!moved) {
                    for(int r: currentByNext[idx][current.dir]) {                            // find before position
                        int nextRow = current.row + DIRECTIONS[r][ROW];
                        int nextCol = current.col + DIRECTIONS[r][COL];

                        if (outOfRange(nextRow, nextCol)) continue;
                        int nInfo = makeInfo(nextRow, nextCol, idx);

                        if(hmap.containsKey(nInfo)) {
                            sharks[idx] = new Shark(nextRow, nextCol, r);
                            hmap.put(nInfo, 1);
                            break;
                        }
                    }
                }
            }

            timeFlow(hmap);
            over += removeSharks(m);

            if(over == 1) return count;
            count++;
        }

        return -1;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= N;
    }

    private static int makeInfo(int row, int col, int idx) {
        return (row * 100 + col) * 1000 + idx;
    }

    private static boolean isRemained(int m, int row, int col, HashMap<Integer, Integer> map) {
        boolean remain = false;

        for(int i = 0; i < m; i++) {
            int nInfo = makeInfo(row, col, i);

            if(map.containsKey(nInfo)){
                remain = true;
                break;
            }
        }

        return remain;
    }

    private static void timeFlow(HashMap<Integer, Integer> map) {
        for(int key: map.keySet()) {                    // smell remove
            int value = (map.get(key) - 1);
            if(value == 0) continue;

            smells.offer(new Pair(key, value));
        }
    }

    private static int removeSharks(int m) {
        int except = 0;

        for(int i = 0; i < m; i++) {                    // shark remove
            if(removed[i]) continue;

            for(int j = i + 1; j < m; j++) {
                if(removed[j]) continue;

                if(sharks[i].row == sharks[j].row && sharks[i].col == sharks[j].col){
                    removed[j] = true;
                    except--;
                }
            }
        }

        return except;
    }
}
