package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2916번: 자와 각도기
 *
 *	@see https://www.acmicpc.net/problem/2916/
 *
 */
public class Boj2916 {
	private static final String AC = "YES";
	private static final String WA = "NO";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int N = Integer.parseInt(st.nextToken());
	    int K = Integer.parseInt(st.nextToken());
	    
	    int[] axis = new int[N];
	    st = new StringTokenizer(br.readLine());
	    for(int i = 0; i < N; i++){
	        axis[i] = Integer.parseInt(st.nextToken());
	    }

	    StringBuilder sb = new StringBuilder();
	    st = new StringTokenizer(br.readLine());
	    while(K-- > 0){
	        int query = Integer.parseInt(st.nextToken());
	        sb.append(bfs(N, axis, query)).append(NEW_LINE);
	    }
	    
	    System.out.print(sb);
	}
	
	private static String bfs(int n, int[] arr, int target) {		
		for(int start: arr) {
			boolean[] visit = new boolean[361];
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(start);
			
			visit[start] = true;
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int value: arr) {
					int[] next = {(value + current) % 360, getAbs(current, value)};		// 360도 제한 맞춘 각의 크기
					
					for(int type = 0; type < 2; type++) {
						if(visit[next[type]]) continue;
						visit[next[type]] = true;
						
						if(next[type] == target) return AC;
						q.offer(next[type]);
					}
				}
			}
		}
		
		return WA;
	}
	
	private static int getAbs(int current, int value) {
		int diff = current - value;
		return diff < 0 ? 360 + diff: diff;
	}
}
