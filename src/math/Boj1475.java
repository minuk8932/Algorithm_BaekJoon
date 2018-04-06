package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1475번 : 방번호
 *
 *	@see https://www.acmicpc.net/problem/1475/
 *
 */
public class Boj1475 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		char[] N = str.toCharArray();

		int[] num = new int[N.length];

		for (int i = 0; i < N.length; i++) {
			num[i] = Character.getNumericValue(N[i]);
		}

		int[] chk = new int[10];
		int cnt = 0;
		int max = 0;

		for (int i = 0; i < N.length; i++) {			// 0~9사이의 값만 받아서, 확인 배열에 더해줌
			chk[num[i]]++;
		}

		cnt = chk[6] + chk[9];							// 이중 6, 9는 뒤집어 쓸 수 있으므로, 그러한 가능성을 추가
		cnt = (cnt / 2) + (cnt % 2);

		for (int i = 0; i < chk.length; i++) {		// 6, 9가 아닐 경우 결과를 더하고
			if (i != 6 && i != 9) {
				if (chk[i] >= max) {
					max = chk[i];
				}
			}
		}

		System.out.println(Math.max(max, cnt));		// 두 값 중 최댓값 출력

	}
}
