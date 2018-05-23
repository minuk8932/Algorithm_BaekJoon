package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 7572번: 간지
 *
 *	@see https://www.acmicpc.net/problem/7572/
 *
 */
public class Boj7572 {
	private static final int[] TEN = {7, 8, 9, 0, 1, 2, 3, 4, 5, 6};										// 2013년도에 맞춘 십간
	private static final char[] TWE = {'J', 'K', 'L', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};		// 2013년도에 맞춘 십이지
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int year = Integer.parseInt(br.readLine()) - 1;			// 년도는 1년부터 인덱스는 0부터 시작이므로 -1
		
		StringBuilder sb = new StringBuilder();
		sb.append(TWE[year % TWE.length]).append(TEN[year % TEN.length]);		// 버퍼에 각 인덱스를 십간의 수와 십이지 수로 나눈 나머지로 설정해 유추한 답을 담음
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
