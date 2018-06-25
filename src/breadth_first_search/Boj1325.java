package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1325번 : 효율적인 해킹
 *
 *	@see https://www.acmicpc.net/problem/1325
 *
 */
public class Boj1325 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] coms = new int[N + 1];
		
		ArrayList<Integer>[] isTrust = new ArrayList[N + 1];
		for(int i = 1; i < N + 1; i++){											// 인접리스트 선언 및 초기화
			isTrust[i] = new ArrayList<>();
		}
		
		while(M-- > 0){
			st = new StringTokenizer(br.readLine(), SPACE);
			isTrust[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 1; i < N + 1; i++){
			int[] isVisited = new int[N + 1];
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(i);																	// 시작 값을 큐에 담아줌
			isVisited[i] = 1;
			
			while(!q.isEmpty()){
				int current = q.poll();
				
				for(int next : isTrust[current]){								// 다음 접근할 컴퓨터(신뢰하는)는 인접리스트에 담겨있는 값으로, 하나씩 가져와서
					if(isVisited[next] == 0){										// 접근했던 노드인지 확인하고
						isVisited[next] = 1;
						coms[next]++;												// 경로가 존재하면(신뢰하는 컴퓨터) +1
						
						q.offer(next);												// 해당 컴퓨터 번호를 다시 큐에담고 값을 찾아나감
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int max = 0;
		
		for(int i = 1; i < N + 1; i++){												// 최대값을 max 변수에 저장
			max = Math.max(coms[i], max);
		}
		
		for(int i = 1; i < N + 1; i++){												// 최댓값 가진 컴퓨터의 번호를 하나씩 버퍼에 담은 후
			if(coms[i] == max){
				sb.append(i).append(SPACE);
			}
		}
		
		System.out.println(sb.toString());									// 한번에 결과값 출력
	}
}
