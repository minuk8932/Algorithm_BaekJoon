import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 다익스트라 인듯?

public class Boj1486 {
	private static final String SPACE = " ";
	
	private static int N = 0;
	private static int M = 0;
	private static int T = 0;
	private static int D = 0;
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 1_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		N = Integer.parseInt(st.nextToken());				// 세로
		M = Integer.parseInt(st.nextToken());				// 가로
		T = Integer.parseInt(st.nextToken());				// 높이차이 한계, Math.abs([i - 1] - [i]) <= T
		D = Integer.parseInt(st.nextToken());				// 호텔에서 출발해 호텔까지 오는데 걸리는 시간
		
		int[][] map = new int[N][M];
		int[][] cost = new int[N][M];
		
		for(int i = 0; i < N; i++){
			String line = br.readLine();
			Arrays.fill(cost[i], INF);
			
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
		
		dijkstra(map, pos);
		
//		int max = 0, size = pos.size();
//		
//		for(int i = 0; i < size; i++){
//			max = Math.max(max, map[pos.get(i).row][pos.get(i).col]);			
//		}
//		
//		System.out.println(max);
	}
	
	private static class Point implements Comparable<Point>{
		int row;
		int col;
		int cost;
		
		public Point(int row, int col, int cost){
			this.row = row;
			this.col = col;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point p) {
			return this.cost < p.cost ? -1 : 1;
		}
	}
	
	private static void dijkstra(int[][] map, ArrayList<Point> pos){		
		int[][] isVisited = new int[N][M];
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(0, 0, map[0][0]));
		
		isVisited[0][0] = 1;
		
		while(!pq.isEmpty()){
			Point current = pq.poll();
			
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
					
//					if(isVisited[current.row][current.col] <= D && isVisited[nextRow][nextCol] == 0 && way <= T){
//						isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
//						
//						if(isOver){
//							int times = (int) Math.pow(way, 2);
//																	
//							if(times > D){
//								pos.add(new Point(current.row, current.col));
//							}
//							else if(times == D){
//								pos.add(new Point(nextRow, nextCol));
//							}
//						}						
//						
//						pq.offer(new Point(nextRow, nextCol));
//					}
				}
			}
		}		
	}
}
