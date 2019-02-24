package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15685번: 드래곤 커브
 *
 *	@see https://www.acmicpc.net/problem/15685/
 *
 */
public class Boj15685 {
	private static final int[][] SQUARE_SEARCH = {{1, 0}, {0, 1}, {1, 1}};
	private static final int[][] DIRECTIONS = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static int[][] map;
	
	private static class Point{
		int x;
		int y;
		int dir;
		int gen;
		
		public Point(int x, int y, int dir, int gen) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.gen = gen;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Point[] info = new Point[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			info[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(dragonCurve(N, info));
	}
	
	private static int dragonCurve(int n, Point[] p) {
		map = new int[101][101];
		
		for(Point coor: p) {
			ArrayList<Integer> list = new ArrayList<>();
			list.add(coor.dir);				// 0 세대
			
			map[coor.y][coor.x] = 1;
			
			for(int i = 0; i < coor.gen; i++) {			// 1세대 부터
				int loop = list.size();
				
				for(int j = 0; j < loop; j++) {					// 각 세대마다 연결될 정점들을 리스트에 담고 다시 넣는 식으로 반복
					coor.dir = (list.get(loop - 1 - j) + 1) % 4;
					list.add(coor.dir);
				}
			}
			
			int loop = list.size();
			for(int i = 0; i < loop; i++) {
				coor.y += DIRECTIONS[list.get(i)][ROW];		// 리스트에 들어있는 방향만큼 모두 꺼내서 사각형 색칠
				coor.x += DIRECTIONS[list.get(i)][COL];
				
				map[coor.y][coor.x] = 1;
			}
		}
		
		return squares();		// 사각형 갯수 반환
	}
	
	private static int squares() {
		int count = 0;
		
		for(int row = 0; row < map.length; row++) {
			for(int col = 0; col < map[row].length; col++) {
				if(map[row][col] == 0) continue;
				
				int isSquare = 1;
				
				for(final int[] DIRECTION: SQUARE_SEARCH) {
					int nextRow = row + DIRECTION[ROW];
					int nextCol = col + DIRECTION[COL];
					
					if(nextRow < 0 || nextRow > 100 || nextCol < 0 || nextCol > 100 || map[nextRow][nextCol] == 0) break;
					isSquare++;
				}
				
				if(isSquare == 4) count++;
			}
		}
		
		return count;
	}
}
