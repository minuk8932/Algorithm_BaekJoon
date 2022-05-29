package sort;

import common.Node;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25167번: 아리의 이상한 채점
 *
 * @see https://www.acmicpc.net/problem/25167
 *
 */
public class Boj25167 {

    private static final String COLON = ":";
    private static final String S = "solve";
    private static final String NEW_LINE = "\n";

    private static HashMap<String, Integer> participants = new HashMap<>();
    private static HashMap<Integer, String> decoder = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int[][] tried = new int[N][M];
        boolean[][] over = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            Arrays.fill(tried[i], -1);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            String name = st.nextToken();
            participants.put(name, i);
            decoder.put(i, name);
        }

        while(P-- > 0) {
            st = new StringTokenizer(br.readLine());
            int pNum = Integer.parseInt(st.nextToken()) - 1;

            StringTokenizer colon = new StringTokenizer(st.nextToken(), COLON);
            int times = Integer.parseInt(colon.nextToken()) * 60
                + Integer.parseInt(colon.nextToken());

            String name = st.nextToken();
            boolean sol = st.nextToken().equals(S);

            if(over[pNum][participants.get(name)]) continue;

            if(tried[pNum][participants.get(name)] == -1) {
                if(sol){
                    tried[pNum][participants.get(name)] = -2;
                    over[pNum][participants.get(name)] = true;
                }
                else {
                    tried[pNum][participants.get(name)] = times;
                }
            }
            else {
                if(tried[pNum][participants.get(name)] != -2) {
                    if(sol) {
                        tried[pNum][participants.get(name)]
                            = times - tried[pNum][participants.get(name)];
                        over[pNum][participants.get(name)] = true;
                    }
                }
            }
        }

        System.out.println(ranking(M, tried, over));
    }

    private static String ranking(int m, int[][] tried, boolean[][] over) {
        int[] result = new int[m];

        for(int i = 0; i < tried.length; i++) {
            ArrayList<Node<Integer, String>> nodes = new ArrayList<>();

            for(int j = 0; j < m; j++) {
                nodes.add(new Node.Builder(j).another(tried[i][j]).cost(decoder.get(j)).build());
            }

            Collections.sort(nodes, Comparator
                .comparingInt(Node<Integer, String>::getAnother)
                .thenComparing(Node::getCost)
            );

            int score = 1;
            for(Node<Integer, String> n: nodes) {
                if(tried[i][n.getNode()] > -1) {
                    if(over[i][n.getNode()]) result[n.getNode()] += score++;
                    else result[n.getNode()] += m;
                }
                else {
                    result[n.getNode()] += m + 1;
                }
            }
        }

        PriorityQueue<Node<String, Integer>> pq = new PriorityQueue<>(
            Comparator.comparingInt(Node<String, Integer>::getCost)
                .thenComparing(Node::getNode)
        );

        for(int i = 0; i < result.length; i++) {
            pq.offer(new Node.Builder(decoder.get(i)).cost(result[i]).build());
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            sb.append(pq.poll().getNode()).append(NEW_LINE);
        }

        return sb.toString();
    }
}
