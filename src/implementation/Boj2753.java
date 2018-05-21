package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2753번: 윤년
 *
 *	@see https://www.acmicpc.net/problem/2753/
 *
 */
public class Boj2753 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int year = Integer.parseInt(br.readLine());
		
		System.out.println(isLeapYear(year));		// 결과값 출력
	}
	
	/**
	 * 
	 * 	@param y: 윤년인지 계산 할 년도
	 * 	@return: 윤년 여부 반환
	 */
	private static int isLeapYear(int y){
		int leapYear = 0;
		
		if(y % 4 == 0 && (y % 100 != 0 || y % 400 == 0)){	// 4의 배수이면서, 100의 배수는 아니고, 400의 배수 일 경우
			leapYear = 1;				// 윤년임
		}
		
		return leapYear;		// 윤년이면 1 아니면 0 반환
	}
}
