package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11023번: 더하기 3
 *
 *	@see https://www.acmicpc.net/problem/11023/
 *
 */
public class Boj11023 {
	public static void main(String[] args)throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int sum = 0;
		
		while(true){
			try{												// 토큰을 떼와서 총 합을 구함
				String line = st.nextToken();
				sum += Integer.parseInt(line);
			}
			catch (Exception e){						// 반복문 실행 중 예외가 발생 할 경우 무한루프 종료
				break;
			}
		}
		
		System.out.println(sum);			// 결과 값 출력
	}
}
