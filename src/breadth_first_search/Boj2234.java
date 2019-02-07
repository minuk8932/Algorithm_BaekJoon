package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2234번: 성곽
 *
 *	@see https://www.acmicpc.net/problem/2234/
 *
 */
public class Boj2234 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};	// 이진수 형식에 맞게 동남북서
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final int BLOCK = 0;
	private static final int ROOM = 1;
	private static final int PASSAGE = 2;
	
	private static final String NEW_LINE = "\n";
	
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken()) * 2 + 1;
		int n = Integer.parseInt(st.nextToken()) * 2 + 1;
		
		int[][] map = new int[n][m];
		
		for(int i = 1; i < n; i += 2) {					// 맵의 크기를 키워서 통로와 벽을 끼워넣음
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j < m; j += 2) {
				map[i][j] = ROOM;
				String binary = Integer.toBinaryString(Integer.parseInt(st.nextToken()));

				int leng = binary.length();
				if(leng != 4) binary = resetBinary(leng, binary);		// 길이가 4이하인 2진수를 길이에 맞게 0추가
				
				for(int idx = 0; idx < 4; idx++) {						
					int nextRow = i + DIRECTIONS[idx][ROW];
					int nextCol = j + DIRECTIONS[idx][COL];

					if(binary.charAt(idx) == '0') map[nextRow][nextCol] = PASSAGE;
				}			
			}
		}
		
		System.out.println(bfs(n, m, map));
	}
	
	private static String resetBinary(int leng, String b) {
		StringBuilder sb = new StringBuilder();
		
		int loop = 4 - leng;
		String str = "";
		
		while(loop-- > 0) {
			str += "0";
		}
		
		sb.append(str).append(b);
		return sb.toString();
	}
	
	private static StringBuilder bfs(int N, int M, int[][] map) {
		StringBuilder sb = new StringBuilder();
		int[] sizes = new int[2501];
		int[][] isVisited = new int[N][M];
		
		int roomCount = 0, maxRoom = 0, maxSum = 0;
		
		for(int row = 1; row < N; row += 2) {
			for(int col = 1; col < M; col += 2) {
				if(isVisited[row][col] != 0 || map[row][col] == BLOCK) continue;
				int roomSize = 1;
				isVisited[row][col] = ++roomCount;		// 각 방마다 번호를 매김 -> 방의 갯수가 됨

				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row, col));
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
						
						if(nextRow < 0 || nextRow > N - 1 || nextCol < 0 || nextCol > M - 1) continue;
						if(isVisited[nextRow][nextCol] != 0 || map[nextRow][nextCol] == BLOCK) continue;
						
						isVisited[nextRow][nextCol] = roomCount;
							
						if(map[nextRow][nextCol] == ROOM) roomSize++;
						q.offer(new Point(nextRow, nextCol));
					}
				}
				
				if(roomSize > maxRoom) maxRoom = roomSize;
				sizes[roomCount] = roomSize;
			}
		}
		
		maxSum = getMaxSum(isVisited, sizes, maxRoom);
		
		sb.append(roomCount).append(NEW_LINE)
		.append(maxRoom).append(NEW_LINE)
		.append(maxSum);
		
		return sb;
	}
	
	private static int getMaxSum(int[][] roomInfo, int[] roomSize, int init) {
		int max = init;
		
		for(int i = 0; i < roomInfo.length; i++) {
			for(int j = 0; j < roomInfo[i].length; j++) {
				if(roomInfo[i][j] != BLOCK) continue;
				if(rangeLimit(i, j, roomInfo.length - 1, roomInfo[i].length - 1)) continue;
				
				int[] up = {i + DIRECTIONS[2][ROW], j + DIRECTIONS[2][COL]};
				int[] down = {i + DIRECTIONS[0][ROW], j + DIRECTIONS[0][COL]};
				int[] left = {i + DIRECTIONS[3][ROW], j + DIRECTIONS[3][COL]};
				int[] right = {i + DIRECTIONS[1][ROW], j + DIRECTIONS[1][COL]};
				
				max = getSum(roomInfo, roomSize, up, down, max);		// 인접한 방의 크기 합 중 최대
				max = getSum(roomInfo, roomSize, left, right, max);
			}
		}
		
		return max;
	}
	
	private static boolean rangeLimit(int row, int col, int rowMax, int colMax) {
		return (row == 0 || col == 0 || row == rowMax || col == colMax) ? true : false;
	}
	
	private static int getSum(int[][] info, int[] size, int[] arr1, int[] arr2, int result) {
		if(info[arr1[ROW]][arr1[COL]] != info[arr2[ROW]][arr2[COL]]) {
			int sum = size[info[arr1[ROW]][arr1[COL]]] + size[info[arr2[ROW]][arr2[COL]]];
			if(sum > result) result = sum;
		}
		
		return result;
	}
}