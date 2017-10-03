package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// DP 1로 만들기
public class boj1463 {
	private static final int MAX = 1_000_001;
	private static final int ZERO = 0;
	private static final int ONE = 1;
	
	public static void main(String[] args) throws Exception {
		int[] token = new int[MAX];
		
		token[1] = ZERO;
		token[2] = ONE;
		token[3] = ONE;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int val = Integer.parseInt(br.readLine());
		
		for(int i = 4; i <= val; i++){
			
			int res = 0;
			
			if(i%3 == ZERO){
				res = token[i/3];
			}
			
			if(i%2 == ZERO){
				if(res != ZERO){
					res = Math.min(token[i/2], res);
				}
				else{
					res = token[i/2];
				}
			}
			
			if(res == ZERO){
				res = token[i-1];
			}
			else{
				res = Math.min(res, token[i-1]);
			}
			token[i] = res + 1;
		}
		
		System.out.println(token[val]);
	}
}
