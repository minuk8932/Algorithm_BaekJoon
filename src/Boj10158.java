import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10158 {
	private static final String SPACE = " ";
	
	private static int rowAdd = 1;
	private static int colAdd = 1;
	
	private static int[][] isVisited = null;
	private static int h = 0;
	private static int w = 0;
	private static int time = 0;
	
	private static Point res = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		isVisited = new int[h + 1][w + 1];
		
		st = new StringTokenizer(br.readLine(), SPACE);
		Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		time = Integer.parseInt(br.readLine());
		
		dfs(start);
//		System.out.println(res.row + SPACE + res.col);
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	private static void dfs(Point s){
		
		System.out.println(s.row + " " + s.col);
		
		if(s == null){
			return;
		}
		
		isVisited[s.row][s.col] = 1;
		
		int nextRow = s.row + rowAdd;
		int nextCol = s.col + colAdd;
		
		if(nextRow < 0 || nextRow > h){
			rowAdd = nextRow < 0 ? 1 : -1;
			nextRow += (rowAdd * 2);
		}
		
		if(nextCol < 0 || nextCol > w){
			colAdd = nextCol < 0 ? 1 : -1;
			nextCol += (colAdd * 2);
		}
		
		if(isVisited[nextRow][nextCol] == 0){
			isVisited[nextRow][nextCol] = isVisited[s.row][s.col] + 1;
				
			System.out.println(nextRow + SPACE + nextCol);
				
			dfs(new Point(nextRow, nextCol));
		}
		
		if(isVisited[nextRow][nextCol] == time){
			res = new Point(nextRow, nextCol);
			return;
		}
	}
}
