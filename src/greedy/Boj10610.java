package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author minchoba
 *	백준 10610번 : 30
 *
 *	@see https://www.acmicpc.net/problem/10610
 *
 */

public class Boj10610 {
	private static final int UNREAL = -1;				// 30의 배수가 아닌 경우
	private static final char HAS_ZERO = '0';		// 해당 숫자에 0이 포함되어있는지 확인
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nums = br.readLine();
		int leng = nums.length();	
		boolean chkTen = false;						// 0이 포함되었는지 확인 할 진리변수
		
		char[] num = nums.toCharArray();		// 문자열로 받은 값을 1개씩 캐릭터 배열에 넣어줌
		int[] mid = new int[num.length];			// 캐릭터 배열의 값을 받을 정수형 배열
		
		for(int i = 0; i < num.length; i++){
			mid[i] = Character.getNumericValue(num[i]);		// 형변환을 통한 값 할당
		}
		Arrays.sort(mid);													// 오름차순 정렬
		
		for(int i = 0; i < leng; i++){
			if(HAS_ZERO == nums.charAt(i)){							// 해당 문자열에 0이 존재하는가? -> 10의 배수를 만들 수 있는지 확인
				chkTen = true;
			}
		}
		
		int sum = 0;
		StringBuilder sb = new StringBuilder();
		
		if(chkTen){																// 0이 존재 했을 경우
			for(int i = 0; i < mid.length; i++){
				sum += mid[i];												/**
				 																	*	자릿수 별로 모두 다 더해줌
				 																	*	-> 3의 배수는 모든 자릿수를 더했을 때 3의 배수가 나온다.
				 																	*/
			}
			
			if(sum % 3 == 0){												// 다 더한 값이 3의 배수인가?
				for(int i = mid.length - 1; i >= 0; i--){
					sb.append(mid[i]);										// 참이면, 해당 값들을 역순으로 1열 나열 : 조건을 만족하는 숫자 중 가장 큰 수를 가려내기 위함.
				}
				System.out.println(sb.toString());					// 값 출력
			}
			else{
				System.out.println(UNREAL);							// 다 더한 값이 3의 배수가 아니다 : -1 출력
			}
		
		}
		else{
			System.out.println(UNREAL);								// 위에서 해당 문자열에 0이 포함되지 않았다 : -1 출력
		}
		
	}
}
