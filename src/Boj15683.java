import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15683 {
	private static boolean[][] isObserved = null;
	private static int[][] map = null;
	
	private static CctvInfo[] cctv = null;
	private static Queue<CctvInfo> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		isObserved = new boolean[N][M];
		
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
		
		int qSize = q.size();
		
		boolean[][][] seq = new boolean[N][M][qSize];
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {
				if(!(map[row][col] > 0 && map[row][col] <= 6)) continue;
				for(int i = 0; i < seq[row][col].length; i++) { 
					seq[row][col][i] = true;
				}
			}
		}
		
		
		
		System.out.println();
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
}
