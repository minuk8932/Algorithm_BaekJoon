package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15683번: 감시
 *
 *	@see https://www.acmicpc.net/problem/15683/
 *
 */
public class Boj15683 {	
	private static int N, M;
	private static int min = Integer.MAX_VALUE;
	private static int[][] map;
	
	private static final int EMPTY = 0, BLOCK = 6, WATCH = -1;
	
	private static class Cam{
		int type;
		int row;
		int col;
		
		public Cam(int type, int row, int col) {
			this.type = type;
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		ArrayList<Cam> list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] > 0 && map[i][j] <= 5) {
					list.add(new Cam(map[i][j], i, j));
				}
			}
		}
		
		supervise(list, map, 0);
		System.out.println(min);
	}
	
	private static void supervise(ArrayList<Cam> cams, int[][] arr, int current) {		
		int[][] isVisited = new int[N][M];
		
		if(current == cams.size()) {				// 캠 다돌면 최솟값
			min = Math.min(getDeadZone(arr), min);
		}
		else {
			Cam cam = cams.get(current);
			
			switch(cam.type) {
			case 1:
				for (int way = 0; way < 4; way++) {					// 1번 cam 4way
					for (int row = 0; row < N; row++) {
						isVisited[row] = Arrays.copyOf(arr[row], M);
					}
                    
					directSearch(isVisited, cam.row, cam.col, way);
                    supervise(cams, isVisited, current + 1);
                }
				
				break;
		
			case 2:
				for (int way = 0; way < 2; way++) {					// 2번 cam 2way
					for (int row = 0; row < N; row++) {
						isVisited[row] = Arrays.copyOf(arr[row], M);
					}
                    
					directSearch(isVisited, cam.row, cam.col, way);
					directSearch(isVisited, cam.row, cam.col, way + 2);
                    supervise(cams, isVisited, current + 1);
                }
				
				break;
		
			case 3:
				for (int way = 0; way < 4; way++) {					// 3번 cam 4way
					for (int row = 0; row < N; row++) {
						isVisited[row] = Arrays.copyOf(arr[row], M);
					}
                    
					directSearch(isVisited, cam.row, cam.col, way);
					directSearch(isVisited, cam.row, cam.col, (way + 1) % 4);
                    supervise(cams, isVisited, current + 1);
                }
				
				break;
				
			case 4:
				for (int way = 0; way < 4; way++) {					// 4번 cam 4way
					for (int row = 0; row < N; row++) {
						isVisited[row] = Arrays.copyOf(arr[row], M);
					}
                    
					directSearch(isVisited, cam.row, cam.col, way);
					directSearch(isVisited, cam.row, cam.col, (way + 1) % 4);
					directSearch(isVisited, cam.row, cam.col, (way + 2) % 4);
                    supervise(cams, isVisited, current + 1);
                }
				
				break;
				
			case 5:
				for(int row = 0; row < N; row++) {
					isVisited[row] = Arrays.copyOf(arr[row], M);
				}
				
				directSearch(isVisited, cam.row, cam.col, 0);		// 5번 cam 4way
				directSearch(isVisited, cam.row, cam.col, 1);
				directSearch(isVisited, cam.row, cam.col, 2);
				directSearch(isVisited, cam.row, cam.col, 3);
				supervise(cams, isVisited, current + 1);
				
				break;
				
			}
		}
	}
	
	private static int getDeadZone(int[][] arr) {
		int count = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(arr[i][j] == EMPTY) count++;
			}
		}
		
		return count;
	}
	
	private static void directSearch(int[][] isVisited, int row, int col, int direction) {
		switch(direction) {
		case 0:
			for(int i = col; i >= 0; i--) {
				if(map[row][i] == BLOCK) break;
				isVisited[row][i] = WATCH;		// 서쪽 감시중
			}
			break;
			
		case 1:
			for(int i = row; i >= 0; i--) {
				if(map[i][col] == BLOCK) break;
				isVisited[i][col] = WATCH;		// 북쪽 감시중
			}
			break;
			
		case 2:
			for(int i = col; i < M; i++) {
				if(map[row][i] == BLOCK) break;
				isVisited[row][i] = WATCH;		// 동쪽 감시중
			}
			break;
			
		case 3:
			for(int i = row; i < N; i++) {
				if(map[i][col] == BLOCK) break;
				isVisited[i][col] = WATCH;		// 남쪽 감시중
			}
			break;
		}
	}
}