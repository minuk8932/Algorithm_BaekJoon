package dijkstra;

import common.Coordinate;
import common.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 14431번: 소수마을
 *
 * @see https://www.acmicpc.net/problem/14431
 *
 */
public class Boj14431 {

    private static List<Node>[] path;
    private static List<Coordinate> input = new ArrayList<>();

    private static boolean[] prime = new boolean[100_000];

    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        input.add(new Coordinate.Builder(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())).build());
        input.add(new Coordinate.Builder(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())).build());

        ertosThenes();

        int N = Integer.parseInt(br.readLine());
        path = new ArrayList[N + 2];
        for(int i = 0; i < path.length; i++) {
            path[i] = new ArrayList<>();
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            input.add(new Coordinate.Builder(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())).build());
        }

        reset();
        System.out.println(dijkstra(N));
    }

    private static int dijkstra(int n) {
        int[] dist = new int[n + 2];
        Arrays.fill(dist, INF);

        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node<Integer>::getCost));
        pq.offer(new Node.Builder(0).cost(0).build());

        dist[0] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            for(Node<Integer> next: path[current.getNode()]) {
                if(dist[next.getNode()] <= dist[current.getNode()] + next.getCost()) continue;
                dist[next.getNode()] = dist[current.getNode()] + next.getCost();

                pq.offer(new Node.Builder(next.getNode()).cost(dist[next.getNode()]).build());
            }
        }

        return dist[1] == INF ? -1: dist[1];
    }

    private static void ertosThenes() {
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        for(int i = 2; i * i < prime.length; i++) {
            if (!prime[i]) continue;

            for(int j = i + i; j < prime.length; j += i) {
                prime[j] = false;
            }
        }
    }

    private static void reset() {
        int size = input.size();

        for(int i = 0; i < size; i++) {
            for(int j = i + 1; j < size; j++) {
                int distance = euclideanDistance(input.get(i), input.get(j));
                if(!prime[distance]) continue;

                path[i].add(new Node.Builder(j).cost(distance).build());
                path[j].add(new Node.Builder(i).cost(distance).build());
            }
        }
    }

    private static int euclideanDistance(Coordinate<Integer, Integer> c1, Coordinate<Integer, Integer> c2) {
        int xdiff = c1.getX() - c2.getX();
        int ydiff = c1.getY() - c2.getY();

        return (int) Math.floor(Math.sqrt(xdiff * xdiff + ydiff * ydiff));
    }
}
