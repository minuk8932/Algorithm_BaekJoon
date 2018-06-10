package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj9610 {
	private static final String[] PRINT = {"Q1: ", "Q2: ", "Q3: ", "Q4: ", "AXIS: ",};
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] axis = new int[5];
		
		while(n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(x == 0 || y== 0) axis[4]++;
			else if(x > 0 && y > 0) axis[0]++;
			else if(x < 0 && y > 0) axis[1]++;
			else if(x < 0 && y < 0) axis[2]++;
			else axis[3]++;
		}
		
		StringBuilder sb = new StringBuilder();		
		for(int i = 0; i < PRINT.length; i++) {
			sb.append(PRINT[i]).append(axis[i]).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}
