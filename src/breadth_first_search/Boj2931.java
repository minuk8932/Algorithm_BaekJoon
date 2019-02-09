package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2931번: 가스관
 *
 *	@see https://www.acmicpc.net/problem/2931/
 *
 */
public class Boj2931 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static final char[] PIPES = {'|', '-', '+', '1', '2', '3', '4'};
	private static final char START = 'M';
	private static final char END = 'Z';
	private static final char EMPTY = '.';
	private static final char SPACE = ' ';
	
	private static char answer = ' ';
	private static Point[] point = new Point[2];
	
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == START) point[0] = new Point(i, j);
				if(map[i][j] == END) point[1] = new Point(i, j);
			}
		}

		Point result = bfs(R, C, map, point);
		sb.append(result.row + 1).append(SPACE).append(result.col + 1).append(SPACE).append(answer);
		
		System.out.println(sb);
	}
	
	private static Point bfs(int N, int M, char[][] arr, Point[] s) {
		boolean[][] isVisited = new boolean[N][M];
		
		Point stolen = new Point(-1, -1);
		
		Point start = getAdjacent(N, M, arr, s[0].row, s[0].col, arr[s[0].row][s[0].col], START);
		if(start.row == -1 || start.col == -1) start = getAdjacent(N, M, arr, s[0].row, s[0].col, arr[s[1].row][s[1].col], END);
		
		if(start.row == -1 || start.col == -1) {
			int row = point[1].row - point[0].row == 0 ? point[0].row + 1 : Math.abs(point[1].row - point[0].row);
			int col = point[1].col - point[0].col == 0 ? point[0].col + 1 : Math.abs(point[1].col - point[0].col);
			
			answer = row == point[0].row + 1 ? PIPES[1] : PIPES[0];
			return new Point(row - 1, col - 1);
		}											// 시작과 끝이 평행하게 1칸차이로 인접한 경우
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(start.row, start.col));
		isVisited[start.row][start.col] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			int[][] nextDir = getNext(arr, current, true);		// 다음으로 움직일 방향을 받아옴
			
			for(final int[] DIRECTION: arr[current.row][current.col] == START || arr[current.row][current.col] == END ? DIRECTIONS : nextDir) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
				if(isVisited[nextRow][nextCol]) continue;
				isVisited[nextRow][nextCol] = true;
								
				if(arr[nextRow][nextCol] == START || arr[nextRow][nextCol] == END) continue;
				if(arr[nextRow][nextCol] == EMPTY) stolen = new Point(nextRow, nextCol);			// 손실된 파이프 위치
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		repair(N, M, arr, stolen);		// 손실된 파이프 복구
		return stolen;
	}
	
	private static Point getAdjacent(int N, int M, char[][] arr, int row, int col, char pipe, char target) {
		int nextI = -1, nextJ = -1;
		
		if(arr[row][col] == target) {
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = row + DIRECTION[ROW];
				int nextCol = col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
				if(arr[nextRow][nextCol] == EMPTY) continue;
				
				nextI = nextRow;
				nextJ = nextCol;
			}
		}
		
		return new Point(nextI, nextJ);
	}
	
	private static void repair(int N, int M, char[][] arr, Point target) {
		LinkedList<Character> tmp = new LinkedList<>();
		
		for(char pipe: PIPES) {
			arr[target.row][target.col] = pipe;
			boolean isCorrect = true;
			
			int[][] nextDir = getNext(arr, target, true);
			
			for(final int[] DIRECTION: nextDir) {			// 현재에서 다음으로 다음에서 다시 현재로 되돌아 올 수 있는지 확인
				int nextRow = target.row + DIRECTION[ROW];
				int nextCol = target.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M || arr[nextRow][nextCol] == EMPTY) {
					isCorrect = false;
					continue;
				}
				
				int[][] currentDir = getNext(arr, new Point(nextRow, nextCol), false);
				Point[] current = new Point[4];
				
				for(int i = 0; i < 4; i++) {
					current[i] = new Point (nextRow + currentDir[i][ROW], nextCol + currentDir[i][COL]);
				}
				
				if(!canBack(current, target)) isCorrect = false;		// 파이프를 하나씩 끼워봄
			}
			
			if(isCorrect) tmp.add(pipe);
		}
		
		int size = tmp.size();
		answer = size == 1 ? tmp.remove() : '+';		// 여러개가 가능한 경우 -> '+'
	}
	
	private static int[][] getNext(char[][] arr, Point current, boolean notRepair) {
		int[][] tmp = new int[4][2];
		
		switch(arr[current.row][current.col]) {
		case '|':
			tmp[0][0] = -1;
			tmp[0][1] = 0;
			tmp[1][0] = 1;
			tmp[1][1] = 0;
			break;
		
		case '-':
			tmp[0][0] = 0;
			tmp[0][1] = -1;
			tmp[1][0] = 0;
			tmp[1][1] = 1;
			break;
		
		case '+':
			tmp[0][0] = -1;
			tmp[0][1] = 0;
			tmp[1][0] = 1;
			tmp[1][1] = 0;
			tmp[2][0] = 0;
			tmp[2][1] = -1;
			tmp[3][0] = 0;
			tmp[3][1] = 1;
			break;
		
		case '1':
			tmp[0][0] = 1;
			tmp[0][1] = 0;
			tmp[1][0] = 0;
			tmp[1][1] = 1;
			break;
		
		case '2':
			tmp[0][0] = -1;
			tmp[0][1] = 0;
			tmp[1][0] = 0;
			tmp[1][1] = 1;
			break;
		
		case '3':
			tmp[0][0] = -1;
			tmp[0][1] = 0;
			tmp[1][0] = 0;
			tmp[1][1] = -1;
			break;
		
		case '4':
			tmp[0][0] = 1;
			tmp[0][1] = 0;
			tmp[1][0] = 0;
			tmp[1][1] = -1;
			break;
		}
		
		return tmp;
	}
	
	private static boolean canBack(Point[] current, Point target) {
		for(int i = 0; i < current.length; i++) {
			if(current[i].row == target.row && current[i].col == target.col) return true;
		}
		
		return false;
	}
}
