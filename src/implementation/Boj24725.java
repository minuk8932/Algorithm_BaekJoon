package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 24725번: 엠비티아이
 *
 * @see https://www.acmicpc.net/problem/24725
 *
 */
public class Boj24725{

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1},
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    private static final int ROW = 0, COL = 1;
    private static final HashSet<String> MBTI = new HashSet<>();
    private static int N, M;
    private static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i = 0; i < N; i++) {
            String input = br.readLine();

            for(int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        mbtiInit();
        System.out.println(searching());
    }

    private static int searching() {
        int count = 0;

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                for(final int[] DIRECTION: DIRECTIONS) {
                    StringBuilder mbtiBuilder = new StringBuilder();

                    for(int d = 0; d <= 3; d++) {
                        int nextRow = row + DIRECTION[ROW] * d;
                        int nextCol = col + DIRECTION[COL] * d;

                        if(outOfRange(nextRow, nextCol)) break;
                        mbtiBuilder.append(map[nextRow][nextCol]);
                    }

                    if(mbtiBuilder.length() != 4) continue;
                    if(!MBTI.contains(mbtiBuilder.toString())) continue;
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }

    private static void mbtiInit(){
        MBTI.add("ENTJ"); MBTI.add("ENTP"); MBTI.add("ENFJ"); MBTI.add("ENFP");
        MBTI.add("ESTJ"); MBTI.add("ESFJ"); MBTI.add("ESTP"); MBTI.add("ESFP");
        MBTI.add("INTJ"); MBTI.add("INTP"); MBTI.add("INFJ"); MBTI.add("INFP");
        MBTI.add("ISTJ"); MBTI.add("ISFJ"); MBTI.add("ISTP"); MBTI.add("ISFP");
    }
}