package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15814번: 야바위 대장
 *
 *	@see https://www.acmicpc.net/problem/15814/
 *
 */
public class Boj15814 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str = br.readLine().toCharArray();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int diff = str[a] - str[b];
			
			str[a] -= diff;			// 문자값의 차이를 얻어 낸 후 값을 빼고 더함
			str[b] += diff;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < str.length; i++) {		// 문자열을 버퍼에 담고
			sb.append(str[i]);
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}
