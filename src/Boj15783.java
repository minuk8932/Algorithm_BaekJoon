import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15783 {
	private static boolean[] isCycle = null;
	private static int[] isVisited = null;
	private static int virus = 0;
	
	private static ArrayList<Integer>[] map = null;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		map = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			map[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			map[A].add(B);
		}

		isCycle = new boolean[N];
		for(int i = 0; i < N; i++) {
			dfs(i);
		}
		
		bfs(N);

		for (int i = 0; i < N; i++) {
			if (isVisited[i] == 0) {
				virus++;
			}
		}

		System.out.println(virus);
	}
	
	private static void dfs(int start) {
		
	}
	
	private static void bfs(int N) {
		isVisited = new int[N];

		for (int i = 0; i < N; i++) {
			for(int start: map[i]) {
				if (isVisited[start] == 0) {
					Queue<Integer> q = new LinkedList<>();
					isVisited[start] = 1;
	
					q.offer(start);
	
					while (!q.isEmpty()) {
						int current = q.poll();
	
						for (int next : map[current]) {							
							if (isVisited[next] == 0) {
								isVisited[next] = isVisited[current] + 1;
								
								if(i == next) virus++;
	
								q.offer(next);
							}
						}
					}
				}
			}
		}
	}
}
