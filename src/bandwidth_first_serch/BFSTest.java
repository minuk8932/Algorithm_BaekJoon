package bandwidth_first_serch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFSTest {
	public static final String SPACE = " ";
	public static final String NEW_LINE = "\n";
	
	private static final int DIRECTIONS[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[R][C];
		StringBuilder sb = new StringBuilder();
		
		while(K-- > 0){
			st = new StringTokenizer(br.readLine(), SPACE);
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] =  true;
		}
		
		int areaCnt = 0;
		
		for(int row = 0; row < R; row++){
			for(int col = 0; col < C; col++){
				int cnt = 0;
				
				if(map[row][col]){					
					map[row][col] = false;
					areaCnt++;
					
					Queue<Point> queue = new LinkedList<>();
					queue.offer(new Point(row, col));
					
					while(!queue.isEmpty()){
						cnt++;
						int size = 1;
						Point current = queue.poll();
						
						for(final int DIRECTION[] : DIRECTIONS){
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C){
								if(map[nextRow][nextCol]){
									size++;
									map[nextRow][nextCol] = false;
									queue.offer(new Point(nextRow, nextCol));
								}
							}
						}
						
					}
					sb.append(areaCnt).append("번째조 인원 : ").append(cnt).append("명").append(NEW_LINE);
				}
			}
		}
		
		br.close();
		
		System.out.println(areaCnt);
		System.out.println(sb.toString());
		

	}

	private static class Point{
		public int row;
		public int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
}
