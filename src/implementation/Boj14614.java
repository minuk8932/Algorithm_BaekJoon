package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14614번: Calculate!
 *
 *	@see https://www.acmicpc.net/problem/14614/
 *
 */
public class Boj14614 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		String C = st.nextToken();
		int leng = C.length();
		int last = Character.getNumericValue(C.charAt(leng - 1)); // 입력된 문자열 맨 끝의 값을 정수형으로 받아옴

		if(last % 2 == 1) {				// 해당 문자열이 홀수인 경우
			System.out.println(A ^ B);
		}
		else {							// 짝수인 경우
			System.out.println(A);
		}
	}
}
