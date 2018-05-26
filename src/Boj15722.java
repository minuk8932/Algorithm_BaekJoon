import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj15722 {
	private static final int INF = 1_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Point pos = new Point(0, 0);
		int[] arr = new int[INF];
		int[] dp = new int[INF];
		dp[1] = 2;
		
		for(int i = 2; i < INF; i++){			
			dp[i] = dp[i - 1] + 2 * i;
		}
		
		boolean isP = false;
		
		for(int i = 1; dp[i] < INF; i++){
			for(int j = dp[i - 1] + 1; j < dp[i] + 1; j++){
				if(!isP){
					arr[j] = 1;
				}
				else{
					arr[j] = -1;
				}
			}
			
			isP = !isP;
		}
		
		for(int i = 1; i < n + 1; i++){
			
		}
		
		System.out.println(pos.row + " " + pos.col);
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
}
