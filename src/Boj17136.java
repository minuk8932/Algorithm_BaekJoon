import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17136 {
	private static final int[] COUNT = {5, 5, 5, 5, 5};
	
	private static class Point{
		int row;
		int col;
		int count;
		int[][] copy;
		int[] used;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		public Point(int row, int col, int count, int[][] copy, int[] used) {
			this.row = row;
			this.col = col;
			this.count = count;
			this.copy = copy;
			this.used = used;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] bg = new int[10][10];
		
		Point start = new Point(-1, -1);
		
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 10; j++) {
				bg[i][j] = Integer.parseInt(st.nextToken());
				if(bg[i][j] == 1 && start.row == -1) start = new Point(i, j);
			}
		}
		
		System.out.println(getMinCount(bg, start));
	}
	
	private static int getMinCount(int[][] arr, Point start) {
		int result = -1;
		if(start.row == -1) return 0;
		
		int[] use = new int[5];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(start.row, start.col, 0, getNewPaper(arr), use));
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			if(result != -1 && current.count > result) continue;
			
			if(zero(current.copy)) {
				if(result == -1) result = current.count;
				else result = Math.min(result, current.count);
			}
			
			for (int size = 0; size < 5; size++) {
	            if (current.used[size] <= COUNT[size] && areaSize(current.copy, size + 1, current.row, current.col)) {
	            	Point next = new Point(0, 0, 0, null, null);
	            	
	            	current.used[size] += 1;
	            	next.used = usedInit(current.used);
	                
	                next.copy = getNewPaper(current.copy); 
	                next.copy = erase(size + 1, current.row, current.col, current.copy);
	                next.count = current.count + 1;
	                
	                Point nextStart = getStart(next.copy);
	            	if(nextStart == null) continue;
		                
	            	next.row = nextStart.row;
	            	next.col = nextStart.col;
	                
	                q.offer(new Point(next.row, next.col, next.count, next.copy, next.used));
	            }
	        }
		}
		
		return result;
	}
	
	private static boolean areaSize(int[][] arr, int size, int r, int c) {
		for(int row = r; row < r + size; row++) {
			for(int col = c; col < c + size; col++) {
				if(row > 9 || col > 9 || arr[row][col] == 0) return false;
			}
		}
		
		return true;
	}
	
	private static boolean zero(int[][] arr) {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(arr[i][j] != 0) return false;
			}
		}
		
		return true;
	}
	
	private static int[][] getNewPaper(int[][] arr){
		int[][] init = new int[10][10];
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				init[i][j] = arr[i][j];
			}
		}
		
		return init;
	}
	
	private static int[] usedInit(int[] arr) {
		int[] init = new int[5];
		
		for(int i = 0; i < 5; i++) {
			init[i] = arr[i];
		}
		
		return init;
	}
	
	private static int[][] erase(int size, int r, int c, int[][] arr) {
		for(int row = r; row < r + size; row++) {
			for(int col = c; col < c + size; col++) {
				arr[row][col] = 0;
			}
		}
		
		return arr;
	}
	
	private static Point getStart(int[][] arr) {
		for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (arr[row][col] == 0) continue;
                return new Point(row, col);
            }
        }
		
		return null;
	}
}
