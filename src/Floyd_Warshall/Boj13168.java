package Floyd_Warshall;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13168번: 내일로 여행
 *
 * @see https://www.acmicpc.net/problem/13168
 *
 */
public class Boj13168 {
    private static HashMap<String, Integer> city = new HashMap<>();
    private static double[][] free;
    private static double[][] pay;
    private static final double INF = 1_000_000_000;

    private static ArrayList<Integer> must = new ArrayList<>();

    private static final String[] DISCOUNT = {"S-Train", "V-Train"};
    private static final String[] FREE = {"ITX-Saemaeul", "ITX-Cheongchun", "Mugunghwa"};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        free = new double[N][N];
        pay = new double[N][N];
        init();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            city.put(st.nextToken(), i);
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while(M-- > 0) {
            must.add(city.get(st.nextToken()));
        }

        int K = Integer.parseInt(br.readLine());
        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            String method = st.nextToken();
            int city1 = city.get(st.nextToken());
            int city2 = city.get(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pay[city1][city2] = pay[city2][city1] = Math.min(pay[city1][city2], cost);          // make cost buy ticket or not

            if(method.equals(FREE[0]) || method.equals(FREE[1]) || method.equals(FREE[2])) free[city1][city2] = free[city2][city1] = 0;
            else if(method.equals(DISCOUNT[0]) || method.equals(DISCOUNT[1])) free[city1][city2] = free[city2][city1] = Math.min(cost / 2.0, free[city1][city2]);
            else free[city1][city2] = free[city2][city1] = Math.min(cost, free[city1][city2]);
        }

        System.out.println(floydWashall(R));
    }

    private static String floydWashall(int ticket) {
        for (int v = 0; v < pay.length; v++) {
            for (int s = 0; s < pay.length; s++) {
                for (int e = 0; e < pay.length; e++) {
                    if(pay[s][e] > pay[s][v] + pay[v][e]) pay[s][e] = pay[s][v] + pay[v][e];
                    if(free[s][e] > free[s][v] + free[v][e]) free[s][e] = free[s][v] + free[v][e];
                }
            }
        }

        int[] total = new int[2];
        int size = must.size();

        for (int i = 0; i < size - 1; i++) {                    // find total cost
            int from = must.get(i);
            int to = must.get(i + 1);

            total[0] += pay[from][to];
            total[1] += free[from][to];
        }

        return total[1] + ticket < total[0] ? "Yes": "No";      // more cheaper ?
    }

    private static void init() {
        for(int i = 0; i < pay.length; i++) {
            Arrays.fill(pay[i], INF);
            Arrays.fill(free[i], INF);
            pay[i][i] = free[i][i] = 0;
        }
    }
}
