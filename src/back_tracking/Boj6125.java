package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6125번: Treasure Cave
 *
 *	@see https://www.acmicpc.net/problem/6125/
 *
 */
public class Boj6125 {
	private static boolean[] visit;
	private static ArrayList<Integer>[] path;
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int P = Integer.parseInt(st.nextToken());
		int NS = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		visit = new boolean[P];
		path = new ArrayList[P];
		for(int i = 0; i < P; i++) {
			path[i] = new ArrayList<>();
		}
		
		while(NS-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to1 = Integer.parseInt(st.nextToken()) - 1;
			int to2 = Integer.parseInt(st.nextToken()) - 1;
			
			path[from].add(to1);
			path[from].add(to2);
			path[to1].add(from);
			path[to2].add(from);
		}
		
		backTracking(0, T - 1);
	}
	
	private static void backTracking(int current, int target) {		
		if(current == target) {		// 보석 위치를 찾은 경우
			visit[target] = true;
			
			getResult();			// 경로 수와 경로 출력 후 종료
			return;
		}
		
		if(visit[current]) return;
		visit[current] = true;
		
		for(int next: path[current]) {
			if(visit[next]) continue;
			
			backTracking(next, target);
			visit[next] = false;
		}
	}
	
	private static void getResult() {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		
		for(int i = 0; i < visit.length; i++) {
			if(!visit[i]) continue;
			count++;
			sb.append(i + 1).append(NEW_LINE);
		}
		
		System.out.println(count);
		System.out.println(sb);
	}
}
