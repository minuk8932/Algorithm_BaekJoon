package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13023번: ABCDE
 *
 *	@see https://www.acmicpc.net/problem/13023/
 *
 */
public class Boj13023 {
	private static boolean[] isVisited;
	private static boolean result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] relation = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			relation[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			relation[from].add(to);
			relation[to].add(from);
		}

		for(int i = 0; i < N; i++) {
			isVisited = new boolean[N];
			isVisited[i] = true;
			
			backTracking(relation, i, 0);
			if(result) break;				// 관계가 존재하면 바로 종료
		}
		
		System.out.println(result ? 1 : 0);
	}
	
	private static void backTracking(ArrayList<Integer>[] arr, int current, int depth) {
		if(depth == 4) {		// 관계의 수가 4개 -> 종료 조건
			result = true;
			return;
		}
		
		for(int next: arr[current]) {
			if(isVisited[next]) continue;
			isVisited[next] = true;
			
			backTracking(arr, next, depth + 1);
			isVisited[next] = false;				// 백 트래킹
		}
	}
}
