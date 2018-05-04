package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 14726번: 신용카드 판별
 *
 *	@see https://www.acmicpc.net/problem/14726/
 *
 */
public class Boj14726 {
	private static final String NEW_LINE = "\n";
	private static final String VALID = "T";
	private static final String INVALID = "F";
	
	private static int TEN = 10;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			char[] credit = br.readLine().toCharArray();			// 신용 카드 번호를 캐릭터 배열에 담아줌
			int sum = 0;
			
			for(int i = credit.length - 1; i >= 0; i--){	
				int num = Character.getNumericValue(credit[i]);	// 뒤에서부터 문자를 받아와 숫자로 변형하고
				
				if(i % 2 == 0){							// 뒤에서 짝수번째 숫자들만
					num *= 2;							// 2배 한 후 그 결과가 10보다 크다면
					
					if(num >= TEN){
						num = (num % TEN) + 1;	// 자릿수끼리 더함
					}
				}
				
				sum += num;				// 위의 연산이 모두 끝난 후 갱신된 카드번호의 각자리마다 더한 후
			}
			
			sb.append(sum % TEN == 0 ? VALID : INVALID).append(NEW_LINE);	// 합이 10으로 나누어 떨어지면 T, 아니면 F를 버퍼에 저장
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
