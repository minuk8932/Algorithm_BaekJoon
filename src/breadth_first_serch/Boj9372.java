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
			
//			int[] isVisited = null;
//			
//			for(int i = 1; i < N + 1; i++){
//				isVisited = new int[N + 1];
//				
//				isVisited[i] = 1;
//				
//				Queue<Integer> q = new LinkedList<>();
//				q.offer(i);
//				
//				while(!q.isEmpty()){
//					int current = q.poll();
//					
//					for(int next : nat[current]){
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
//			for(int i = 1; i < N + 1; i++){
//				res += isVisited[i];
//			}
			
			sb.append(N - 1).append(NEW_LINE);			// 한 나라에 도달하려면 일단은 1개의 비행기는 타야하므로 항상 N - 1번이 최소가 되는것을 알 수 있음
		}
		
		System.out.println(sb.toString());					// 결과값 한번에 출력
	}
}
