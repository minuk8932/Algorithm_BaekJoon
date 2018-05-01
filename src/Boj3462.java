import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj3462 {
	private static final String SPACE = " ";
	private static final String END_LINE = "\n";
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int D = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(D-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int[][] bSter = new int[n][m];
			bSter[0][0] = 1;
			
			for(int i = 0; i < n; i++){
				bSter[i][0] = 0;
			}
			for(int i = 0; i < m; i++){
				bSter[0][i] = 0;
			}
			
			for(int i = 1; i < n; i++){
				for(int j = 1; j < m; j++){
					bSter[i][j] = j * bSter[i - 1][j] + bSter[i - 1][j - 1];
				}
			}
			
			
			sb.append(bSter[n - 1][m - 1]).append(END_LINE);
		}
		br.close();
		
		System.out.println(sb.toString());
	}
}
