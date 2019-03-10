package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1864번: 문어 숫자
 *
 *	@see https://www.acmicpc.net/problem/1864/
 *
 */
public class Boj1864 {
	private static final String TERMINATE = "#";
	private static final String NEW_LINE = "\n";
	
	private static final char[] FORMAT = {'-', '\\', '(', '@', '?', '>', '&', '%', '/'};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			if(line.equals(TERMINATE)) break;
			
			sb.append(getDecimal(line.toCharArray())).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int getDecimal(char[] word) {
		int result = 0;
		
		for(int i = 0; i < word.length; i++) {
			int num = 0;
			
			for(int index = 0; index < FORMAT.length; index++) {
				if(word[i] == FORMAT[index]) {						// 대응 수 찾기
					num = index;
					break;
				}
			}
			
			result += Math.pow(8, word.length - 1 - i) * (num == 8 ? -1 : num);		// 음수를 제외한 8진수 계산
		}
		
		return result;
	}
}
