package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6996번: 애너그램
 *
 *	@see https://www.acmicpc.net/problem/6996/
 *
 */
public class Boj6996 {
	private static final String AND = " & ";
	private static final String ANA = " are anagrams.\n";
	private static final String NOT_ANA = " are NOT anagrams.\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String word1 = st.nextToken().toLowerCase();				// 각 입력된 문자를 소문자로 변경
			String word2 = st.nextToken().toLowerCase();
			
			int[] arr1 = new int[26];
			int[] arr2 = new int[26];
			
			for(char tmp : word1.toCharArray()) {		// 알파벳에 맞춰 인덱스에 값을 더해줌
				arr1[tmp - 'a']++;
			}
			
			for(char tmp : word2.toCharArray()) {
				arr2[tmp - 'a']++;
			}
			
			boolean isTrue = true;
			
			for(int i = 0; i < 26; i++) {		// 두 배열의 각 인덱스 0(a)~25(z)까지 비교를 하며,
				if(arr1[i] != arr2[i]) {		// 다른값이 하나라도 있으면 isTrue = 거짓
					isTrue = false;
				}
			}
			// 버퍼에 isTrue 값에 따라 형식을 담아줌
			sb.append(word1).append(AND).append(word2).append(isTrue ? ANA : NOT_ANA);
		}
		
		System.out.println(sb.toString());	// 결과값 한번에 출력
	}
}
