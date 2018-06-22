package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10178번: 할로윈의 사탕
 *
 *	@see https://www.acmicpc.net/problem/10178/
 *
 */
public class Boj10178 {
	private static final String ANS_1 = "You get ";
	private static final String ANS_2 = " piece(s) and your dad gets ";
	private static final String ANS_3 = " piece(s).\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int candy = Integer.parseInt(st.nextToken());
			int man = Integer.parseInt(st.nextToken());
			
			sb.append(ANS_1).append(candy / man).append(ANS_2).append(candy % man).append(ANS_3); // 나머지와 몫을 버퍼에 담고
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
