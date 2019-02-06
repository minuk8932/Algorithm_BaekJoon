import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2234 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
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
		
		for(int i = 1; i < n; i += 2) {
			st = new StringTokenizer(br.readLine());
			String binary = "";
			
			for(int j = 1; j < m; j += 2) {
				map[i][j] = ROOM;
				binary = Integer.toBinaryString(Integer.parseInt(st.nextToken()));

				int leng = binary.length();
				if(leng != 4) binary = resetBinary(leng, binary);
					
				for(int idx = 0; idx < leng; idx++) {						
					int nextRow = i + DIRECTIONS[idx][ROW];
					int nextCol = j + DIRECTIONS[idx][COL];
						
					if(binary.charAt(idx) == '1') map[nextRow][nextCol] = BLOCK;
					else map[nextRow][nextCol] = PASSAGE;
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
				if(isVisited[row][col] != 0 || map[row][col] != ROOM) continue;
				int roomSize = 1;
				isVisited[row][col] = ++roomCount;
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row, col));
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
						
						if(nextRow < 0 || nextRow > N - 1 || nextCol < 0 || nextCol > M - 1) continue;
						if(isVisited[nextRow][nextCol] != 0) continue;
						
						if(map[nextRow][nextCol] == ROOM || map[nextRow][nextCol] == PASSAGE) {
							isVisited[nextRow][nextCol] = roomCount;
							
							if(map[nextRow][nextCol] == ROOM) roomSize++;
							q.offer(new Point(nextRow, nextCol));
						}
					}
				}
				
				if(roomSize > maxRoom) maxRoom = roomSize;
				sizes[roomCount] = roomSize;
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(isVisited[i][j] + " ");
			}
			System.out.println();
		}
		
		maxSum = getMaxSum(isVisited, sizes, map);
		
		sb.append(roomCount).append(NEW_LINE)
		.append(maxRoom).append(NEW_LINE)
		.append(maxSum);
		
		return sb;
	}
	
	private static int getMaxSum(int[][] roomInfo, int[] roomSize, int[][] arr) {
		int max = 0;
		
		for(int i = 0; i < roomInfo.length; i++) {
			for(int j = 0; j < roomInfo[i].length; j++) {
				if(arr[i][j] != BLOCK) continue;
				if(rangeLimit(i, j, roomInfo.length - 1, roomInfo[i].length - 1)) continue;
				
				int[] up = {i + DIRECTIONS[2][ROW], j + DIRECTIONS[2][COL]};
				int[] down = {i + DIRECTIONS[0][ROW], j + DIRECTIONS[0][COL]};
				int[] left = {i + DIRECTIONS[3][ROW], j + DIRECTIONS[3][COL]};
				int[] right = {i + DIRECTIONS[1][ROW], j + DIRECTIONS[1][COL]};
				
				max = getSum(roomInfo, roomSize, up, down, max);
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