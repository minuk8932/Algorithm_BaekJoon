import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1393 {
	
	private static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Point station = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		st = new StringTokenizer(br.readLine());
		Point current = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		Point move = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		
	}
}
