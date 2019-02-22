package disjoint_set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10216번: Count Circle Groups
 *
 *	@see https://www.acmicpc.net/problem/10216/
 *
 */
public class Boj10216 {
	private static int[] parent;
	private static final String NEW_LINE = "\n";
	
	private static class Coordinate{
		int x;
		int y;
		int range;
		
		public Coordinate(int x, int y, int range) {
			this.x = x;
			this.y = y;
			this.range = range;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			Coordinate[] coor = new Coordinate[N];
			parent = new int[N];
			
			Arrays.fill(parent, -1);
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				coor[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			for(int camp1 = 0; camp1 < N; camp1++) {
				for(int camp2 = camp1 + 1; camp2 < N; camp2++) {					
					if(compRangeWithDistance(coor[camp1], coor[camp2])) {		// 통신망이 서로 닿으면
						merge(camp1, camp2);									// 같은 진영
					}
				}
			}
			
			int count = 0;
			
			for(int i = 0; i < parent.length; i++) {
				if(parent[i] < 0) count++;				// 각 진영별로 갯수를 세준다. 이때 양수를 제외한 모든 음수가 개별적인 진영이 됨
			}
			
			sb.append(count).append(NEW_LINE);
		}
	
		System.out.println(sb);
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
	
	private static boolean compRangeWithDistance(Coordinate c1, Coordinate c2) {
		int distPow = (c1.x - c2.x) * (c1.x - c2.x) + (c1.y - c2.y) * (c1.y - c2.y);
		int radar = (c1.range + c2.range) * (c1.range + c2.range);
		
		return radar >= distPow ? true : false;		// 레이더 거리 제곱과 좌표상에서 거리 비교
	}
}
