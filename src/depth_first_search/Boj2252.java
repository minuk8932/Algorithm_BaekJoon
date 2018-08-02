package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2252번: 줄 세우기
 *
 *	@see https://www.acmicpc.net/problem/2252/
 *
 */
public class Boj2252 {
	private static ArrayList<Integer> res = new ArrayList<>();
	private static ArrayList<Integer>[] map = null;
	private static boolean[] isVisited = null;
	
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		isVisited = new boolean[N + 1];
		
		map = new ArrayList[N + 1];
		for(int i = 1; i < N + 1; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 1; i < N + 1; i++) {
			dfs(i);			 // 깊이 우선 탐색을 통한 위상 정렬
		}
		
		StringBuilder sb = new StringBuilder();
		
		int size = res.size();
		int[] tmp = new int[size];
		
		for(int i = tmp.length - 1; i >= 0; i--) {	// 구한 값들을 역순으로 배열에 다시 담아줌
			tmp[i] = res.remove(0);
		}
		
		for(int i = 0; i < tmp.length; i++) sb.append(tmp[i]).append(SPACE);	// 순서대로 버퍼에 담은 후
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 깊이 우선 탐색 메소드
	 * 	
	 */
	private static void dfs(int start) {
		if(isVisited[start]) return;
		isVisited[start] = true;
		
		for(int next: map[start]) {
			if(!isVisited[next]) dfs(next);	// 아직 방문하지 않은 노드는 이어서 탐색
		}
		
		res.add(start);		// 역으로 함수를 종료해 나오면 리스트의 노드들을 뒤에서부터 저장
	}
}
