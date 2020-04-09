package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 6987번: 월드컵
 *
 * @see https://www.acmicpc.net/problem/6987/
 *
 */
public class Boj6987 {
    private static final int[][] TEAMS = {{0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 },
            {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5 }};

    private static final String SPACE = " ";

    private static int[][] matrix;
    private static int[][] records;
    private static int[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        matrix = new int[6][3];
        records = new int[6][3];
        result = new int[4];

        for (int games = 0; games < 4; games++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            backTracking(games, 0);
        }

        StringBuilder sb = new StringBuilder();

        for (int x : result) {
            sb.append(x).append(SPACE);
        }

        System.out.println(sb.toString());
    }

    private static void backTracking(int current, int count) {
        if (count == 15) {
            if(result[current] == 1) return;                    // already

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (matrix[i][j] != records[i][j]) return;  // not matched
                }
            }

            result[current] = 1;
            return;
        }

        int team1 = TEAMS[0][count];
        int team2 = TEAMS[1][count];
        
        records[team1][0]++;
        records[team2][2]++;
        backTracking(current, count + 1);               // make records
        records[team1][0]--;
        records[team2][2]--;

        records[team1][1]++;
        records[team2][1]++;
        backTracking(current, count + 1);
        records[team1][1]--;
        records[team2][1]--;

        records[team1][2]++;
        records[team2][0]++;
        backTracking(current, count + 1);
        records[team1][2]--;
        records[team2][0]--;

    }
}
