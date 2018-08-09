package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 14916번: 거스름돈
 *
 *	@see https://www.acmicpc.net/problem/14916/
 *
 */
public class Boj14916 {
	private static final int[] COIN = { 2, 5 };

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int res = 0;
		
		if (n % COIN[1] == 0) {		// 5에 나누어 떨어지는 경우
			res = n / COIN[1];
		} 
		
		else {
			while (true) {
				if (n < 0) {		// n이 어느 수에도 나누어 떨어지지 않는 경우
					res = -1;
					break;
				}

				if (n == 0) break;		// n이 나누어 떨어진 경우

				n -= COIN[0];			// 2를 뺴주고, 동전 1개를 추가
				res++;

				if (n % COIN[1] == 0) {		// 뺀 값이 5로 나누어 떨어지는 경우
					res += n / COIN[1];
					break;
				}
			}
		}

		System.out.println(res);			// 결과값 출력
	}
}
