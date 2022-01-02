package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16935번: 배열 돌리기 3
 *
 * @see https://www.acmicpc.net/problem/16935
 *
 */
public class Boj16935 {

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    private static int[][] arr;
    private static List<Integer> direction = new ArrayList<>();

    private static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while (R-- > 0) {
            direction.add(Integer.parseInt(st.nextToken()));
        }

        rotating();
        System.out.println(printer());
    }

    private static String printer() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(SPACE);
            }

            sb.append(NEW_LINE);
        }

        return sb.toString();
    }

    private static void rotating() {

        for (int dir : direction) {
            switch (dir) {
                case 1:
                    copy(upAndDown());
                    break;

                case 2:
                    copy(leftAndRight());
                    break;

                case 3:
                    copySwitch(rightRotate());
                    break;

                case 4:
                    copySwitch(leftRotate());
                    break;

                case 5:
                    copy(clockWise());
                    break;

                case 6:
                    copy(countClockWise());
                    break;
            }
        }
    }

    private static int[][] countClockWise() {
        int[][] temp = new int[N][M];

        int halfN = N >> 1;
        int halfM = M >> 1;

        for (int i = 0; i < halfN; i++) {
            for (int j = 0; j < halfM; j++) {
                temp[halfN + i][j] = arr[i][j];
            }
        }

        for (int i = 0; i < halfN; i++) {
            for (int j = 0; j < halfM; j++) {
                temp[halfN + i][halfM + j] = arr[halfN + i][j];
            }
        }

        for (int i = 0; i < halfN; i++) {
            for (int j = 0; j < halfM; j++) {
                temp[i][halfM + j] = arr[halfN + i][halfM + j];
            }
        }

        for (int i = 0; i < halfN; i++) {
            for (int j = 0; j < halfM; j++) {
                temp[i][j] = arr[i][halfM + j];
            }
        }

        return temp;
    }

    private static int[][] clockWise() {
        int[][] temp = new int[N][M];

        int halfN = N >> 1;
        int halfM = M >> 1;

        for (int i = 0; i < halfN; i++) {
            for (int j = 0; j < halfM; j++) {
                temp[i][j] = arr[halfN + i][j];
            }
        }

        for (int i = 0; i < halfN; i++) {
            for (int j = 0; j < halfM; j++) {
                temp[i][halfM + j] = arr[i][j];
            }
        }

        for (int i = 0; i < halfN; i++) {
            for (int j = 0; j < halfM; j++) {
                temp[halfN + i][halfM + j] = arr[i][halfM + j];
            }
        }

        for (int i = 0; i < halfN; i++) {
            for (int j = 0; j < halfM; j++) {
                temp[halfN + i][j] = arr[halfN + i][halfM + j];
            }
        }

        return temp;
    }

    private static int[][] leftRotate() {
        int[][] temp = new int[M][N];

        for (int i = M - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = arr[j][M - 1 - i];
            }
        }

        return temp;
    }

    private static int[][] rightRotate() {
        int[][] temp = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = N - 1; j >= 0; j--) {
                temp[i][j] = arr[N - 1 - j][i];
            }
        }

        return temp;
    }

    private static int[][] leftAndRight() {
        int[][] temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = M - 1; j >= 0; j--) {
                temp[i][j] = arr[i][M - 1 - j];
            }
        }

        return temp;
    }

    private static int[][] upAndDown() {
        int[][] temp = new int[N][M];

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = arr[N - 1 - i][j];
            }
        }

        return temp;
    }

    private static void copy(int[][] src) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = src[i][j];
            }
        }
    }

    private static void copySwitch(int[][] src) {
        int temp = N;
        N = M;
        M = temp;

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = src[i][j];
            }
        }
    }
}
