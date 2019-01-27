package disjoint_set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3108번: 로고
 *
 *	@see https://www.acmicpc.net/problem/3108/
 *
 */
public class Boj3108 {
	private static int[] parent;
	
	private static class Coordinate{
		int x1;
		int y1;
		int x2;
		int y2;
		
		public Coordinate(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Coordinate[] cor = new Coordinate[N + 1];
		cor[0] = new Coordinate(0, 0, 0, 0);			// 원점 초기화
		parent = new int[N + 1];
		
		init();
		
		for(int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int nx = Integer.parseInt(st.nextToken());
			int ny = Integer.parseInt(st.nextToken());
			
			cor[i] = new Coordinate(x, y, nx, ny);
		}
		
		System.out.println(findSet(N, cor));
	}
	
	private static void init() {
		for(int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
	}
	
	private static int find(int x) {
		if(parent[x] == x) return x;
		else return find(parent[x]);
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x < y) parent[y] = x;
		else parent[x] = y;
	}
	
	private static int findSet(int n, Coordinate[] c) {
		int puCount = 0;
		
		for(int a = 0; a < n + 1; a++) {
			for(int b = 0; b < n + 1; b++) {
				if(a == b) continue;
				if(isSet(c[a], c[b])) merge(a, b);		// 서로 다른 직사각형이 겹치는 경우, 한 집합으로
			}
		}
		
		boolean[] set = new boolean[n + 1];
		for(int i = 0; i < n + 1; i++) set[find(parent[i])] = true;		// 각 집합별 존재하면 참으로
		
		for(int i = 0; i < n + 1; i++) {
			if(set[i]) puCount++;			// 집합의 갯수
		}
	
		return puCount - 1;			// 원점 제외
	}
	
	private static boolean isSet(Coordinate a, Coordinate b) {
		if(a.y1 > b.y2 || b.x2 < a.x1) return false;
		if(a.x2 < b.x1 || a.y2 < b.y1) return false;
		if(a.x1 < b.x1 && b.x2 < a.x2 && a.y1 < b.y1 && b.y2 < a.y2) return false;
		if(b.x1 < a.x1 && a.x2 < b.x2 && b.y1 < a.y1 && a.y2 < b.y2) return false;

		return true;
	}
}
