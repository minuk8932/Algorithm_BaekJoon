package bandwidth_first_serch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1389 {
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
