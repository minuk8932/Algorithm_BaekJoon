import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1799 {
	private static int N = 0;
	private static int result = 0;
	private static int[] map = null;
	
	private static boolean[][] blocked;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		blocked = new boolean[N + 1][N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j < N + 1; j++) {
				int val = Integer.parseInt(st.nextToken());
				if(val == 0) blocked[i][j] = true;
			}
		}
		
		for(int i = 1; i < N + 1; i++){
			for(int j = 1; j < N + 1; j++) {
				map = new int[N + 1];
				map[i] = j;
				
				dfs(i, j);
			}
		}
		
		System.out.println(result);
	}

	private static void dfs(int row, int col){
		if(col == N + 1){
			result++;
			return;
		}
		
		for(int i = 1; i < N + 1; i++){
			if(blocked[i][col]) continue;
			map[i] = col;
				
			if(isPossible(i)) dfs(i, col + 1);
			else map[i] = 0;
		}
	}
	
	private static boolean isPossible(int pos){
		for(int i = 1; i < pos; i++){
			if(Math.abs(pos - i) == Math.abs(map[pos] - map[i])) return false;
		}
		
		return true;
	}
}
