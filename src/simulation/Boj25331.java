package simulation;

import common.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25331번: Drop 7
 *
 * @see https://www.acmicpc.net/problem/25331
 *
 */
public class Boj25331 {

    private static int[] rows = new int[7];
    private static int[][] test;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[7][7];

        for(int i = 0; i < 7; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 7; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] != 0) continue;
                rows[j] = i;
            }
        }

        int ball = Integer.parseInt(br.readLine());
        int min = 50;
        for(int col = 0; col < 7; col++) {
            test = copyArray(map);
            min = Math.min(drop7(ball, col), min);
        }

        System.out.println(min);
    }

    private static int[][] copyArray(int[][] map) {
        int[][] arr = new int[7][7];
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                arr[i][j] = map[i][j];
            }
        }

        return arr;
    }

    private static int drop7(int ball, int col) {
        LinkedList<Point<Integer, Integer>> removeBall = new LinkedList<>();
        test[rows[col]][col] = ball;

        while(true) {
            for(int i = 0; i < 7; i++) {
                for(int j = 0; j < 7; j++) {
                    if(test[i][j] == 0) continue;

                    int target = test[i][j];
                    int rowLen = searchRow(i, j);
                    int colLen = searchCol(i, j);

                    if(rowLen != target && colLen != target) continue;
                    removeBall.add(new Point.Builder<Integer, Integer>(i, j).build());
                }
            }

            if(removeBall.size() == 0) break;

            while(!removeBall.isEmpty()) {
                Point<Integer, Integer> balls = removeBall.remove();
                test[balls.getRow()][balls.getCol()] = 0;
            }

            remake();
        }

        int answer = 0;

        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                if(test[i][j] == 0) continue;
                answer++;
            }
        }

        return answer;
    }

    private static void remake() {
        for(int i = 0; i < 7; i++) {
            for(int j = 6; j >= 0; j--) {
                if(test[j][i] == 0) continue;
                int move = 1;

                while(j + move < 7) {
                    if(test[j + move][i] == 0) {

                        int tmp = test[j + move][i];
                        test[j + move][i] = test[j + move - 1][i];
                        test[j + move - 1][i] = tmp;
                    }

                    move++;
                }
            }
        }
    }

    private static int searchCol(int row, int col) {
        int count = 1;
        int loop = col - 1;

        while(loop >= 0) {
            if(test[row][loop] == 0) break;
            count++;
            loop--;
        }

        loop = col + 1;
        while(loop < 7) {
            if(test[row][loop] == 0) break;
            count++;
            loop++;
        }

        return count;
    }

    private static int searchRow(int row, int col) {
        int count = 1;
        int loop = row - 1;

        while(loop >= 0) {
            if(test[loop][col] == 0) break;
            count++;
            loop--;
        }

        loop = row + 1;
        while(loop < 7) {
            if(test[loop][col] == 0) break;
            count++;
            loop++;
        }

        return count;
    }
}
