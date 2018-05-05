package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 6378번: 디지털 루트
 *
 *	@see https://www.acmicpc.net/problem/6378/
 *
 */
public class Boj6378 {
	private static final String ZERO = "0";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		
		while(true){
			String num = br.readLine();
			
			if(num.equals(ZERO)){			// 입력값이 0 이면 테스트 케이스 종료
				break;
			}
			
			long res = 0;			// 결과 변수
			
			while(true){
				int leng = num.length();		// 문자열로 받은 숫자의 길이를 구함
				
				if(leng == 1){								// 길이가 1이면, 해당 숫자를 바로 res에 정수형으로 담고 반복문 종료 (ex: 1~9사이의 입력이 주어진 경우 대비)
					res = Integer.parseInt(num);
					break;
				}
				
				res = 0;
				for(int i = 0; i < leng; i++){											// 그 외의 경우
					res += Character.getNumericValue(num.charAt(i));		// 자릿수별로 정수형으로 변환해 결과값으로 담아줌
				}
				
				num = String.valueOf(res);			// 해당 값의 길이를 다시 확인하기위해 문자열로 변환
			}

			sb.append(res).append(NEW_LINE);		// 버퍼에 케이스 별로 담고
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
