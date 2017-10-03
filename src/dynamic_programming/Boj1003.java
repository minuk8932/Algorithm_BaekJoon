package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1003 {
	private static int[] chk = new int[2];
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(T-- > 0){
			int num = Integer.parseInt(br.readLine());		
			chk = new int[2];
			
			fibonacci(num);			
			sb.append(chk[0]).append(SPACE).append(chk[1]).append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}
	public static int fibonacci(int n) {
	    if (n==0) {
	        chk[n]++;
	        return 0;
	        
	    } 
	    else if (n==1) {
	        chk[n]++;
	        return 1;
	        
	    } 
	    else {
	        return fibonacci(n - 1) + fibonacci(n - 2);
	    }
	}

}
