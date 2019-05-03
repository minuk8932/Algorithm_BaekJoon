package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13417번: 카드 문자열
 *
 *	@see https://www.acmicpc.net/problem/13417/
 *
 */
public class Boj13417 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			char[] words = new char[N];
			for(int i = 0; i < N; i++) {
				words[i] = st.nextToken().charAt(0);
			}
			
			String str = "";
			char left = ' ';
			
			for(char c: words) {
				if(left == ' ') {			// 초기
					str += c;
					left = c;
				}
				else {
					if(left >= c) {			// 왼쪽 가장 작은 문자보다 크기가 작거나 같은 경우
						str = c + str;
						left = c;
					}
					else {
						str += c;
					}
				}
			}
			
			sb.append(str).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
}
