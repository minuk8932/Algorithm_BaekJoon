package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 4287번: Word Ratios
 *
 *	@see https://www.acmicpc.net/problem/4287/
 *
 */
public class Boj4287 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String TERMINATE = "#";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String input = br.readLine();
			if(input.equals(TERMINATE)) break;
			
			StringTokenizer st = new StringTokenizer(input);
			String[] form = {st.nextToken(), st.nextToken()};
			String target = st.nextToken();
			
			sb.append(form[0]).append(SPACE).append(form[1]).append(SPACE).append(target).append(SPACE).append(answer(form, target)).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
	
	private static String answer(String[] arr, String str) {
		StringBuilder sb = new StringBuilder();
		int leng = arr[0].length();
		
		for(int i = 0; i < leng; i++) {
			int sum = arr[1].charAt(i) - arr[0].charAt(i) + str.charAt(i);
			
			if(sum < 97) sum += 26;
			if(sum > 122) sum = (sum % 123) + 'a';
			
			sb.append((char) sum);
		}
		
		return sb.toString();
	}
}
