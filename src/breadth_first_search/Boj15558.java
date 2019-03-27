package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15558번: 점프 게임
 *
 *	@see https://www.acmicpc.net/problem/15558/
 *
 */
public class Boj15558 {
	
	private static class Pair{
		int side;
		int num;
		
		public Pair(int side, int num) {
			this.side = side;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		boolean[][] board = new boolean[2][N];
		for(int j = 0; j < 2; j++) {
			String line = br.readLine();
			
			for(int i = 0; i < N; i++) {
				board[j][i] = line.charAt(i) == '0' ? false: true;
			}
		}
		
		System.out.println(bfs(N, k, board));
	}
	
	private static int bfs(int n, int k, boolean[][] arr) {
		int[][] isVisited = new int[2][n];
		
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(0, 0));
		
		isVisited[0][0] = 1;
		
		while(!q.isEmpty()) {
			Pair current = q.poll();
			
			Pair[] direction = {new Pair(current.side, current.num + 1), 		// 3가지 움직이는 경우
					new Pair(current.side, current.num - 1), 
					new Pair((current.side + 1) % 2, current.num + k)};
			
			for(Pair next: direction) {
				if(next.num >= n) return 1;
				if(next.num <= isVisited[current.side][current.num] - 1) continue;				// x초후 박살난 보드는 접근 불가
				if(!arr[next.side][next.num] || isVisited[next.side][next.num] != 0) continue;	// 다음칸이 이동 불가 또는 이미 방문한 경우
				isVisited[next.side][next.num] = isVisited[current.side][current.num] + 1;
				
				q.offer(next);
			}
		}
		
		return 0;
	}
}
