package breadth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9372번 : 상근이의 여행
 *
 *	@https://www.acmicpc.net/problem/9382
 *
 */
public class Boj9372 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer>[] nat = new ArrayList[N + 1];
			
			for(int i = 0; i < N + 1; i++){
				nat[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < M; i++){											// 왕복 비행기 입력
				st = new StringTokenizer(br.readLine(), SPACE);
				int air1 = Integer.parseInt(st.nextToken());
				int air2 = Integer.parseInt(st.nextToken());
				
				nat[air1].add(air2);
				nat[air2].add(air1);
			}
			
			sb.append(N - 1).append(NEW_LINE);			// 한 나라에 도달하려면 일단은 1개의 비행기는 타야하므로 항상 N - 1번이 최소가 되는것을 알 수 있음
		}
		
		System.out.println(sb.toString());					// 결과값 한번에 출력
	}
}

// BFS를 이용한 풀이

//	public class Main {
//	private static final String SPACE = " ";
//	private static final String NEW_LINE = "\n";
//	
//	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	버퍼를 통한 값 입력
//		int T = Integer.parseInt(br.readLine());
//		
//		StringBuilder sb = new StringBuilder();
//		
//		while(T-- > 0){
//			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
//			int N = Integer.parseInt(st.nextToken());
//			int M = Integer.parseInt(st.nextToken());
//			
//			ArrayList<Integer>[] nat = new ArrayList[N + 1];
//			
//			for(int i = 0; i < N + 1; i++){
//				nat[i] = new ArrayList<>();
//			}
//			
//			for(int i = 0; i < M; i++){
//				st = new StringTokenizer(br.readLine(), SPACE);
//				int air1 = Integer.parseInt(st.nextToken());
//				int air2 = Integer.parseInt(st.nextToken());
//				
//				nat[air1].add(air2);											// 인접리스트에 양방향으로 경로를 담음
//				nat[air2].add(air1);
//			}
//			
//			int[] isVisited = null;
//			
//			for(int i = 1; i < N + 1; i++){
//				isVisited = new int[N + 1];
//				
//				isVisited[i] = 1;
//				
//				Queue<Integer> q = new LinkedList<>();				// BFS 알고리즘 실행
//				q.offer(i);
//				
//				while(!q.isEmpty()){
//					int current = q.poll();
//					
//					for(int next : nat[current]){							// 현재 값에 대한배열을 다음이라는 변수에 담아서 반복문 수행
//						if(isVisited[next] == 0){
//							isVisited[next]++;
//							
//							q.offer(next);
//						}
//					}
//				}		
//			}
//			
//			int res = 0;
//			
//			for(int i = 1; i < N + 1; i++){										// 방문 배열의 합을 구하고
//				res += isVisited[i];
//			}
//			
//			sb.append(res - 1).append(NEW_LINE);						// 처음 방문 횟수 1을 제외한 나머지 값을 결과 버퍼에 담고
//		}
//		
//		System.out.println(sb.toString());								// 테스트 케이스 별로 한번에 출력
//	}
//}