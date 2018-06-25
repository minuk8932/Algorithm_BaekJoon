package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12761번 : 돌다리
 *
 *	@see https://www.acmicpc.net/problem/12761
 *
 */
public class Boj12761 {
	private static final String SPACE = " ";
	private static final int MAX = 100_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());	
		int M = Integer.parseInt(st.nextToken());

		int[] isVisited = new int[MAX];

		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		isVisited[N] = 1;
		
LOOP:		while(!q.isEmpty()){
			int current = q.poll();
			
			int[] direct = {-A, -B, A, B, 1, -1, current * A, current * B, current * (-A), current * (-B)};		// 이동 가능한 경우의 수를 정적이 아닌 큐에서 값을 받아올 때 마다 사용
			
			for(int move = 0; move < direct.length; move++){		// 아래는 일반적인 BFS, 단 곱셈이 들어간 경우랑 덧셈이 들어간 경우 수식을 나누어 넣어준다.
				int next = 0;
				
				if(move < 6){
					next = current + direct[move];
				}
				else{
					next = direct[move];
				}
				
				if(next >= 0 && next < MAX){
					if(isVisited[next] == 0){
						isVisited[next] = isVisited[current] + 1;
						
						if(next == M){													// 목적지에 도착한 경우
							break LOOP;
						}
						
						q.offer(next);
					}
				}
			}
		}
		
		System.out.println(isVisited[M] - 1);								// 최단거리 출력
	}
}
