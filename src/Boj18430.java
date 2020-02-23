import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj18430 {
    private static class Pair{
        int row;
        int col;

        public Pair(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] material = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++){
                material[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(makeWeapon(N, M, material));
    }

    private static int makeWeapon(int n, int m, int[][] mat){
        int max = 0;

        for (int row = 0; row < n; row++){
            for(int col = 0; col < m; col++){

            }
        }

        return max;
    }
}
