package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1107번: 리모콘
 *
 *	@see https://www.acmicpc.net/problem/1107/
 *
 */
public class Boj1107 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		boolean[] error = new boolean[10];
		StringTokenizer st = null;
		if(M != 0) st = new StringTokenizer(br.readLine());
		
		while(M-- > 0) {
			error[Integer.parseInt(st.nextToken())] = true;
		}
		
		System.out.println(bfs(N, M, error, 100));
	}
	
	private static int bfs(int n, int m, boolean[] isError, int origin) {
		if(origin == n) return 0;
		
		boolean[] isVisited = new boolean[1_000_001];
		
		for(int start = 0; start < 10; start++) {
			if(isError[start]) continue;
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(start);
			
			isVisited[start] = true;
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int num = 0; num < 10; num++) {
					int nextNum = current * 10 + num;		// 뒷자리에 num 하나씩 붙이기
					
					if(nextNum > isVisited.length - 1) continue;
					if(isError[num] || isVisited[nextNum]) continue;
					isVisited[nextNum] = true;
					
					q.offer(nextNum);
				}
 			}
		}
		
		isVisited[100] = true;			// 시작 채널
		return getMin(isVisited, n);
	}
	
	private static int getMin(boolean[] visit, int target) {
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < visit.length; i++) {
			if(!visit[i]) continue;
			
			int diff = Math.abs(i - target);			// +, - 누른 횟수
			int count = String.valueOf(i).length();		// 숫자를 누른 횟수
			
			if(diff + count < min) min = diff + count;
		}
		
		return Math.min(min, Math.abs(100 - target));
	}
}
