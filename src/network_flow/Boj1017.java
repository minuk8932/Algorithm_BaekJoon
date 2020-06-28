package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1017번: 소수 쌍
 *
 * @see https://www.acmicpc.net/problem/1017/
 *
 */
public class Boj1017 {
    private static int[] aMatch;
    private static int[] bMatch;
    private static boolean[][] sumPrime = new boolean[2_001][2_001];

    private static int N;

    private static boolean[] visited;
    private static boolean[] prime = new boolean[2_001];

    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] values = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        eratosthenesSieve(values);
        System.out.println(bipartiteMatch(values));
    }

    private static void eratosthenesSieve(int[] val) {
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        int loop = (int) Math.sqrt(prime.length) + 1;
        for(int i = 2; i < loop; i++) {
            if(!prime[i]) continue;

            for(int j = i + i; j < prime.length; j += i) {
                prime[j] = false;
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(i == j) continue;
                if(prime[val[i] + val[j]]) sumPrime[i][j] = true;       // sum is prime?
            }
        }
    }

    private static int recursion(int prime) {
        if(visited[prime]) return 0;
        visited[prime] = true;

        for(int i = 0; i < N; i++){
            if(!sumPrime[prime][i]) continue;

            if(bMatch[i] == -1 || recursion(bMatch[i]) == 1){
                bMatch[i] = prime;
                aMatch[prime] = i;
                return 1;
            }
        }

        return 0;
    }

    private static String bipartiteMatch(int[] val) {
        ArrayList<Integer> results = new ArrayList<>();
        aMatch = new int[N];
        bMatch = new int[N];

        for(int i = 0; i < N; i++){
            if(!sumPrime[0][i]) continue;
            Arrays.fill(bMatch, -1);
            int size = 0;

            for(int j = 1; j < N; j++){
                visited = new boolean[N];
                aMatch[0] = i;
                aMatch[i] = 0;

                visited[0] = visited[i] = true;
                bMatch[i] = 0;
                bMatch[0] = i;
                size += recursion(j);
            }

            if(size == N - 2) results.add(val[i]);      // matching 1th index value
        }

        if(results.size() == 0) return "-1";            // any data sum is prime with 1th index

        StringBuilder sb = new StringBuilder();
        Collections.sort(results);

        for(int re: results) {
            sb.append(re).append(SPACE);
        }

        return sb.toString();
    }
}
