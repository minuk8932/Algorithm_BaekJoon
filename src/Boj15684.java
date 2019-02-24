import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15684 {
	private static boolean[][] map;
	private static int result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		map = new boolean[H + 1][N + 1];
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			map[x][y] = true;
		}
			
		dfs(H, N, 0, 1, 1);
		System.out.println(result > 3 ? -1 : result);
	}
	
	private static void dfs(int N, int M, int count, int row, int col) {
		if(count >= result) return;
		if(move(N, M)) {
			result = count;
			return;
		}
		
	}
	
	private static boolean move(int N, int M) {		
		for(int i = 1; i < M + 1; i++) {
			int target = i;
			
			for(int j = 1; j < N + 1; j++) {
				if(map[i][j]) target++;
				else if(map[i][j - 1]) target--;
			}
			
			if(target != i) return false;
		}
		
		return true;
	}
}
