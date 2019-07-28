package disjoint_set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 12767번: Ceiling Function
 *
 *	@see https://www.acmicpc.net/problem/12767
 *
 */
public class Boj12767 {
	private static int[][] tree;
	private static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		init(N, K);
		
		int[][] nodes = new int[N][K];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < K; j++) {
				nodes[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(makeTree(N, K, nodes));
	}
	
	private static void init(int n, int k) {
		int S = (int) Math.pow(2, 20);
		
		tree = new int[n][S * 2 + 1];
		parent = new int[n];
		
		for(int i = 0; i < n; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {				// disjoint set
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
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
	
	private static int makeTree(int n, int k, int[][] arr) {
		for(int i = 0; i < n; i++) {
			tree[i][1] = arr[i][0];
			
			for(int j = 1; j < k; j++) {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(1);
				int t = 1;

				for(int x = 0; x < list.size(); x++) {
					if(tree[i][t] == 0) {
						tree[i][t] = arr[i][j];

						list.add(t);
						break;
					}

					if(arr[i][j] > tree[i][t]) {		// 비교하는 수보다 크면 idx * 2 + 1번 트리로
						t = list.get(x) * 2 + 1;
						list.add(t);
					}
					else {								// 작으면 idx * 2번 트리
						t = list.get(x) * 2;
						list.add(t);
					}
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = i + 1; j < n; j++) {			// 모양이 같은 트리끼리 집합
				if(getSet(i, j)) merge(i, j);
			}
		}
		
		return getCount(n);
	}
	
	private static int getCount(int N) {
		int count = 0;
		for(int i = 0; i < N; i++) {					// 집합의 갯수
			if(parent[i] < 0) count++;
		}
		
		return count;
	}
	
	private static boolean getSet(int i, int j) {
		for(int x = 1; x < tree[i].length; x++) {
			if((tree[i][x] != 0 && tree[j][x] == 0) || (tree[i][x] == 0 && tree[j][x] != 0)) return false;		// 모양이 다른 경우
		}
		
		return true;
	}
}
