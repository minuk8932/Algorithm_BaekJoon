import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17136 {	
	private static boolean[][] covered = new boolean[10][10];
	private static int result;
	
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
		int[][] paper = new int[10][10];
		
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 10; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		backTracking(paper, 0, 0, 0, 0, 0);		
		System.out.println();
	}
	
	private static void backTracking(int[][] arr, int size1, int size2, int size3, int size4, int size5) {
		if(size1 < 5) {
			covering(arr, 1);
			backTracking(arr, size1 + 1, size2, size3, size4, size5);
		}
		
		if(size2 < 5) {
			covering(arr, 2);
			backTracking(arr, size1, size2 + 1, size3, size4, size5);
		}
		
		if(size3 < 5) {
			covering(arr, 3);
			backTracking(arr, size1, size2, size3 + 1, size4, size5);
		}
		
		if(size4 < 5) {
			covering(arr, 4);
			backTracking(arr, size1, size2, size3, size4 + 1, size5);
		}
		
		if(size5 < 5) {
			covering(arr, 5);
			backTracking(arr, size1, size2, size3, size4, size5 + 1);
		}
	}
	
	private static void covering(int[][] arr, int size) {
		Point start = new Point(-1, -1);
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(arr[i][j] != 1) continue;
				start = new Point(i, j);
			}
		}
		
		if(start.row == -1 && start.col == -1) return;
		if(start.row + size >= 10 || start.col + size >= 10) return;
		
		
	}
}
