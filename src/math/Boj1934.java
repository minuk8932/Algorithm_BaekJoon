package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1934번: 최소공배수
 *
 *	@see https://www.acmicpc.net/problem/1934/
 *
 */
public class Boj1934 {	
	private static final String NEW_LINE = "\n";
	private static int A = 0;
	private static int B = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			int res = lcm(A, B);				// 최소공배수 반환 메소드를 통해 결과 변수에 저장
			
			sb.append(res).append(NEW_LINE);	// 버퍼에 결과를 담고
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 최소 공배수 반환 메소드: 유클리드 호제법 이용
	 * @param 비교할 숫자 2개
	 * @return 수식을 통해 최소 공배수 반환
	 */
	private static int lcm(int num1, int num2) {
		if(num2 == 0) {
			return A * B /num1;		// 최대 공약수를 이용한 최소 공배수 반환
		}

		return lcm(num2, num1 % num2);
	}
}
