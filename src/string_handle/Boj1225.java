package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1225번: 이상한 곱셈
 *
 *	@see https://www.acmicpc.net/problem/1225/
 *
 */
public class Boj1225 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String A = st.nextToken();
		String B = st.nextToken();
		
		long res = 0;
		for(char aTmp : A.toCharArray()) {			// A, B의 곱의 합을 구해줌
			for(char bTmp : B.toCharArray()) {
				res += ((aTmp - '0') * (bTmp - '0'));
			}
		}
		
		System.out.println(res);		// 결과 값 출력
	}
}
