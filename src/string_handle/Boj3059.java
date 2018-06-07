package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 3059번: 등장하지 않는 문자의 합
 *
 *	@see http://www.acmicpc.net/problem/3059/
 *
 */
public class Boj3059 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			String S = br.readLine();
			
			boolean[] alpha = new boolean[91];		// 65 ~ 90의 배열만 사용
			
			for(char tmp : S.toCharArray()) {		// 해당하는 문자를 아스키값으로 전환해 해당 인덱스의 값을 참으로 변경
				alpha[(int) tmp] = true;
			}
			
			int sum = 0;
			
			for(int i = 65; i < alpha.length; i++) {	// 등장하지 않은 알파벳에 해당하는 인덱스 값을 더한 후
				if(!alpha[i]) {
					sum += i;
				}
			}
			
			sb.append(sum).append(NEW_LINE);		// 버퍼에 담아줌
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
