package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 3449번: 해밍거리
 *
 *	@see https://www.acmicpc.net/problem/3449/
 *
 */
public class Boj3449 {
	private static final String ANSWER = "Hamming distance is ";
	private static final String NEW_LINE = ".\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			char[] a = br.readLine().toCharArray();
			char[] b = br.readLine().toCharArray();
			
			int idx = 0;
			int res = 0;
			
			for(char tmp : a) {			// 문자 별 차이로 해밍거리를 구함
				if(tmp != b[idx]) {
					res++;
				}
				
				idx++;
			}
			
			sb.append(ANSWER).append(res).append(NEW_LINE);		// 각 해밍거리를 버퍼에 담음
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
