import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17136 {
	private static int result = 50;
	private static int[] size = new int[5];
	
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
		boolean[][] paper = new boolean[10][10];
		
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 10; j++) {
				paper[i][j] = st.nextToken().equals("1");
			}
		}
		
		backTracking(paper);
		System.out.println(result > 25 ? -1: result);
	}

	private static boolean allCovered (boolean[][] p) {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(p[i][j]) return !p[i][j];
			}
		}

		return true;
	}
	
	private static void backTracking(boolean[][] p) {
		if(allCovered(p)) {
			int sum = 0;
			for(int i = 0; i < 5; i++) {
				sum += size[i];
			}

			result = Math.min(result, sum);
			return;
		}

		Point target;

		for(int i = 0; i < 5; i++) {
			if(size[i] >= 5) continue;
			target = covering(p, i + 1);

			if(target.row != -1 && target.col != -1){
				size[i] += 1;
				backTracking(p);
				recovery(p, target, i + 1);
				size[i] -= 1;
			}
		}
	}
	
	private static Point covering(boolean[][] p, int size) {
		Point start = new Point(-1, -1);

		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(!p[i][j]) continue;
				start = new Point(i, j);
			}
		}

		if(start.row == -1 && start.col == -1) return start;
		if(start.row + size >= 10 || start.col + size >= 10) return new Point(-1, -1);

		for(int i = start.row; i < start.row + size; i++) {
			for(int j = start.col; j < start.col + size; j++) {
				if(!p[i][j]) return new Point(-1, -1);
			}
		}

		for(int i = start.row; i < start.row + size; i++) {
			for(int j = start.col; j < start.col + size; j++) {
				p[i][j] = false;
			}
		}

		return start;
	}

	private static void recovery(boolean[][] p, Point start, int size) {
		for(int row = start.row; row < start.row + size; row++) {
			for(int col = start.col; col < start.col + size; col++) {
				p[row][col] = true;
			}
		}
	}
}
