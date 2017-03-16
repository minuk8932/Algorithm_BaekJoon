package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9546 {
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++){
			double k = Double.parseDouble(br.readLine());
			double passenger = 0;
			
			for(int j = 0; j < k; j++){
				passenger = (passenger + 0.5) * 2;
			}
			sb.append((int)passenger).append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}

}
