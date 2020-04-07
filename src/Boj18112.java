import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author exponential-e
 * 백준 18112번: 이진수 게임
 *
 * @see https://www.acmicpc.net/problem/18112/
 *
 */
public class Boj18112 {
    private static int[] visit = new int[3_000];
    private static final int[] XOR = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int src = 0;
        int t = 0;
        char[] input = br.readLine().toCharArray();

        for(int x = input.length - 1; x >= 0; x--){
            src += Math.pow(2, t) * (input[x] - '0');
            t++;
        }

        int snk = 0;
        t = 0;
        input = br.readLine().toCharArray();

        for(int x = input.length - 1; x >= 0; x--){             // binary to int
            snk += Math.pow(2, t) * (input[x] - '0');
            t++;
        }

        System.out.println(bfs(src, snk));
    }

    private static int bfs(int src, int snk){
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);

        visit[src] = 1;

        while(!q.isEmpty()){
            int current = q.poll();

            ArrayList<Integer> list = new ArrayList<>();
            if(current != 0) list.add(current - 1);
            list.add(current + 1);

            int S = 1;
            while(S < current) S <<= 1;
            if(S != current) S /= 2;

            for(final int X: XOR){
                if(X >= S) break;                       // except head number
                list.add(current ^ X);
            }

            for(int next: list){
                if(next >= visit.length) continue;
                if(visit[next] != 0) continue;
                visit[next] = visit[current] + 1;

                if(next == snk) break;
                q.offer(next);
            }
        }

        return visit[snk] - 1;
    }
}
