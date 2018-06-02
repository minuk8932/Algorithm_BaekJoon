package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2480번: 주사위 세개
 *
 *	@see https://www.acmicpc.net/problem/2480/
 *
 */
public class Boj2480 {
	private static final int TRIPLE = 10_000;
	private static final int DOUBLE = 1_000;
	private static final int UNI = 100;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int d1 = Integer.parseInt(st.nextToken());
		int d2 = Integer.parseInt(st.nextToken());
		int d3 = Integer.parseInt(st.nextToken());
		
		int res = 0;
		
		if(d1 == d2 && d2 == d3) {					// 3개 모두 같을 때
			res = TRIPLE + d1 * DOUBLE;
		}
		else if(d1 != d2 && d2 != d3 && d1 != d3) {	// 3개 모두 다를 때
			res = UNI * getMax(d1, d2, d3);			// 그 중 최댓값을 통해 결과값 생성
		}
		else {
			if(d1 == d2) {					// 2개만 같은 경우
				res = DOUBLE + UNI * d1;
			}
			
			if(d2 == d3) {
				res = DOUBLE + UNI * d2;
			}
			
			if(d1 == d3){
				res = DOUBLE + UNI * d3;
			}
		}
		
		System.out.println(res);
	}
	
	/**
	 * 최댓값 반환 메소드
	 * @param 주사위 3개의 값
	 * @return	3개의 값 중 최댓값
	 */
	private static int getMax(int a, int b, int c) {
		int val = a;
		
		val = Math.max(val, Math.max(b, c));
		
		return val;
	}
}
