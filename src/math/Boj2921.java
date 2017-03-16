package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2921 {
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int set = 0;
		
		for(int i = 1; i <= N; i++){
			for(int j = i; j <= i*2; j++){
				set += j;
			} 
		}
		sb.append(set).append(NEW_LINE);
		System.out.println(sb.toString());
	}

}
