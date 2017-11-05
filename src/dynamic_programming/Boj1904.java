package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1904 {
	private static final int SPARE = 15746;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] tile = new long[N + 1];
		
		if(N==1){
			tile[1] = 1;
		}
		
		if(N==2){
			tile[2] = 2;
		}
		
		if(N > 2){
			tile[1] = 1;
			tile[2] = 2;
			
			for(int i=3; i < N+1; i++){
				tile[i] = (tile[i - 1] + tile[i - 2]) % SPARE;
			}
		}
		
		
		System.out.println(tile[N]);
	}
}
