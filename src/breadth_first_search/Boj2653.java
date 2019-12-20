package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 2653번: 안정된 집단
 *
 *	@see https://www.acmicpc.net/problem/2653/
 *
 */
public class Boj2653 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		boolean[][] group = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) continue;
				group[i][j] = true;
			}
		}
		
		System.out.println(grouping(N, group));
	}
	
	private static String grouping(int n, boolean[][] arr) {
		boolean[] visit = new boolean[n];
		int count = 0;
		
		ArrayList<Integer>[] list = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			list[i] = new ArrayList<>();
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int start = 0; start < n; start++) {
			if(visit[start]) continue;
			visit[start] = true;
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(start);
			
			ArrayList<Integer> save = new ArrayList<>();
			
			while(!q.isEmpty()) {
				int current = q.poll();
				save.add(current);
				
				for(int next = 0; next < n; next++){
					if(!arr[current][next] || visit[next]) continue;			// find group
					visit[next] = true;
					q.offer(next);
				}
			}
			
			int size = save.size();
			
			for(int i = 0; i < size; i++){
				for(int j = i + 1; j < size; j++){
					if(!arr[save.get(i)][save.get(j)]) return sb.append(0).toString();
				}
			}
			
			if(size > 1){
				count++;
				Collections.sort(save);
				
				for(int i = 1; i < size; i++) {				// save group with heads
					list[save.get(0)].add(save.get(i));
				}
			}
		}
		
		sb.append(count).append(NEW_LINE);
		for(int i = 0; i < n; i++) {
			if(list[i].size() == 0) continue;
			sb.append(i + 1).append(SPACE);
			
			for(int a: list[i]) {
				sb.append(a + 1).append(SPACE);
			}
			
			sb.append(NEW_LINE);
		}

		return sb.toString();
	}
}
