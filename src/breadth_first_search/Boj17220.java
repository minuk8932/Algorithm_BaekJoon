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
 *	백준 17220번: 마약 수사대
 *
 *	@see https://www.acmicpc.net/problem/17220/
 *
 */
public class Boj17220 {
	private static ArrayList<Integer>[] graph;
	private static int[] search;
	
	private static boolean[] isChild;
	private static boolean[] arrested;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N];
		isChild = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = st.nextToken().charAt(0) - 'A';
			int to = st.nextToken().charAt(0) - 'A';
			
			graph[from].add(to);
			isChild[to] = true;			// 원산지가 아닌 경우
		}
		
		arrested = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		int loop = Integer.parseInt(st.nextToken());
		
		while(loop-- > 0) {
			arrested[st.nextToken().charAt(0) - 'A'] = true;		// 체포당한 경우
		}
		
		System.out.println(getCount(N));
	}
	
	private static int getCount(int n) {
		search = new int[n];
		boolean flag = true;
		
		for(int prod = 0; prod < n; prod++) {
			if(!arrested[prod]) continue;
			
			flag = false;
			bfs(n, prod, -1);				// 체포당한 공급책을 확인
		}
		
		return flag ? 0: alived(n);
	}
	
	private static void bfs(int n, int src, int fill) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(src);
		
		search[src] = fill;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: graph[current]) {
				if(search[next] == fill || arrested[next]) continue;
				search[next] = fill;
				
				q.offer(next);
			}
		}
	}
	
	private static int alived(int n) {
		for(int i = 0; i < n; i++) {
			if(isChild[i] || arrested[i]) continue;		// 원산지가 아니거나 체포당한 경우 돌지 않음
			bfs(n, i, 1);
		}
		
		int count = 0;
		for(int i = 0; i < n; i++) {
			if(search[i] == 1 && isChild[i]) count++;		// 여전히 공급 받는 공급책의 갯수
		}
		
		return count;
	}
}
