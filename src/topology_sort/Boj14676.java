package topology_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14676번: 영우는 사기꾼?
 *
 * @see https://www.acmicpc.net/problem/14676
 *
 */
public class Boj14676 {

    private static int[] degree;
    private static int[] built;
    private static List<List<Integer>> constructor;

    private static final String FAIR_PLAYER = "King-God-Emperor";
    private static final String CHEAT_PLAYER = "Lier!";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        degree = new int[N];
        built = new int[N];
        constructor = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            constructor.add(i, new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken()) - 1;
            int Y = Integer.parseInt(st.nextToken()) - 1;

            constructor.get(X).add(Y);
            degree[Y]++;
        }

        boolean flag = true;

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int building = Integer.parseInt(st.nextToken()) - 1;

            if(cmd == 1){
                if(degree[building] > 0) {
                   flag = false;
                    break;
                }

                built[building]++;
                if(built[building] != 1) continue;

                search(building, -1);
            }
            else{
                if(built[building] == 0) {
                    flag = false;
                    break;
                }

                built[building]--;
                if(built[building] != 0) continue;

                search(building, 1);
            }
        }

        System.out.println(flag ? FAIR_PLAYER: CHEAT_PLAYER);
    }

    private static void search(int target, int add) {
        for(int next: constructor.get(target)) {
            degree[next] += add;
        }
    }
}
