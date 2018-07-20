package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5656 {
	private static final String TERMINATE = "E";
	private static final String NEW_LINE = "\n";
	private static final String CASE = "Case ";
	private static final String COLON = ": ";
	
	private static final String BIGGER = ">";
	private static final String BIGGER_OR_EQUAL = ">=";
	private static final String SMALLER = "<";
	private static final String SMALLER_OR_EQUAL = "<=";
	private static final String EQUAL = "==";
	private static final String NOT_EQUAL = "!=";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int loop = 0;
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			String mid = st.nextToken();
			int num2 = Integer.parseInt(st.nextToken());
			
			if(TERMINATE.equals(mid)) break;
			
			boolean isTrue = false;
			
			switch (mid) {
			case BIGGER:
				isTrue = num1 > num2 ? true : false;
				break;
				
			case BIGGER_OR_EQUAL:
				isTrue = num1 >= num2 ? true : false;
				break;
				
			case SMALLER:
				isTrue = num1 < num2 ? true : false;
				break;
				
			case SMALLER_OR_EQUAL:
				isTrue = num1 <= num2 ? true : false;
				break;
				
			case EQUAL:
				isTrue = num1 == num2 ? true : false;
				break;
				
			case NOT_EQUAL:
				isTrue = num1 != num2 ? true : false;
				break;
			}
			
			sb.append(CASE).append(++loop).append(COLON).append(isTrue).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}
