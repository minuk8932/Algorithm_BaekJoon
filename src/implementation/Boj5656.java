package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5656번: 비교 연산자
 *
 *	@see https://www.acmicpc.net/problem/5656/
 *
 */
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
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int loop = 0;
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			String mid = st.nextToken();
			int num2 = Integer.parseInt(st.nextToken());
			
			if(TERMINATE.equals(mid)) break;	// 연산자로 E가 들어오면 종료
			
			boolean isTrue = false;
			
			switch (mid) {			// 연산자에 따른 결과를 isTrue에 담음
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
			
			// 버퍼에 isTrue 값을 담고
			sb.append(CASE).append(++loop).append(COLON).append(isTrue).append(NEW_LINE);
		}
		
		System.out.println(sb.toString()); // 결과값 한번에 출력
	}
}
