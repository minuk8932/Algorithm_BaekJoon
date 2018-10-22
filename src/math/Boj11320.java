package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11320번: 삼각 무늬 1
 *
 *	@see https://www.acmicpc.net/problem/11320/
 *
 */
public class Boj11320 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args)throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());		// 삼각형 한 변의 길이가 1인 것을 기준으로 개수를 구함
			int A = (int) Math.pow(Integer.parseInt(st.nextToken()), 2);
			int B = (int) Math.pow(Integer.parseInt(st.nextToken()), 2);
			
			sb.append(A % B == 0 ? A / B : 0).append(NEW_LINE);			// 두 삼각형의 갯수가 서로 나누어 떨어지면 나눈 몫을 아니면 0을 버퍼에 저장
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}
