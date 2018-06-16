package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1871번: 좋은 자동차 번호판
 *
 *	@see https://www.acmicpc.net/problem/1871/
 *
 */
public class Boj1871 {
	private static final String HYPEN = "-";
	private static final String GOOD = "nice";
	private static final String BAD = "not nice";
	private static final String NEW_LINE = "\n";
	
	private static final int VALUE = 100;
	private static final int DECAX = 26;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), HYPEN);
			char[] alpha = st.nextToken().toCharArray();	
			int num = Integer.parseInt(st.nextToken());
			
			int alphaSum = 0;
			for(int i = 0; i < alpha.length; i++) {		// 알파벳 1개씩 가져와 26진수 계산
				alphaSum += (int) Math.pow(DECAX, alpha.length - i - 1) * (alpha[i] - 65);
			}
			
			int res = Math.abs(alphaSum - num); 		// 앞자리와 뒷자리의 숫자를 뺀 값의 절댓값
			
			sb.append(res <= VALUE ? GOOD : BAD).append(NEW_LINE);	// 절댓값이 100이하이면 good 그 외엔 bad를 버퍼에 담음
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
