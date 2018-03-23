package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1003번 : 피보나치 함수
 *
 *	@see https://www.acmicpc.net/problem/1003/
 *
 */
public class Boj1003 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			int num = Integer.parseInt(br.readLine());
			int[][] chk = new int[2][41];							// idx0 : 0의 출력횟수, idx1 : 1의 출력횟수
			
			chk[0][0] = 1;
			chk[1][1] = 1;
			
			for(int i = 2; i < 41; i++){								// 피보나치 함수 실행
				chk[0][i] = chk[0][i - 2] + chk[0][i - 1];
				chk[1][i] = chk[1][i - 1] + chk[1][i - 2];
			}

			sb.append(chk[0][num]).append(SPACE).append(chk[1][num]).append(NEW_LINE);	//0 과 1이 출력되는 횟수를 버퍼에 담음
		}
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
