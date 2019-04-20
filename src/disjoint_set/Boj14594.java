package disjoint_set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14594번: 동방 프로젝트 (small)
 *
 *	@see https://www.acmicpc.net/problem/14594/
 *
 */
public class Boj14594 {
	private static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		init(N);
		
		while(M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			for(int i = x; i < y; i++) {		// 부숴진 동방을 하나로
				merge(i, i + 1);
			}
		}
		
		System.out.println(getRooms(N));
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
	
	private static int getRooms(int n) {
		int count = 0;
		
		for(int i = 0; i < n; i++) {		// 음수를 갖는 동방은 서로 분리된 동방
			if(parent[i] < 0) count++;
		}
		
		return count;
	}
}
