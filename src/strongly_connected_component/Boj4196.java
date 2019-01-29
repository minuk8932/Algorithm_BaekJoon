package strongly_connected_component;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 4196번: 도미노
 *
 *	@see https://www.acmicpc.net/problem/4196/
 *
 */
public class Boj4196 {
	private static final String NEW_LINE = "\n";
	
	private static ArrayDeque<Integer> stack = new ArrayDeque<>();
	private static boolean[] isVisited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer>[] map = new ArrayList[N + 1];			
			for(int i = 0; i < N + 1; i++) {
				map[i] = new ArrayList<>();
			}
			
			while(M-- > 0) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				map[x].add(y);
			}
			
			isVisited = new boolean[N + 1];
			for(int i = 1; i < N + 1; i++) {
				if(isVisited[i]) continue;
				
				backTracking(map, i, true);
				stack.push(i);						// 탐색의 역순으로 스택에 저장
			}
			
			int scc = 0;
			isVisited = new boolean[N + 1];
			
			while(!stack.isEmpty()) {
				int start = stack.pop();
				if(isVisited[start]) continue;
			
				backTracking(map, start, false);
				scc++;								// 넘어뜨려야 하는 최소 갯수
			}
			
			sb.append(scc).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static void backTracking(ArrayList<Integer>[] arr, int current, boolean save) {	
		if(isVisited[current]) return;
		isVisited[current] = true;
		
		for(int next: arr[current]) {
			if(isVisited[next]) continue;
			
			backTracking(arr, next, save);		// 함수 반환시 마다 해당 노드를 스택에 저장
			if(save) stack.push(next);
		}
	}
}
