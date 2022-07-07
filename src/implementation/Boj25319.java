package implementation;

import common.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25319번: Twitch Plays VIIIbit Explorer
 *
 * @see https://www.acmicpc.net/problem/25319
 *
 */
public class Boj25319 {

    private static final ArrayList<LinkedList<Point<Integer, Integer>>> KEYWORD = new ArrayList<>();
    private static final HashMap<Character, Integer> INDEX_MAPPER = new HashMap<>();
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";
    private static final String DOWN = "D";
    private static final String UP = "U";
    private static final String RIGHT = "R";
    private static final String LEFT = "L";
    private static final String PICK = "P";
    private static final char EMPTY = '?';

    private static StringBuilder path = new StringBuilder();
    private static char[][] map;
    private static int N, M;
    private static int repeat = 1_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i = 0; i < N; i++) {
            String input = br.readLine();

            for(int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        char[] id = br.readLine().toCharArray();
        keywordSetting(id);

        System.out.println(searching(id));
    }

    private static StringBuilder searching(char[] id) {
        int total = repeat;

        Point<Integer, Integer> current
            = new Point.Builder<Integer, Integer>(0, 0).build();

        while(repeat-- > 0) {
            for(char word: id) {
                int index = INDEX_MAPPER.get(word);

                Point<Integer, Integer> next = KEYWORD.get(index).remove();
                Point<Integer, Integer> difference = new Point.Builder<Integer, Integer>
                    (next.getRow() - current.getRow(), next.getCol() - current.getCol())
                    .build();

                tracking(difference.getRow(), new String[] {DOWN, UP});
                tracking(difference.getCol(), new String[] {RIGHT, LEFT});

                path.append(PICK);
                map[next.getRow()][next.getCol()] = EMPTY;
                current = next;
            }
        }

        Point<Integer, Integer> difference = new Point.Builder<Integer, Integer>
            (N - 1 - current.getRow(), M - 1 - current.getCol())
            .build();

        tracking(difference.getRow(), new String[] {DOWN, UP});
        tracking(difference.getCol(), new String[] {RIGHT, LEFT});

        StringBuilder sb = new StringBuilder();
        sb.append(total).append(SPACE).append(path.length()).append(NEW_LINE)
            .append(path);

        return sb;
    }

    private static void tracking(int difference, final String[] DIRECTION) {
        if(difference == 0) return;

        int loop = Math.abs(difference);
        String target = difference > 0 ? DIRECTION[0]: DIRECTION[1];

        while(loop-- > 0) {
            path.append(target);
        }
    }

    private static void keywordSetting(char[] id) {
        for(int i = 0; i < id.length; i++) {
            KEYWORD.add(i, new LinkedList<>());
        }

        HashMap<Character, Integer> count = new HashMap<>();
        int index = 0;

        for(char word: id) {
            count.merge(word, 1, Integer::sum);

            if(INDEX_MAPPER.containsKey(word)) continue;
            INDEX_MAPPER.put(word, index++);

            for (int row = 0; row < N; row++) {
                for(int col = 0; col < M; col++) {
                    if(map[row][col] != word) continue;

                    KEYWORD.get(INDEX_MAPPER.get(word))
                        .add(new Point.Builder<Integer, Integer>(row, col).build());
                }
            }
        }

        for(char word: id) {
            int size = KEYWORD.get(INDEX_MAPPER.get(word)).size();
            repeat = Math.min(repeat, size / count.get(word));
        }
    }
}
