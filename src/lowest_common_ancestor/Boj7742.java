package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 7742번: Railway, Java(OOM), C++(AC)
 *
 * @see https://www.acmicpc.net/problem/7742
 *
 */
public class Boj7742 {
    private static ArrayList<Node>[] path;
    private static int[][] parent;
    private static int[] depth;
    private static int[] dist;

    private static final String NEW_LINE = "\n";

    private static class Node{
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        init(N);

        int loop = N - 1;
        while(loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            path[node1].add(new Node(node2, cost));
            path[node2].add(new Node(node1, cost));
        }

        recursion(0, 0);
        connection(N);

        StringBuilder sb = new StringBuilder();

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            sb.append(dist[x] + dist[y] - dist[lca(x, y)] * 2).append(NEW_LINE);        // total cost
        }

        System.out.println(sb.toString());
    }

    private static int lca(int x, int y) {
        if(depth[x] > depth[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--) {
            int jump = 1 << i;
            if (depth[y] - depth[x] >= jump) y = parent[y][i];
        }

        if(x == y) return x;

        for(int i = 20; i >= 0; i--) {
            if(parent[x][i] == parent[y][i]) continue;
            x = parent[x][i];
            y = parent[y][i];
        }

        return parent[x][0];
    }

    private static void init(int n) {
        path = new ArrayList[n];
        parent = new int[n][21];
        dist = new int[n];
        depth = new int[n];

        for(int i = 0; i < n; i++) {
            path[i] = new ArrayList<>();
            depth[i] = -1;
        }
    }

    private static void recursion(int current, int deep) {
        depth[current] = deep;

        for(Node next: path[current]) {
            if(depth[next.node] != -1) continue;

            parent[next.node][0] = current;
            dist[next.node] = dist[current] + next.cost;        // make prefix cost
            recursion(next.node, deep + 1);
        }
    }

    private static void connection(int n) {
        for(int p = 1; p < 21; p++) {
            for(int cur = 0; cur < n; cur++) {
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }
}

/*
C++

#include <cstdio>
#include <cstring>
#include <vector>

using namespace std;
const int SIZE = 200000;
typedef pair<int, int> node;

int N;
int parent[SIZE][21], depth[SIZE], dist[SIZE];
vector<node> path[SIZE];

void recursion(int current, int deep){
	depth[current] = deep;

	for(auto &next: path[current]){
		if(depth[next.first] != -1) continue;

		parent[next.first][0] = current;
		dist[next.first] = dist[current] + next.second;

		recursion(next.first, deep + 1);
	}
}

void connection(){
    for(int p = 1; p < 21; p++) {
		for(int cur = 0; cur < N; cur++) {
				parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
		}
	}
}

int lca(int x, int y) {
	if(depth[x] > depth[y]) {
        int tmp = x;
		x = y;
		y = tmp;
	}

	for(int i = 20; i >= 0; i--) {
    	int jump = 1 << i;
        if (depth[y] - depth[x] >= jump) y = parent[y][i];
    }

    if(x == y) return x;

    for(int i = 20; i >= 0; i--) {
    	if(parent[x][i] == parent[y][i]) continue;
        x = parent[x][i];
        y = parent[y][i];
	}

    return parent[x][0];
}

int main(){
	int Q;
	scanf("%d %d", &N, &Q);

	int loop = N - 1;
	while(loop-- > 0) {
		int node1, node2, cost;
		scanf("%d %d %d", &node1, &node2, &cost);

		node1--;
		node2--;

		path[node1].push_back(node(node2, cost));
		path[node2].push_back(node(node1, cost));
	}

	memset(depth, -1, sizeof(depth));

	dist[0] = 0;
	recursion(0, 0);
    connection();

	while(Q-- > 0) {
		int x, y;
		scanf("%d %d", &x, &y);

		x--;
		y--;

		printf("%d\n", dist[x] + dist[y] - dist[lca(x, y)] * 2);
	}
}
 */