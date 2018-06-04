package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 3062번: 수 뒤집기
 *
 *	@see https://www.acmicpc.net/problem/3062/
 *
 */
public class Boj3062 {
	private static final String Y = "YES";
	private static final String NO = "NO";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			String line = br.readLine();
			int size = line.length();
			int N = Integer.parseInt(line);
			int sum = 0;
			
			for(int i = 0; i < size; i++) {		// 역순으로 된 값을 더한 후
				sum += Character.getNumericValue(line.charAt(i)) * Math.pow(10, i);
			}
			
			sum += N;		// 원래 수도 더함
			
			String res = String.valueOf(sum);	// 그 결과를 문자열로 바꾼 후
			boolean isWrong = false;
			int leng = res.length() / 2;
			int maxLeng = res.length();
			
			for(int i = 0; i < leng; i++) {							// 인덱스마다 비교
				if(res.charAt(i) != res.charAt(maxLeng - 1 - i)) {	// 만약 양쪽으로 비교하면서 중앙으로 들어가다가 서로 다른값이 하나라도 있다면,
					isWrong = true;					// 대칭이 아님을 표시해줌
				}
			}
			
			sb.append(isWrong ? NO : Y).append(NEW_LINE);	// 진리 변수의 값에 따라 버퍼에 Yes or No를 담아줌
		}
		
		System.out.println(sb.toString());	// 결과값 한번에 출력
	}
}
