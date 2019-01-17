import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15683 {
	private static int[][] res;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Queue<CctvInfo> q = new LinkedList<>();
		int[][] map = new int[N][M];
		boolean[][] isObserved = new boolean[N][M];
		
		int size = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] > 0 && map[i][j] <= 6) {
					if(map[i][j] != 6) {
						q.offer(new CctvInfo(map[i][j], i , j));
						size++;
					}
		
					isObserved[i][j] = true;
				}
			}
		}
		
		res = new int[size][4];
		
		bfs(q, map, isObserved);
	}
	
	private static class CctvInfo{
		int num;
		int row;
		int col;
		
		public CctvInfo(int num, int row, int col) {
			this.num = num;
			this.row = row;
			this.col = col;
		}
	}
	
	private static void bfs(Queue<CctvInfo> q, int[][] arr, boolean[][] isObserved) {
		
	}
}
