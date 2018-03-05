package breadth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5014 : 스타트링크
 *
 *	@see https://www.acmicpc.net/problem/5014
 *
 */
public class Boj5014 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int F = Integer.parseInt(st.nextToken());											// 제일 꼭대기 층
		int S = Integer.parseInt(st.nextToken());											// 시작 층
		int G = Integer.parseInt(st.nextToken());											// 목표 층
		int U = Integer.parseInt(st.nextToken());											// 한번에 올라갈 수 있는 층의 수
		int D = Integer.parseInt(st.nextToken());											// 한번에 내려갈 수 있는 층의 수
		
		if(S == G){																						// 시작 층과 목표 층이 같다면 0 출력 
			System.out.println(0);
		}
		else{
		
			int[] DIRECTIONS = {U, -D};															// 올라가는 수와 내려가는 수를 배열에 담아줌
			
			int[] isVisited = new int[F + 1];														// 방문 했는지 체크
			
			int res = Integer.MAX_VALUE;
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(S);																					// 시작층을 큐에 담고
			
			isVisited[S] = 1;																			// 방문했다고 체크
			
			while(!q.isEmpty()){
				int current = q.poll();
				
				for(int DIRECTION : DIRECTIONS){
					int next = current + DIRECTION;
					
					if(next > 0 && next < F + 1){
						if(isVisited[next] == 0){
							isVisited[next] = isVisited[current] + 1;						// 유효한 층에 도착했을때 해당 방문 배열에 이전 유효 층 방문 배열값 + 1을 해줌
							
							if(next == G){																// 목표층에 도착했다면
								if(res > isVisited[next]){											// isVisited 값 중 최솟값을 res에 담아줌
									res = isVisited[next];
								}
							}
							
							q.offer(next);
						}
					}
				}
			}
			
			if(res == Integer.MAX_VALUE){													// 만약 res가 갱신이 안되었을 때, 즉 목표 층에 도착하지 못함
				System.out.println("use the stairs");
			}
			else{
				System.out.println(res - 1);													// 목표층에 도착한 경우 처음 시작층에서 더한 1을 뺀 후 결과값 출력
			}
		}
	}
}
