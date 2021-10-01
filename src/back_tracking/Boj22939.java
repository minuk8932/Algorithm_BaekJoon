package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author exponential-e
 * 백준 22939번: 쿠키 크루
 *
 * @see https://www.acmicpc.net/problem/22939
 *
 */
public class Boj22939 {

    private static final String[] ANSWER = {"Assassin", "Healer", "Mage", "Tanker"};
    private static final Map<Integer, Point>[] MAPPER = new HashMap[4];
    private static final Map<Character, Integer> INDEX = new HashMap<>();

    private static final char ASSASSIN = 'J', HEALER = 'C', MAGE = 'B', TANKER = 'W';
    private static final char EMPTY = 'X', COOKIE_CREW_PP = '#', HOME = 'H';

    private static int N;
    private static char[][] map;

    private static Point start = new Point(-1, -1);
    private static Point end = new Point(-1, -1);

    private static List<Integer> permutation = new ArrayList<>();
    private static boolean[] visit;

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
        N = Integer.parseInt(br.readLine());
        init();

        int[] count = new int[4];
        map = new char[N][N];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();

            for(int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == EMPTY) continue;

                if(map[i][j] == COOKIE_CREW_PP) end = new Point(i, j);
                else if(map[i][j] == HOME) start = new Point(i, j);
                else MAPPER[INDEX.get(map[i][j])].put(count[INDEX.get(map[i][j])]++, new Point(i, j));
            }
        }

        for(int i = 1; i < 4; i++) {
            visit = new boolean[4];
            recursion(i, i, 1);
        }

        System.out.println(applicant());
    }

    /**
     *
     * Applicant
     *
     * line 91 ~ 101: get cost, from home to cookie crew pp
     *
     * @return
     */
    private static String applicant() {
        int min = Integer.MAX_VALUE;
        int target = -1;

        for(int user = 0; user < 4; user++) {
            for(int p: permutation) {
                int loop = p;

                Point current = start;
                int cost = 0;

                while(loop > 0) {
                    int pos = (loop % 10) - 1;

                    Point next = MAPPER[user].get(pos);
                    cost += manhattanDistance(current, next);

                    loop /= 10;
                    current = next;
                }

                cost += manhattanDistance(current, end);

                if(min <= cost) continue;
                min = cost;
                target = user;
            }
        }

        return ANSWER[target];
    }

    private static int manhattanDistance(Point current, Point next) {
        return Math.abs(current.row - next.row) + Math.abs(current.col - next.col);
    }

    private static void recursion(int current, int value, int count) {
        if(count == 3) {
            permutation.add(value);
            return;
        }

        visit[current] = true;

        for(int next = 1; next < 4; next++) {
            if(visit[next]) continue;

            recursion(next, value * 10 + next, count + 1);
            visit[next] = false;
        }
    }

    private static void init() {
        INDEX.put(ASSASSIN, 0);
        INDEX.put(HEALER, 1);
        INDEX.put(MAGE, 2);
        INDEX.put(TANKER, 3);

        for(int i = 0; i < 4; i++) {
            MAPPER[i] = new HashMap<>();
        }
    }
}
