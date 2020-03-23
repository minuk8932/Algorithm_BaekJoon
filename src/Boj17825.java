import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj17825 {
    private static int[][] board;
    private static ArrayList<Integer> move = new ArrayList<>();
    private static HashSet<Long> permutation = new HashSet<>();
    private static boolean[] visit;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            move.add(Integer.parseInt(st.nextToken()));
        }

//        for (int i = 0; i < 10; i++) {
//            visit = new boolean[10];
//            backTracking(10, i, move.get(i), 0);
//        }
        permutation.add(1234123412L);

        System.out.println(getMax());
    }

    private static int getMax(){
        int max = 0;

        for(long p: permutation){
            int[] arr = new int[10];
            int[] sum = new int[4];
            boolean[] flag = new boolean[61];
            boolean[] used = new boolean[4];
            int[] current = {0, 0, 0, 0};
            int total = 0;

            int index = (int) (p % 10);
            arr[index]++;

            // bfs

            p /= 10;
            total = sum[3];

            for (int i = 0; i < sum.length; i++){
                if(max < sum[i]) max = sum[i];
            }
        }

        return max;
    }

    private static void backTracking(int n, int current, long val, int count){
        if(count == n - 1) {
            permutation.add(val);
            return;
        }

        visit[current] = true;

        for(int next = 0; next < 10; next++){
            if(visit[next]) continue;

            backTracking(n, next, val * 10 + move.get(next), count + 1);
            visit[next] = false;
        }
    }

    private static void init(){
        board = new int[61][4];

        for(int i = 0; i < 21; i++){
            if(i < 6) board[i][0] = i * 2;
            if(i < 11) board[i][1] = i * 2;
            if(i < 16) board[i][2] = i * 2;

            board[i][3] = i * 2;
        }

        board[6][0] = 13; board[7][0] = 16; board[8][0] = 19; board[9][0] = 25; board[10][0] = 30; board[11][0] = 35; board[12][0] = 40;
        board[11][1] = 22; board[12][1] = 24; board[13][1] = 25; board[14][1] = 30; board[15][1] = 35; board[16][1] = 40;
        board[16][2] = 28; board[17][2] = 27; board[18][2] = 26; board[19][2] = 25; board[20][2] = 30; board[21][2] = 35; board[22][2] = 40;
    }
}
