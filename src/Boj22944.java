import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

public class Boj22944 {

    private static int N;
    private static int size;

    private static Point start;
    private static Point end;

    private static List<Integer> permutation = new ArrayList<>();
    private static Map<Integer, Point> umbrellas = new HashMap<>();
    private static boolean[] used;

    private static final char START = 'S';
    private static final char END = 'E';
    private static final char UMBRELLA = 'U';

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
        N = PARSE.apply(st.nextToken());
        int H = PARSE.apply(st.nextToken());
        int D = PARSE.apply(st.nextToken());

        char[][] map = ARRY_2D.apply(N);
        int idx = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for(int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);

                if(map[i][j] == START) start = new Point(i, j);
                if(map[i][j] == END) end = new Point(i, j);
                if(map[i][j] == UMBRELLA) umbrellas.put(size++, new Point(i, j));
            }
        }

        for(int i = 0; i < size; i++) {
            used = new boolean[size];
            recursion(i, i, 0);
        }

        System.out.println(search(H, D, map));
    }

    private static void recursion(int current, int value, int count) {
        if(count == size - 1) {
            permutation.add(value);
            return;
        }

        used[current] = true;

        for(int next = 0; next < size; next++) {
            if(used[next]) continue;

            recursion(next, value * 10 + next, count + 1);
            used[next] = false;
        }
    }

    private static int search(int health, int duration, char[][] map) {
        return 0;
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;
    private static final Function<Integer, char[][]> ARRY_2D = N -> new char[N][N];

}
