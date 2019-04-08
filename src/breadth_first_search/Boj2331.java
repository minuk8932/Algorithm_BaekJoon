package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2331번: 반복 수열
 *
 *	@see https://www.acmicpc.net/problem/2331/
 *
 */
public class Boj2331 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(A, P));
	}
	
	private static int bfs(int start, int repeat) {
		LinkedList<Integer> result = new LinkedList<>();
		boolean[] visit = new boolean[10_000_001];
		
		int cut = 0;
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		result.add(start);
		
		visit[start] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			String maker = current + "";
			
			int next = 0;
			for(char c: maker.toCharArray()) {
				int num = c - '0';
				int loop = repeat;
				int value = 1;
				
				while(loop-- > 0) {		// 제곱수
					value *= num;
				}
				
				next += value;
			}

			if(visit[next]) {
				cut = next;				// 반복기점
				break;
			}
			
			visit[next] = true;
			result.add(next);
			q.offer(next);
		}
		
		int count = 0;
		
		while(!result.isEmpty()) {
			if(cut == result.remove()) break;		// 반복기점이 나오기 전까지 +1
			count++;
		}
		
		return count;
	}
}
