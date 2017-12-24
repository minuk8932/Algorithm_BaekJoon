import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj4883 {
	private static final int ROW = 3;
	
	private static final String STOP = "0";
	private static final String SPACE = " ";
	private static final String DOT = ".";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int[][] graph = null;
		int TestCase = 0;
		String loopChecker = null;
		
		while(!(STOP.equals(loopChecker = br.readLine()))){
			int N = Integer.parseInt(loopChecker);
			graph = new int[N + 1][ROW + 1];
			
			for(int i = 1; i < N + 1; i++){
				st = new StringTokenizer(br.readLine(), SPACE);
				for(int j = 1; j < ROW + 1; j++){
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] dp = new int[N + 1];
			
			for(int i = 2; i < N + 1; i++){
				int tmp = 0;
				
				if(i == 2){
					
				}
				else if(2 <= i && i < N){
					
				}
				else{
					
				}
			}
			
			TestCase++;
			sb.append(TestCase).append(DOT).append(SPACE).append(dp[N]).append(NEW_LINE);
		}
		br.close();
		
		System.out.println(sb.toString());
	}
}
