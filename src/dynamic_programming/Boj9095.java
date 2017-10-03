package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9095 {
	private static final int MAX = 12;
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		int[] token = new int[MAX];
		
		token[1] = 1;
		token[2] = 2;
		token[3] = 4;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			int n = Integer.parseInt(br.readLine());
			
			for(int i = 4; i <= n; i++){
				token[i] = token[i-3] + token[i-2] + token[i-1];
			}
			sb.append(token[n]).append(SPACE);
		}
		System.out.println(sb.toString());
		
	}
}
