package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 3041번: N-퍼즐
 *
 *	@see https://www.acmicpc.net/problem/3041/
 *
 */
public class Boj3041 {
	private static final char[][] PUZZLE = { { 'A', 'B', 'C', 'D' }, { 'E', 'F', 'G', 'H' }, { 'I', 'J', 'K', 'L' },
			{ 'M', 'N', 'O', 'P' } };
	
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[][] comp = new char[4][5];

		for (int i = 0; i < 4; i++) {
			comp[i] = br.readLine().toCharArray();
		}

		int dist = 0;

		for (int r1 = 0; r1 < 4; r1++) {
			for (int c1 = 0; c1 < 4; c1++) {
				for (int r2 = 0; r2 < 4; r2++) {
					for (int c2 = 0; c2 < 4; c2++) {
						if (PUZZLE[r1][c1] == comp[r2][c2]) {				// 두값이 같은 경우를 찾고
							dist += Math.abs(r2 - r1) + Math.abs(c2 - c1);	// 해당 인덱스가 떨어진 거리 만큼을 거리 변수에 더함
						}
					}
				}
			}
		}
		
		System.out.println(dist);	// 결과값 출력
	}
}
