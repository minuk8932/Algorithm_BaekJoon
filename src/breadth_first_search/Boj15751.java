package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15751번: Teleportation
 *
 *	@see https://www.acmicpc.net/problem/15751/
 *
 */
public class Boj15751 {
	
	private static class Pair{
		int edge;
		boolean tel;
		
		public Pair(int edge, boolean tel) {
			this.edge = edge;
			this.tel = tel;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(a, b, x, y));
	}
	
	private static int bfs(int start, int end, int x, int y) {		
		int[] isVisited = new int[101];
		
		int[] teleport = new int[101];
		Arrays.fill(teleport, -1);
		teleport[x] = y;
		teleport[y] = x;
		
		int min = Integer.MAX_VALUE;
		
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(start, teleport[start] == x || teleport[start] == y ? true: false));	// 위치 탐색 시 현재와 다음 위치가 텔레포트인지 체크
		
		isVisited[start] = 1;
		
		while(!q.isEmpty()) {
			Pair current = q.poll();
			int[] move = {current.edge + 1, current.edge - 1, teleport[current.edge]};		// 앞 뒤 텔레포트
				
			for(int m: move) {
				int next = m;
					
				if(next < 0 || next > 100) continue;
				if(isVisited[next] != 0) continue;
				
				if((next == teleport[x] || next == teleport[y]) && current.tel) isVisited[next] = isVisited[current.edge];
				else isVisited[next] = isVisited[current.edge] + 1;
				
				if(next == end) min = Math.min(min, isVisited[next]);
				q.offer(new Pair(next, next == teleport[x] || next == teleport[y] ? true: false));
			}
		}
		
		return min - 1;
	}
}
