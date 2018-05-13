import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5549 {
	private static char[][] map = null;
	
	private static final int INF = 100_000;
	
	private static final char JUN = 'J';
	private static final char OCE = 'O';
	private static final char ICE = 'I';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		map = new char[M][N];
		
		for(int i = 0; i < M; i++){
			String line = br.readLine();
			
			for(int j = 0; j < N; j++){
				map[i][j] = line.charAt(j);
			}
		}
		
		int[] search = new int[INF];
		
		
		
	}
}
