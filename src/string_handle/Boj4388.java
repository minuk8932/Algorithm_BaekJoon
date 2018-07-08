package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 4388번: 받아올림
 *
 *	@see https://www.acmicpc.net/problem/4388/
 *
 */
public class Boj4388 {
	private static final String TERMINATE = "0 0";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			if(TERMINATE.equals(line)) break;			// "0 0" 입력시 종료
			
			StringTokenizer st = new StringTokenizer(line);
			char[] a = st.nextToken().toCharArray();
			char[] b = st.nextToken().toCharArray();
			
			int diff = a.length - b.length;
			
			int leng = 0;
			if(diff > 0) {			// 반복할 길이와 그때의 차이를 구함
				leng = a.length;
			}
			else {
				 leng = b.length;
				 diff = -diff;
			}
			
			int res = 0;
			int carry = 0;
			
			for(int i = leng - 1; i >= diff; i--) {
				// 각 배열을 뒤에서부터 한 자리씩 더하면서 carry값을 합해줌
				int tmp = (a.length == leng ? (a[i] - '0') + (b[i - diff] - '0') : (a[i - diff] - '0') + (b[i] - '0')) + carry;
				
				if(tmp >= 10) {		// 현재 한자리의 합이 10보다 크거나 같으면 결과 +1 및 carry = 1로 갱신
					res++;
					carry = 1;
				}
				else {				// 10보다 작다면 carry 할 것이 없음
					carry = 0;
				}
			}
			
			for(int i = 0; i < diff; i++) {			// 겹치는 자리 계산 종료 후, 더 긴 문자열에서, 캐리가 1인 경우 연산을 지속해줌
				int tmp = (a.length == leng ? a[i] - '0' : b[i] - '0') + carry;
				
				if(tmp >= 10) res++;		// 합이 10보다 크다면 결과 +1
				else break;
			}
			
			sb.append(res).append(NEW_LINE);		// 각각의 결과를 버퍼에 담은 후
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
