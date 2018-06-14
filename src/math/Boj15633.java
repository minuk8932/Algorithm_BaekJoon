package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15633번: Fan Death
 *
 *	@see https://www.acmicpc.net/problem/15633/
 *
 */
public class Boj15633 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int sum = 0;
		
		if(n % 2 == 1) {						// 홀수의 경우
			for(int i = 1; i < n + 1; i++) {	// 해당 수의 모든 약수를 구함
				if(n % i == 0) {
					sum += i;
				}
			}
		}
		else {									// 짝수의 경우
			for(int i = 1; i < n / 2 + 1; i++) {		// 해당 수의 절반에 해당하는 수의 약수를 구해 다 더하고
				if(n % i == 0) {
					sum += i;
				}
			}
			
			sum += n;			// 마지막으로 해당하는 수를 더해줌
		}
		
		System.out.println(sum * 5 - 24);		// 결과값 출력
	}
}
