package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1769번: 3의 배수
 *
 *	@see https://www.acmicpc.net/problem/1769/
 *
 */
public class Boj1769 {
	private static final String NEW_LINE = "\n";
	private static final String O = "YES";
	private static final String X = "NO";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String line = br.readLine();
		int loop = 0;
		
		while(line.length() > 1) {				// 1자리 수가 될 때까지 반복문 수행
			int num = 0;
			
			for(char tmp : line.toCharArray()) {
				num += (tmp - '0');				// num에 각 회차마다의 합을 담고
			}

			line = num + "";					// 그 값을 다시 문자열로 바꾼 후
			loop++;					// 반복횟수 + 1
		}
		
		// 반복횟수와 최종 수가 3의 배수인지 여부를 버퍼에 담은 후
		sb.append(loop).append(NEW_LINE).append(Integer.parseInt(line) % 3 == 0 ? O : X);
		System.out.println(sb.toString());	// 결과 값 한번에 출력
	}
}
