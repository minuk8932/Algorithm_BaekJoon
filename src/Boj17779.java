import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17779 {
	private static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int total = 0;
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}
		
		System.out.println(getDiff(N, total));
	}
	
	private static int getDiff(int n, int sum) {		
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {				
				for(int x = 1; x < n; x++) {
					for(int y = 1; y < n; y++) {
						int region1 = getRegion(x, row, col);
						int region2 = getRegion(y, row, col);
						int region3 = getRegion(y, row, col);
						int region4 = getRegion(x, row, col);
					}
				}
			}
		}
		
		return 0;
	}
	
	private static int getRegion(int x, int row, int col) {
		int sum = 0;
		
		for(int i = 0; i <= row + x; i++) {
			for(int j = 0; j <= col + x + 1; j++) {
				if(i + j > x + 1) break;
				
				sum += map[i][j];
			}
		}
		
		
		
		return 0;
	}
}
