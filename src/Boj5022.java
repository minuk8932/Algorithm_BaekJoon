import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5022 {
    private static class Point{
        int row;
        int col;

        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Point[] A = new Point[2];
        for(int i = 0; i < N; i++){
            A[i] = new Point(Integer.parseInt(st.nextToken())- 1, Integer.parseInt(st.nextToken()) - 1);
        }

        Point[] B = new Point[2];
        for(int i = 0; i < N; i++){
            B[i] = new Point(Integer.parseInt(st.nextToken())- 1, Integer.parseInt(st.nextToken()) - 1);
        }


    }
}
