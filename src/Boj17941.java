import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17941 {
    private static int[][] tree;

    private static int N, M;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new int[N + 2][M + 2];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                tree[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());

        while(Q-- > 0){
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken()) - 1;
            int K = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            int X1 = X + R;
            int Y1 = Y + C;

            int[] add = adder(D);
            int result = 0;

            while(K-- > 0) {
                int max = 0;
                for(int row = X; row <= X1; row++) {
                    max = Math.max(judgement(row, Y, Y1), max);
                }

                X += add[0];
                X1 += add[0];
                Y += add[1];
                Y1 += add[1];

                result ^= max;
            }

            sb.append(result).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int[] adder(int d) {
        switch (d) {
            case 1:
                return new int[]{1, 0};
            case 2:
                return new int[]{-1, 0};
            case 3:
                return new int[]{0, 1};
            case 4:
                return new int[]{0, -1};
        }

        return null;
    }

    private static int judgement(int row, int y, int N) {
        int result = 0;
        for(int col = y; col <= N; col += col & -col) {
            result = Math.max(result, tree[row][col]);
        }

        return result;
    }
}
