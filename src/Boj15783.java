import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15783 {
	private static ArrayList<Integer>[] map = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N];
		
		for(int i = 0; i < N; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			map[A].add(B);
		}
		
		System.out.println(bfs(N));
	}
	
	private static int bfs(int N) {
		int[] isVisited = new int[N];
		int virus = 0;
		
		for(int i = 0; i < N; i++) {
			if(isVisited[i] == 0) {
				
				Queue<Integer> q = new LinkedList<>();
				isVisited[i] = 1;
				
				q.offer(i);
				
				while(!q.isEmpty()) {
					int current = q.poll();
					
					for(int next : map[current]) {
						if(isVisited[next] == 0) {
							isVisited[next] = isVisited[current] + 1;
							
							q.offer(next);
						}
						
						if(isVisited[next] == isVisited[current] && isVisited[current] == 1) {
							isVisited[next] = isVisited[current] + 1;
						}
					}
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			if(isVisited[i] == 1) {
				virus++;
			}
		}
		
		return virus;
	}
}
