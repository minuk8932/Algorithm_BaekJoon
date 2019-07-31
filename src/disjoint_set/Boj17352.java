package disjoint_set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17352번: 여러분의 다리가 되어 드리겠습니다!
 *
 *	@see https://www.acmicpc.net/problem/17352/
 *
 */
public class Boj17352 {
	private static int[] parent;
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		init(N);
		
		int loop = N - 2;
		while(loop-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			merge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		}
		
		System.out.println(getBridge(N));
	}
	
	private static void init(int n) {
		parent = new int[n];
		
		for(int i = 0; i < n; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
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
	
	private static String getBridge(int n) {
		StringBuilder sb = new StringBuilder();
		int a = getParent(0);
		int x = -1;
		
		for(int i = 1; i < n; i++) {
			x = getParent(i);

			if(x != a) {				// 1과 부모가 다른 경우 서로 엮어야 하는 섬
				x = i + 1;
				break;
			}
		}
		
		return sb.append(1).append(SPACE).append(x).toString();
	}
	
	private static int getParent(int idx) {
		return find(idx) < 0 ? idx + 1: find(idx);
	}
}
