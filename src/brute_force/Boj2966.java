package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2966번 : 찍기
 *
 *	@see https://www.acmicpc.net/problem/2966/
 *
 */
public class Boj2966 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	private static final char[] ADRIAN = { 'A', 'B', 'C' };							// 상근이의 찍기 방식
	private static final char[] BRUNO = { 'B', 'A', 'B', 'C' };						// 창영이의 찍기 방식
	private static final char[] GORAN = { 'C', 'C', 'A', 'A', 'B', 'B' };			// 현진이의 찍기 방식

	private static final String[] WINNER = { "Adrian", "Bruno", "Goran" };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] ans = br.readLine().toCharArray();

		int[] res = { getPoints(N, ans, ADRIAN), getPoints(N, ans, BRUNO), getPoints(N, ans, GORAN)};		// 함수를 돌린 결과를 결과 배열에 담아줌
		int max = getMax(res[0], res[1], res[2]);								// 그 중 최댓값을 구하여

		StringBuilder sb = new StringBuilder();
		sb.append(max).append(NEW_LINE);										// 버퍼에 획득한 최대 점수를 담고

		for (int i = 0; i < WINNER.length; i++) {									// 반복문을 통해 그 점수를 획득한 사람의 이름을 문제의 조건에 따라 차례로 버퍼에 담음
			if (res[i] == max) {
				sb.append(WINNER[i]).append(SPACE);
			}
		}

		System.out.println(sb.toString());										// 결과값 한번에 출력
	}
	
	/**
	 * 
	 * 찍은대로 점수를 받는 메소드
	 * 
	 * @param N : 문제수
	 * @param line : 문제의 답이 들어있는 배열
	 * @param PLAYER : 각자 답을 찍는 방식이 들어있는 배열
	 * @return : 찍기에 따라 획득한 점수를 결과로 반환
	 */
	public static int getPoints(int N, char[] line, char[] PLAYER) {
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < PLAYER.length; j++) {				
				if(N > i * PLAYER.length + j){								// 찍기방식의 길이는 정해져있으나 문제의 수는 그 이상을 훨씬 초과하므로, 반복적으로 돌려주기위한 범위설정
					if (line[i * PLAYER.length + j] == PLAYER[j]) {		// 해당 순서에따라 각 배열이 서로 같은 값을 가진다면,
						cnt++;														// 점수를 1점 추가
						
						continue;
					}
				}
			}
		}

		return cnt;																// 최종 획득점수 반환
	}
	
	/**
	 * 
	 * @param a : 점수 1
	 * @param b : 점수 2
	 * @param g : 점수 3
	 * @return 3개의 점수 중 최댓값 반환
	 */
	public static int getMax(int a, int b, int g) {
		return a > b ? Math.max(a, g) : Math.max(g, b);
	}
}
