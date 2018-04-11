package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4504번 : 배수 찾기
 *
 *	@see https://www.acmicpc.net/problem/4504/
 *
 */
public class Boj4504 {
	private static final String END_LINE = ".\n";
	private static final String UNCORRECT = " is NOT a multiple of ";
	private static final String CORRECT = " is a multiple of ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(true){
			int x = Integer.parseInt(br.readLine());
			
			if(x == 0){					// 입력으로 0이 들어오면 반복문 종료
				break;
			}
			
			// x가 n의 배수인지 확인하고 그에따라 출력값을 달리해 버퍼에 저장
			sb.append(x).append(x % n == 0 ? CORRECT : UNCORRECT).append(n).append(END_LINE);
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
