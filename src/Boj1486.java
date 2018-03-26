import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1486 {
	private static final String SPACE = " ";
	
	private static int N = 0;
	private static int M = 0;
	private static int T = 0;
	private static int D = 0;
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for(int i = 0; i < N; i++){
			String line = br.readLine();
			
			for(int j = 0; j < M; j++){
				char tmp = line.charAt(j);
				
				if(tmp >= 'A' && tmp <= 'Z'){
					map[i][j] = (tmp - 'A');
				}
				
				else if(tmp >= 'a' && tmp <= 'z'){
					map[i][j] = (tmp - 'a' + 26);
				}
			}
		}
		
		ArrayList<Point> pos = new ArrayList<>();
		
		bfs(map, pos);
		
		int max = 0, size = pos.size();
		
		for(int i = 0; i < size; i++){
			max = Math.max(max, map[pos.get(i).row][pos.get(i).col]);			
		}
		
		System.out.println(max);
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	private static void bfs(int[][] map, ArrayList<Point> pos){
		
		int isVisited[][] = new int[N][M];
			
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0));
		isVisited[0][0] = 1;
		
		while(!q.isEmpty()){
			Point current = q.poll();
			
			for(final int[] DIRECTION : DIRECTIONS){
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M){
					int way = map[current.row][current.col] - map[nextRow][nextCol];
					boolean isOver = false;
					
					if(way < 0){				// 다음 갈 곳이 더 높은 경우
						isOver = true;
						way *= - 1;
					}
					
					if(isVisited[current.row][current.col] <= D && isVisited[nextRow][nextCol] == 0 && way <= T){
						isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
						
						if(isOver){
							int times = (int) Math.pow(way, 2);
																	
							if(times > D){
								pos.add(new Point(current.row, current.col));
							}
							else if(times == D){
								pos.add(new Point(nextRow, nextCol));
							}
						}						
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}		
	}
}
