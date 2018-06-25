package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*							배열 :  리스트
 * 		1  -  2             	1 : {2, 3,4}
 *     |  \					2 : {1}					ArrayList[N+1]  :  1 ~ N 번까지 사용
 *     3     4				3 : {1}					ArrayList<Integer>[] list = new Arraylist[N+1];
 *     							4 : {1}					-> 루프를 돌면서 내부 리스트를 초기화 해주어야 nullPointerException이 뜨지 않음
 *																 1 : {}
 *     															2 : {}
 *     															3 : {}
 *																4 : {}     
 *														list[1].add(2);  list[2].add(1);  양 방향 노드 생성
 *     
 */


public class Boj1389_adjacentList {
	private static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] list = new ArrayList[N + 1];

		for (int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}

		while(M-- > 0){
			st = new StringTokenizer(br.readLine(), SPACE);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		br.close();
		
		int minIdx = 1;
		int min = Integer.MAX_VALUE;
		
		for(int i = 1; i <= N; i++){
			int sum = -N;
			int[] isVisited = new int[N+1];
			isVisited[i] = 1;
			
			Queue<Integer> queue = new LinkedList<>();
			queue.offer(i);		// 1번째 값 큐에 입력
			
			while(!queue.isEmpty()){
				int current = queue.poll();
				
				for(final int next : list[current]){   		// current = 1 일 때 1에서 갈 수 있는 경로가 하나씩 들어옴
					if(isVisited[next] == 0){
						
						sum += isVisited[next] = isVisited[current] + 1;
						queue.offer(next);
						
					}
				}
			}
			if(min > sum){			// sum 케빈 베이컨 값이 min 보다 작으면 update 해주어야 한다 
				min = sum;
				minIdx = i;
			}
			
		}
		System.out.println(minIdx);
	}
}
