package disjoint_set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 * 백준 17090번: 미로 탈출하기
 * 
 * @see https://www.acmicpc.net/problem/17090/
 *
 */
public class Boj17090 {	
	private static HashMap<Character, Point> DIRECTIONS = new HashMap<>();	
	private static ArrayList<Point> fix = new ArrayList<>();
	private static int[] parent;
	
	private static final int[][] SEARCH = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static class Point{
		int row, col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		init(N, M);
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				
				if(i == 0 || j == 0 || i == N - 1 || j == M - 1) {
					Point dir = DIRECTIONS.get(map[i][j]);
					
					int nextRow = i + dir.row;
					int nextCol = j + dir.col;
					
					// 빠져나갈 수 있는 부분을 시작점으로
					if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) fix.add(new Point(i, j));
				}
			}
		}
		
		System.out.println(bfs(N, M, map));
	}
	
	private static void init(int n, int m) {
		parent = new int[n * m];
		
		for(int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}
		
		DIRECTIONS.put('U', new Point(-1, 0));
		DIRECTIONS.put('D', new Point(1, 0));
		DIRECTIONS.put('L', new Point(0, -1));
		DIRECTIONS.put('R', new Point(0, 1));
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		return parent[x] = find(parent[x]);
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}
	
	private static int bfs(int n, int m, char[][] arr) {
		boolean[][] isVisited = new boolean[n][m];
		int result = 0;
		
		for(Point start: fix) {
			if(isVisited[start.row][start.col]) continue;
			isVisited[start.row][start.col] = true;
			
			result++;		// 단일 노드 처리
			
			Queue<Point> q = new LinkedList<>();
			q.offer(new Point(start.row, start.col));
			
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				for(final int[] DIRECTION: SEARCH) {
					int adjRow = current.row + DIRECTION[ROW];
					int adjCol = current.col + DIRECTION[COL];
					
					if(adjRow < 0 || adjRow >= n || adjCol < 0 || adjCol >= m) continue;
					Point dir = DIRECTIONS.get(arr[adjRow][adjCol]);			// 인접 행렬의 알파벳 값으로 방향 탐색
					
					int nextRow = adjRow + dir.row;
					int nextCol = adjCol + dir.col;
					
					if(nextRow == current.row && nextCol == current.col) {		// 인접 행렬의 값을 통해 현재 행렬로 돌아올 수 있는 경우
						merge(nextRow * m + nextCol, adjRow * m + adjCol);
						
						if(isVisited[adjRow][adjCol]) continue;
						isVisited[adjRow][adjCol] = true;
						
						q.offer(new Point(adjRow, adjCol));
					}
				}
			}
		}
		
		for(int i = 0; i < parent.length; i++) {
			if(parent[i] < -1) result -= (parent[i] + 1);		// 시작점은 미리 더했으므로 하나씩 빼줌
		}
		
		return result;
	}
}
