package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6502번: 동혁피자
 *
 *	@see https://www.acmicpc.net/problem/6502/
 *
 */
public class Boj6502 {
	private static final String ZERO = "0";
	private static final String NEW_LINE = "\n";
	private static final String PIZZA = "Pizza ";
	private static final String FITS = " fits on the table.";
	private static final String NOT_FITS = " does not fit on the table.";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int seq = 0;
		
		while(true){
			String line = br.readLine();
			
			if(line.equals(ZERO)) break;			// 마지막 입력 0일 때 반복문 종료
			
			seq++;		// 피자의 번호 지시 변수
			
			StringTokenizer st = new StringTokenizer(line);
			double r = Double.parseDouble(st.nextToken()) * 2;	//	피자 지름의 길이
			double w = Double.parseDouble(st.nextToken());		// 탁자의 너비
			double l = Double.parseDouble(st.nextToken());		// 탁자의 길이
			double slash = w * w + l * l;						// 탁자 대각선 길이
			
			if(slash <= r * r){				// 탁자 대각선 길이 <= 피자 지름의 제곱
				sb.append(PIZZA).append(seq).append(FITS).append(NEW_LINE);		// 피자 놓을 수 있음을 버퍼에 담음
			}
			else{
				sb.append(PIZZA).append(seq).append(NOT_FITS).append(NEW_LINE);		// 그 외, 놓지 못 함을 버퍼에 담음
			}
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
