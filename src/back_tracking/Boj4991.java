package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 4991번: 로봇 청소기
 *
 *	@see https://www.acmicpc.net/problem/4991/
 *
 */
public class Boj4991 {
	private static final String TERMINATE = "0 0";
	private static final String NEW_LINE = "\n";
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static Point start;
	private static ArrayList<Point> dirty;
	private static int min;
	
	private static final char DIRT = '*';
	private static final char BLOCK = 'x';
	private static final char ROBO = 'o';
	
	private static int[] seq;
	private static int[][] dist;
	
	private static boolean flag;
	private static boolean[] used;
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String input = br.readLine();
			if(TERMINATE.equals(input)) break;
			
			StringTokenizer st = new StringTokenizer(input);
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			char[][] map = new char[N][M];
			dirty = new ArrayList<>();
			start = new Point(-1, -1);
			
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				
				for(int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
					if(map[i][j] == ROBO) start = new Point(i, j);
					if(map[i][j] == DIRT) dirty.add(new Point(i, j));
				}
			}
			
			dirty.add(0, start);						// add start front
			int size = dirty.size();
			dist = new int[size][size];
			
			if(!bfs(N, M, map)) {						// robot cannot reached dirt
				sb.append(-1).append(NEW_LINE);
				continue;
			}
			
			seq = new int[size];
			used = new boolean[size];
			min = Integer.MAX_VALUE;
			
			for(int i = 0; i < size; i++) {				// make sequence find min cost
				backTracking(N, M, map, i, 0);
			}
			
			sb.append(min).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static boolean bfs(int n, int m, char[][] arr) {
		for(int i = 0; i < dist.length; i++) {
			for(int j = i + 1; j < dist[i].length; j++) {
				if(dist[i][j] != 0 || dist[j][i] != 0) continue;			// already calculated
				int[][] visit = new int[n][m];
				
				flag = false;
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(dirty.get(i).row, dirty.get(i).col));
				visit[dirty.get(i).row][dirty.get(i).col] = 1;
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
						
						if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
						if(visit[nextRow][nextCol] != 0 || arr[nextRow][nextCol] == BLOCK) continue;
						visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
						
						if(nextRow == dirty.get(j).row && nextCol == dirty.get(j).col) {			// reached
							dist[i][j] = dist[j][i] = visit[nextRow][nextCol] - 1;
							
							flag = true;
							break;
						}
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
				
				if(!flag) return flag;				// cannot reached at least one
			}
		}
		
		return true;
	}
	
	private static void backTracking(int n, int m, char[][] arr, int current, int count) {
		if(used[current]) return;
		used[current] = true;
		
		seq[count] = current;
		
		if(count == seq.length - 1) {
			int cost = 0;
			int prev = 0;
			
			for(int idx = 1; idx < seq.length; idx++) {
				cost += dist[prev][seq[idx]];
				prev = seq[idx];
			}
			
			if(cost < min) min = cost;
			return;
		}
		
		for(int next = 0; next < seq.length; next++) {
			if(used[next]) continue;
			
			backTracking(n, m, arr, next, count + 1);
			used[next] = false;
		}
	}
}