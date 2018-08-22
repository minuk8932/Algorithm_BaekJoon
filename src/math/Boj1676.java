package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1676번: 팩토리얼 0의 갯수
 *
 *	@see https://www.acmicpc.net/problem/1676/
 *
 */
public class Boj1676 {	
	private static final int TWO = 2;
	private static final int FIVE = 5;
	private static final int TEN = 10;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int two = 0;
		int five = 0;
		int ten = 0;
		
		for(int i = 2; i < N + 1; i++) {
			int num = i;
			
			if(num % TEN == 0) {			// 첫째로 10의 약수의 갯수를 구함
				int tmp = i;
				
				while(tmp != 1) {
					if(tmp % TEN != 0) break;
					
					tmp /= TEN;
					ten++;
				}
				
				if(tmp == 1) continue;		// 해당 값이 나누어 떨어지면 다음 숫자로
				else num = tmp;				// 아니면 num에 나눈 후의 값을 저장 후 다음 조건문 실행
			}
			
			if(num % FIVE == 0) {			// 둘째로 5의 약수의 갯수를 구함
				int tmp = num;
				
				while(tmp != 1) {
					if(tmp % FIVE != 0) break;
					
					tmp /= FIVE;
					five++;
				}
				
				if(tmp == 1) continue;		// 해당 값이 나누어 떨어지면 다음 숫자로
				else num = tmp;				// 아니면 num에 나눈 후의 값을 저장 후 다음 조건문 실행
			}
			
			if(num % TWO == 0) {			// 마지막으로 2의 약수의 갯수를 구함
				int tmp = num;
				
				while(tmp != 1) {
					if(tmp % TWO != 0) break;
					
					tmp /= TWO;
					two++;
				}
			}
		}
		
		// 결과값에 2와 5중 더 많은 숫자의 값 + 10의 갯수를 저장
		int res = (five > two ? two : five) + ten;
		
		System.out.println(res);	// 결과 값 출력
	}
}
