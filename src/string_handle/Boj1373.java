package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1373번 : 2진수 8진수
 *
 *	@see https://www.acmicpc.net/problem/1373/
 *
 */
public class Boj1373 {
	private static String line = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		line = br.readLine();
		
		System.out.println(binaryToOct());		// 8진수로 변환해주는 메소드를 통해 결과 출력
	}
	
	/**
	 * 2진수 -> 8진수 변환 메소드
	 * 원리: 2진수의 3자리를 10진 계산해서 하나씩 붙여주면 8진수
	 * 
	 * @return 8진수로 변환된 글자를 문자열로 반환
	 */
	private static String binaryToOct(){
		StringBuilder sb = new StringBuilder();
		int leng = line.length();			// 문자열 길이를 받아옴, 외부 반복문 작동 횟수
		int res = 0;						// 결과 8진수를 하나씩 담아줄 변수
		int loop = 0;						// 내부 반복문 작동 횟수
		
		int mod = leng % 3;				// 맨 앞에 변환할 2진수의 자리수
		if(mod == 0){				// mod가 0이면 자릿수가 3으로 딱 떨어짐
			loop = 3;
		}
		else if(mod == 1){		// 1이면 한자리가 남음: 1회 반복문 실행 후 다음부터 3개씩 탐색
			loop = 1;
		}
		else{
			loop = 2;				// 2이면 두자리가 남음: 2회 반복문 실행 후 다음부터 3개씩 탐색
		}
		
		
		for(int i = 0; i < leng;){
			int time = 0;					// 인덱스 체크 변수
			
			for(int j = 0; j < loop; j++){		// loop만큼 돌아가면서(보통 3자리) 단위별로 받아와서 2진수를 10진수로 변환 계산 한 후
				res += Character.getNumericValue(line.charAt(i + j)) * Math.pow(2, loop - 1 - j);
				time++;		// 반복문 실행 횟수만큼 구해서
			}
			 i += time;		// 문자열 인덱스를 실행횟수 만큼 뒤로 밀어줌
					 
			sb.append(res);		// 계산된 숫자를 버퍼에 담고
			res = 0;				// 결과 숫자 변수 초기화
			loop = 3;			// 1회 실행 이후엔 내부 반복문은 무조건 3회만 실행하면 되므로.
		}
		
		return sb.toString();		// 버퍼의 값들을 문자열로 바꾸며 반환
	}
}
