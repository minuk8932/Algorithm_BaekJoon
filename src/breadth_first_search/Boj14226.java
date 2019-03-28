package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 14226번: 이모티콘
 *
 *	@see https://www.acmicpc.net/problem/14226/
 *
 */
public class Boj14226 {
	
	private static class Pair{
		int emot;
		int copy;
		
		public Pair(int emot, int copy) {
			this.emot = emot;
			this.copy = copy;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int S = Integer.parseInt(br.readLine());
		
		System.out.println(bfs(S));
	}
	
	private static int bfs(int target) {
		int[][] visit = new int[2_002][2_002];			// [이모티콘][클립보드]
		int min = Integer.MAX_VALUE;
		int start = 1;
		
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(start, 0));
		visit[start][0] = 1;
		
		while(!q.isEmpty()) {
			Pair current = q.poll();
			if(current.emot == target) min = Math.min(min, visit[current.emot][current.copy]);
			
			Pair next = new Pair(current.emot, current.emot);
			if(visit[next.emot][next.copy] == 0) {										// 클립보드로 복사
				visit[next.emot][next.copy] = visit[current.emot][current.copy] + 1;
				
				q.offer(next);
			}
			
			if(current.copy != 0) {														// 붙여넣기
				next = new Pair(current.emot + current.copy, current.copy);
                
				if(next.emot < 1_001){
				    if(visit[next.emot][next.copy] == 0) {
					    visit[next.emot][next.copy] = visit[current.emot][current.copy] + 1;
					
					    q.offer(next);
                    }
				}
			}
			
			if(current.emot > 1) {														// 1글자 삭제
				next = new Pair(current.emot - 1, current.copy);
				
				if(visit[next.emot][next.copy] == 0) {
					visit[next.emot][next.copy] = visit[current.emot][current.copy] + 1;
					
					q.offer(next);
				}
			}
		}
		
		return min - 1;
	}
}
