package disjoint_set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1976번: 여행 가자
 *
 *	@see https://www.acmicpc.net/problem/1976/
 *
 */
public class Boj1976 {
	private static int[] set = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;

		int[][] map = new int[N + 1][N + 1];
		set = new int[N + 1];
		for(int i = 1; i < N + 1; i++) set[i] = i;
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j < N + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) merge(i, j);				// 도달 할 수 있는 위치는 합집합 처리
			}
		}
		
		int[] path = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) path[i] = Integer.parseInt(st.nextToken());
		
		boolean isCorrect = true;
		for(int i = 0; i < M - 1; i++) {
			if(find(set[path[i]]) != find(set[path[i + 1]])) {		// 서로 같은 parent를 공유 하는지?
				isCorrect = false;
				
				break;
			}
		}
		
		System.out.println(isCorrect ? "YES" : "NO");		// 가능한 여행경로이면 YES 아니면, NO
	}
	
	/**
	 * find 메소드를 통해 parent 값을 찾아 합치는 merge 메소드
	 * 
	 */
	private static void merge(int u, int v) {
		u = find(u);
		v = find(v);
		
		set[u] = v;
	}
	
	/**
	 * 같은 값을 공유하면 u를, 아니면 find를 재귀호출하여 동일 값을 찾아줌
	 * 
	 */
	private static int find(int u) {
		if(u == set[u]) {
			return u;
		}
		
		return set[u] = find(set[u]);
	}
}
