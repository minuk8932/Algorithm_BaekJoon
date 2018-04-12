package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2739 {
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i < 10; i++){
			sb.append(N+" * "+i+" = "+i*N).append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}

}
