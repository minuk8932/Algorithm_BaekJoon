import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj9205 {
	private static final String SPACE = " ";
	private static final String H = "happy";
	private static final String S = "sad";
	
	private static final int MAX = 100_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0){
			int n = Integer.parseInt(br.readLine());
			int[][] fest = new int[3][n + 3];
			
			for(int i = 1; i < fest.length + 1; i++){
				StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
				fest[1][i] = Integer.parseInt(st.nextToken());
				fest[2][i] = Integer.parseInt(st.nextToken());
			}
			
			for(int v = 1; v < fest.length + 1; v++){
				for(int s = 1; s < fest.length + 1; s++){
					for(int e = 1; e < fest.length + 1; e++){
						
					}
				}
			}
		}
	}

}
