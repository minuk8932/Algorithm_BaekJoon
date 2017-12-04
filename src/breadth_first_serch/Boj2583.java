package breadth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2583 {
	private static final String SPACE = " ";
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] area = new int[N][M];

		while (K-- > 0) {
			st = new StringTokenizer(br.readLine(), SPACE);
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			
			for(int i = B; i < D; i++){
				for(int j = A; j < C; j++){
					area[i][j] = 1;
				}
			}
		}
		
		int[][] isVisited = new int[N][M];
		
		ArrayList<Integer> list = new ArrayList<>();
		
		int areaCnt = 0;
		
		StringBuilder sb = new StringBuilder();
		
		for(int row = 0; row < N; row++){
			for(int col = 0; col < M; col++){
				if(area[row][col] == 0 && isVisited[row][col] == 0){
					isVisited[row][col] = 1;
					
					int areaWidth = 1;
					
					areaCnt++;
					
					Queue<Point> q = new LinkedList<>();
					q.offer(new Point(row, col));
					 
					while(!q.isEmpty()){
						Point current = q.poll();
						
						for(final int[] DIRECTION : DIRECTIONS){
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M){
								if(area[nextRow][nextCol] == 0 && isVisited[nextRow][nextCol] == 0){
									isVisited[nextRow][nextCol] = 1;
									
									areaWidth += isVisited[row][col];
									q.offer(new Point(nextRow, nextCol));
								}
							}
						}
					}
					list.add(areaWidth);
				}
			}
		}
		
		int size = list.size();
		int[] cnt = new int[size];
		
		for(int i = 0; i < cnt.length; i++){
			cnt[i] = list.get(i);
		}
		Arrays.sort(cnt);
		
		for(int i = 0; i < cnt.length; i++){
			sb.append(cnt[i]).append(SPACE);
		}
		
		System.out.println(areaCnt);
		System.out.println(sb.toString());
		
	}
	
	private static class Point{
		int row;
		int col;
		
			public Point(int row, int col){
				this.row = row;
				this.col = col;
			}
	}
	
}
