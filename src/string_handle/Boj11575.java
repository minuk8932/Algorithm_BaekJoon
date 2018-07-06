package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11575번: Affine Cipher
 *
 *	@see https://www.acmicpc.net/problem/11575/
 *
 */
public class Boj11575 {
	private static final String NEW_LINE = "\n";
	private static final char I_TO_C = 'A';
	private static final int MOD = 26;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			String s = br.readLine();
			for(char word : s.toCharArray()) {
				char tmp = (char) (((a * (word - I_TO_C) + b) % MOD) + I_TO_C);	// (a * X + B) % MOD 를 계산 후 문자형으로 변형
				sb.append(tmp);	// 변형된 문자를 버퍼에 담음
			}
			
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb.toString());	// 결과 값 한번에 출력
	}
}
