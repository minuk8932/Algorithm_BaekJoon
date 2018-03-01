package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2998번 : 8진수
 *
 *	@see https://www.acmicpc.net/problem/2998
 *
 */
public class Boj2998 {
	private static final String ZERO_PUT = "0";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String bin = br.readLine();
		String res = null;
		
		int leng = bin.length();
		
		if(leng % 3 == 1){									// 길이가 3으로 나누어 떨어질 수 있도록 앞자리에 0을 채워줌
			bin = ZERO_PUT + ZERO_PUT + bin;
		}
		else if(leng % 3 == 2){
			bin = ZERO_PUT + bin;
		}
		
		leng = bin.length();
		
		for(int i = 0; i < leng; i += 3){					// 3자리씩 끊어서
			int tmp = 0;
			
			if(i < leng - 2){
				if(cutter(bin, i, i+2).charAt(0) == '1'){		// 자른 3자리 문자를 각 자리에 1이 존재하는지 체크 후 알맞은 값을 더해줌
					tmp += 4;
				}
				
				if(cutter(bin, i, i+2).charAt(1) == '1'){
					tmp += 2;
				}
				
				if(cutter(bin, i, i+2).charAt(2) == '1'){
					tmp += 1;
				}
			}																// 더한 값은 문자열로 변경해 하나씩 붙여줌
			
			if(i == 0){
				res = String.valueOf(tmp);
			}
			else{
				res += String.valueOf(tmp);
			}
		}
		
		System.out.println(res);									// 결과값 출력
	}
	
	/**
	 * 
	 * 	@param cut : 자를 문자열
	 * 	@param start : 시작 인덱스
	 * 	@param end : 끝 인덱스
	 * 	@return : 자른 문자열
	 * 
	 */
	private static String cutter(String cut, int start, int end){
		String output = null;
		
		for(int i = start; i < end + 1; i++){					// 각 문자마다 3개씩 잘라줌
			if(i == start){												// 2진수에서 8진수로 변경은 비트로 봤을때 3문자씩 잘라서 2진수 연산 후 그대로 붙여주면 되기 때문
				output = String.valueOf(cut.charAt(i));
			}
			else{
				output += String.valueOf(cut.charAt(i));	// ex) 011110 : 이런숫자가 들어오면 앞의 011과 뒤의 110으로 나누어준 후 2진수 계산해주면
			}																//			011(2) : 3, 110(2) : 6 이므로 이는 36(8)이 된다.
		}
		
		return output;
	}
}