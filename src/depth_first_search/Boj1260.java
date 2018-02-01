package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1260번 : BFS 와 DFS
 *
 *	@see https://www.acmicpc.net/problem/1260
 *
 */

public class Boj1260 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] graph = new ArrayList[N + 1];		// 인접 리스트 선언
		
		boolean[] isVisited = new boolean[N + 1];
		
		for(int i = 0; i < N + 1; i++){											// 인접 리스트 내부 초기화 
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), SPACE);
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph[from].add(to);
			graph[to].add(from);
		}
		br.close();
		
		for(int i = 1; i < N; i++){												// 인접 리스트 입력 값 정렬
			Collections.sort(graph[i]);
		}
		
		dfs(V, graph, isVisited);												// dfs 실행 메소드
		
		isVisited = new boolean[N + 1];									// 방문 확인 배열 초기화
		sb.append(NEW_LINE);
		
		bfs(V, graph, isVisited);												// bfs 실행 메소드
		
		System.out.println(sb.toString());								// 버퍼에 담긴 노드 방문 순서를 한번에 출력
	}
	
	/**
	 * 
	 * @param current : 시작 값
	 * @param graph : 인접 리스트
	 * @param isVisited : 방문 여부 체크 배열
	 * 
	 * 인접 리스트 + 재귀함수
	 * 
	 */
	private static void dfs(int current, ArrayList<Integer>[] graph, boolean[] isVisited){
		isVisited[current] = true;					// 현재 방문한 노드에 해당하는 방문 체크 배열의 값: true(방문함)
		sb.append(current).append(SPACE);	// 현재 방문한 노드값을 버퍼에 담아둠
		
		for(int next : graph[current]){			// next 변수에 현재 방문한 노드에 해당하는 리스트에 담겨있는 값들을 하나씩 할당
			if(!isVisited[next]){						// 즉, 그 다음 행선지를 정해주는 반복문
				dfs(next, graph, isVisited);			// 다음 방문할 노드 값으로 바꾸어 재귀 함수 실행
			}
		}
	}
	
	/**
	 * 
	 * @param current : 시작 값
	 * @param graph : 인접 리스트
	 * @param isVisited : 방문 여부 체크 배열
	 * 
	 * 인접리스트 + 큐
	 * 
	 */
	private static void bfs(int current, ArrayList<Integer>[] graph, boolean[] isVisited){
		Queue<Integer> queue = new LinkedList<>();
		queue.add(current);									// 큐에 현재(시작) 노드 값을 입력
		isVisited[current] = true;							// 현재(시작) 노드값에 해당하는 방문 체크 배열의 값: true(방문함)
		
		while(!queue.isEmpty()){								// 큐 내부가 빌 때 까지 실행
			current = queue.poll();							// 큐 가장 앞쪽에 있는 값을 뽑아 현재 노드에 해당하는 값에 할당 : 즉 현재 노드, 그리고 그 노드에 다음에 있는 노드 모두를 하나씩 탐색
			sb.append(current).append(SPACE);		// 현재 방문한 노드의 값을 버퍼에 입력
			
			for(int next : graph[current]){				// next 변수에 현재 방문한 노드에 해당하는 리스트에 담겨있는 값들을 할당
				if(!isVisited[next]){							// 즉, 다음 갈 수 있는 노드들에 대한 정보를 하나씩 할당
					isVisited[next] = true;					// 다음 방문할 노드에 해당하는 방문 체크 배열의 값: true(방문함)
					queue.add(next);							// 다음 방문할 노드(리스트)를 큐에 담아줌
				}
			}
		}
	}
}
