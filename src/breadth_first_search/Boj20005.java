package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 20005번: 보스몬스터 전리품
 *
 * @see https://www.acmicpc.net/problem/20005
 *
 */
public class Boj20005 {
    private static final char BLOCK = 'X';
    private static final char BOSS = 'B';
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static int N, M;
    private static int HP;
    private static int[][] visit;
    private static char[][] map;

    private static Point[] player;
    private static HashMap<Character, Long> deal = new HashMap<>();
    private static boolean[][] timer = new boolean[1_000_001][26];
    private static HashSet<Integer> value = new HashSet<>();

    private static class Player {
        char name;
        int time;

        public Player(char name, int time) {
            this.name = name;
            this.time = time;
        }
    }

    private static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        player = new Point[P];
        int index = 0;

        for(int i = 0; i < N; i++) {
            String input = br.readLine();

            for(int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);

                if(map[i][j] >= 'a' && map[i][j] <= 'z') player[index++] = new Point(i, j);
            }
        }

        while(P-- > 0) {
            st = new StringTokenizer(br.readLine());
            deal.put(st.nextToken().charAt(0), Long.parseLong(st.nextToken()));
        }

        HP = Integer.parseInt(br.readLine());
        System.out.println(dealer());
    }

    private static int dealer() {
        for(int p = 0; p < player.length; p++) {
            visit = new int[N][M];
            bfs(p);
        }

        int count = 0;
        int time = 0;
        long totalDeal = 0;

        for(int t = 0; t < timer.length; t++) {                     // boss hunting
            if(HP <= 0) break;
            ArrayList<Player> list = new ArrayList<>();

            for(int a = 0; a < 26; a++) {
                if(!timer[t][a]) continue;
                list.add(new Player((char) ('a' + a), t));
                count++;
            }

            for(Player p: list) {
                if(time == 0) totalDeal += deal.get(p.name);
                else totalDeal += deal.get(p.name) * (t - time);
            }

            HP -= totalDeal;
            time = t;
        }

        return count;
    }

    private static void bfs(int start) {
        Queue<Point> q = new LinkedList<>();
        q.offer(player[start]);

        visit[player[start].row][player[start].col] = 1;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol)) continue;
                if(map[nextRow][nextCol] == BLOCK || visit[nextRow][nextCol] != 0) continue;
                visit[nextRow][nextCol] = visit[current.row][current.col] + 1;

                if(map[nextRow][nextCol] == BOSS){              // arrived time
                    timer[visit[nextRow][nextCol] - 1][map[player[start].row][player[start].col] - 'a'] = true;
                    return;
                }

                q.offer(new Point(nextRow, nextCol));
            }
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
