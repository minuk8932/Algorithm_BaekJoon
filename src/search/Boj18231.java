package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 18231번: 파괴된 도시
 *
 * @see https://www.acmicpc.net/problem/18231/
 *
 */
public class Boj18231 {
    private static ArrayList<Integer>[] graph;
    private static ArrayList<Integer> result = new ArrayList<>();
    private static ArrayList<Integer> list = new ArrayList<>();
    private static boolean[] destroyed;
    private static int K;

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        for(int i = 0; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        while (M-- > 0){
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken()) - 1;
            int V = Integer.parseInt(st.nextToken()) - 1;

            graph[U].add(V);
            graph[V].add(U);
        }

        K = Integer.parseInt(br.readLine());
        destroied = new boolean[N];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < K; i++) {
            int index = Integer.parseInt(st.nextToken()) - 1;
            destroied[index] = true;
            list.add(index);
        }

        System.out.println(search());
    }

    private static String search() {
        for(int target: list) {
            boolean flag = true;

            for(int adj: graph[target]) {
                if(!(flag = destroyed[adj])) break;
            }

            if(flag) result.add(target);                    // bomb is dropped ?
        }

        int count = judgement();                            // check count validation

        StringBuilder sb = new StringBuilder();
        if(result.size() == 0 || count != K) return sb.append(-1).toString();

        sb.append(result.size()).append(NEW_LINE);

        for(int city: result) {
            sb.append(city + 1).append(SPACE);
        }

        return sb.toString();
    }

    private static int judgement() {
        Set size = new HashSet<>();

        for(int target: result) {
            for(int adj: graph[target]) {
                if(destroyed[adj]) size.add(adj);
            }

            size.add(target);
        }

        return size.size();
    }
}
