package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17244번: 아 맞다 우산
 *
 *	@see https://www.acmicpc.net/problem/17244/
 *
 */
public class Boj17244 {
	private static ArrayList<Point> thing = new ArrayList<>();
	private static ArrayList<String> query = new ArrayList<>();
	
	private static boolean[] visit;
	private static int N, M;
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static final char START = 'S';
	private static final char END = 'E';
	private static final char BLOCK = '#';
	private static final char ITEM = 'X';
	
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
		StringTokenizer st  = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		Point s = new Point(0, 0);
		Point e = new Point(0, 0);
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == ITEM) thing.add(new Point(i, j));
				if(map[i][j] == START) s = new Point(i, j);
				if(map[i][j] == END) e = new Point(i, j);
			}
		}
		
		int leng = thing.size();
		for(int i = 0; i < leng; i++) {
			visit = new boolean[leng];
			backTracking(leng, i, 0, i + "");			// 물건을 줍는 경로 생성 (모든 경우)
		}
		
		int distance = Integer.MAX_VALUE;
		if(leng == 0) {									// 갖고 나갈 물건이 없는 경우
			distance = bfs(map, s, e);
		}
		else {
			for(String Q: query) {
				int value = getMinDistance(map, s, e, Q);
				if(value < distance) distance = value;		// 이동거리의 최소
			}
		}
		
		System.out.println(distance);
	}
	
	private static void backTracking(int size, int current, int count, String str) {
		if(visit[current]) return;
		visit[current] = true;
		
		if(count == size - 1) {
			query.add(str);
		}
		
		for(int next = 0; next < size; next++) {
			if(visit[next]) continue;
			
			backTracking(size, next, count + 1, str + " " + next);
			visit[next] = false;
		}
	}
	
	private static int getMinDistance(char[][] arr, Point start, Point end, String path) {
		StringTokenizer st = new StringTokenizer(path);
		Point s = start;
		Point e = new Point(0, 0);
		
		int cost = 0;
		
		while(st.hasMoreTokens()) {								// 나온 경로를 통해 총 이동 거리 합
			e = thing.get(Integer.parseInt(st.nextToken()));
			cost += bfs(arr, s, e);

			s = new Point(e.row, e.col);
		}
		
		cost += bfs(arr, s, end);
		return cost;
	}
	
	private static int bfs(char[][] arr, Point s, Point e) {
		int[][] visit = new int[N][M];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(s);
		visit[s.row][s.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
				if(visit[nextRow][nextCol] != 0 || arr[nextRow][nextCol] == BLOCK) continue;
				visit[nextRow][nextCol] = visit[current.row][current.col] + 1;

				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		return visit[e.row][e.col] - 1;
	}
}