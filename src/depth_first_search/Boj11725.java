package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11725번: 트리의 부모 찾기
 *
 *	@see https://www.acmicpc.net/problem/11725/
 *
 */
public class Boj11725 {
	private static ArrayList<Integer>[] tree = null;
	private static int[] isVisited = null;
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		tree = new ArrayList[N + 1];
		isVisited = new int[N + 1];
		
		for(int i = 0; i < N + 1; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			tree[from].add(to);				// 노드 양방향 연결
			tree[to].add(from);
		}
		
		dfs(1);			// 루트 노드에서부터 깊이 우선 탐색 시작
		
		StringBuilder sb = new StringBuilder();
		for(int i = 2; i < N + 1; i++) {				// 2번 노드부터 각 부모의 번호를 버퍼에 담고
			sb.append(isVisited[i]).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());				// 결과 값 한번에 출력
	}
	
	private static void dfs(int start) {		
		for(int next: tree[start]) {
			if(isVisited[next] != 0) continue;		// 부모를 찾은 경우 더 이상 탐색하지 않음
			
			isVisited[next] = start;			// 부모의 값을 배열 값으로 저장
			dfs(next);							// 다음 노드 탐색
		}
	}
}
