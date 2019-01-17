import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2780 {
	private static final int MOD = 1_234_567;
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int[][] DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	private static final int[][] DOOR_LOCK = {{1, 2, 3},
											{4, 5, 6}, 
											{7, 8, 9},
											{0, -1,-1}};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int length = Integer.parseInt(br.readLine());
			
			
		}
		
		System.out.println(sb);
	}
}
