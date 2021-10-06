package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 5081번: Constellations
 *
 * @see https://www.acmicpc.net/problem/5081
 *
 */
public class Boj5081 {

    private static int[] distance;
    private static int[] parent;

    private static final String SKY = "Sky ";
    private static final String CONTAIN = " contains ";
    private static final String CONSTS = " constellations.\n";

    private static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = 1;

        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            parent = new int[N];
            distance = new int[N];
            Arrays.fill(parent, -1);
            Arrays.fill(distance, Integer.MAX_VALUE);

            Coordinate[] coors = new Coordinate[N];
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                coors[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            makeGraph(coors);
            sb.append(SKY).append(T++).append(CONTAIN).append(counting()).append(CONSTS);
        }

        System.out.println(sb.toString());
    }

    private static int counting() {
        int count = 0;

        for(int p: parent) {
            if(p >= 0) continue;
            count++;
        }

        return count;
    }

    private static void makeGraph(Coordinate[] c) {
        for(int node1 = 0; node1 < c.length; node1++){
            for(int node2 = 0; node2 < c.length; node2++) {
                if(node1 == node2) continue;
                int cost = pow(c[node1], c[node2]);

                distance[node1] = Math.min(distance[node1], cost);
            }
        }

        for(int node1 = 0; node1 < c.length; node1++){
            for(int node2 = 0; node2 < c.length; node2++) {
                if(node1 == node2) continue;
                int cost = pow(c[node1], c[node2]);

                if(distance[node1] != cost) continue;
                merge(node1, node2);
            }
        }
    }

    private static int pow(Coordinate c1, Coordinate c2) {
        return (c1.x - c2.x) * (c1.x - c2.x) + (c1.y - c2.y) * (c1.y - c2.y);
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }
}
