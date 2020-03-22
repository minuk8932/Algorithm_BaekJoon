package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17406번: 배열 돌리기 4
 *
 * @see https://www.acmicpc.net/problem/17406/
 *
 */
public class Boj17406 {
    private static ArrayList<Rotate> query = new ArrayList<>();
    private static ArrayList<Integer> permutation = new ArrayList<>();

    private static int N, M;
    private static boolean[] visit;

    private static class Rotate{
        int row;
        int col;
        int sub;

        public Rotate(int row, int col, int sub){
            this.row = row;
            this.col = col;
            this.sub = sub;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        visit = new boolean[K];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(K-- > 0){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int sub = Integer.parseInt(st.nextToken());

            query.add(new Rotate(row, col, sub));               // saved rotate
        }

        System.out.println(simulation(map));
    }

    private static int simulation(int[][] map){
        for(int i = 1; i <= visit.length; i++) {
            visit = new boolean[visit.length];
            backTracking(visit.length, i, 0, i);            // make permutation
        }

        int result = Integer.MAX_VALUE;

        for(int p: permutation){
            int[][] arr = reset(map);

            while(p > 0){
                int idx = p % 10;
                Rotate current = query.get(idx - 1);

                arr = procedure(arr, current);
                p /= 10;
            }

            int val = getMin(arr);
            if(result > val) result = val;              // find min
        }

        return result;
    }

    private static void backTracking(int n, int current, int count, int val){
        if(count == n - 1){
            permutation.add(val);
            return;
        }

        visit[current - 1] = true;

        for(int next = 1; next <= n; next++){
            if(visit[next - 1]) continue;

            backTracking(n, next, count + 1, val * 10 + next);
            visit[next - 1] = false;
        }
    }

    private static int[][] reset(int[][] src){
        int[][] snk = new int[N][M];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                snk[i][j] = src[i][j];
            }
        }

        return snk;
    }

    private static int[][] procedure(int[][] src, Rotate r){
        for(int i = 0; i < r.sub; i++){
            int startRow = r.row - r.sub + i;
            int startCol = r.col - r.sub + i;
            int endRow = r.row + r.sub - i;
            int endCol = r.col + r.sub - i;

            int head = src[startRow][startCol];

            for(int row = startRow; row < endRow; row++){                   // push up
                src[row][startCol] = src[row + 1][startCol];
            }

            for(int col = startCol; col < endCol; col++){                   // push left
                src[endRow][col] = src[endRow][col + 1];
            }

            for(int row = endRow; row > startRow; row--){                   // push down
                src[row][endCol] = src[row - 1][endCol];
            }

            for(int col = endCol; col > startCol; col--){                   // push right
                src[startRow][col] = src[startRow][col - 1];
            }

            src[startRow][startCol + 1] = head;
        }

        return src;
    }

    private static int getMin(int[][] arr){
        int res = Integer.MAX_VALUE;

        for(int i = 0; i < arr.length; i++){
            int sum = 0;

            for(int j = 0; j < arr[i].length; j++){
                sum += arr[i][j];
            }

            if(res > sum) res = sum;
        }

        return res;
    }
}
