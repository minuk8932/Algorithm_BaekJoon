package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9466번: 텀 프로젝트
 *
 *	@see https://www.acmicpc.net/problem/9466/
 *
 */
public class Boj9466 {
	private static final String NEW_LINE = "\n";
	
	private static int[] term = null;
	private static boolean[] isVisited = null;
	private static int[] res = null;
	private static int cycle = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			int n = Integer.parseInt(br.readLine());
			
			isVisited = new boolean[n + 1];	
			term = new int[n + 1];
			res = new int[n + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i < n + 1; i++) term[i] = Integer.parseInt(st.nextToken());
			
			cycle = 0;
			
			for(int i = 1; i < n + 1; i++) {
				if(isVisited[i]) continue;
				
				dfs(i);
			}
			
			sb.append(n - cycle).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());		// 결과 출력
	}
	
	private static void dfs(int current) {
		if(res[current] != 0) return;
		isVisited[current] = true;
		
		int next = term[current];		// 다음 깊이를 탐색하기 위한 해당 배열의 값을 저장
		
		if(!isVisited[next]) {
			dfs(next);
		}
		else {
			if(res[next] == 0) {
				cycle++;
				
				for(int i = next; i != current; i = term[i]) cycle++;		// 사이클에 걸리는 인원의 수를 증가
			}
			
			// 이미 탐색이 된 인원의 경우 -> 이전 탐색에서 싸이클에 포함되지 않았거나, 이미 포함된 경우 이므로 배제
		}
		
		res[current] = 1;		// 탐색 완료
		return;
	}
}
