package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11815번: 짝수? 홀수?
 *
 *	@see https://www.acmicpc.net/problem/11815/
 *
 */
public class Boj11815 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(N-- > 0) {
			long X = Long.parseLong(st.nextToken());
			long root = (long) Math.sqrt(X);
			root *= root;
			
			sb.append(root == X ? 1 : 0).append(SPACE);	// 제곱수의 경우 1 그 외 0을 버퍼에 담음
		}
		
		System.out.println(sb.toString());	// 결과값 한번에 출력
	}
}
