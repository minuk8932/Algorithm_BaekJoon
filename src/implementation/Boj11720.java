package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 11720번: 숫자의 합
 *
 *	@see https://www.acmicpc.net/problem/11720/
 *
 */
public class Boj11720 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int res = 0;
		String line = br.readLine();
		
		for(char num : line.toCharArray()){				// 향상된 포문을 통해 하나씩 쪼개어 받아와서
			res += Character.getNumericValue(num);		// 숫자로 바꾸고 결과 변수에 합해줌
		}
		
		System.out.println(res);		// 결과값 출력
	}
}
