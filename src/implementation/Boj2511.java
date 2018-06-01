package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2511번: 카드놀이
 *
 *	@see https://www.acmicpc.net/problem/2511/
 *
 */
public class Boj2511 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] A = new int[10];
		int[] B = new int[10];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < A.length; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < B.length; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		int sumA = 0, sumB = 0, cnt = 0;
		int[] win = new int[10];			// 누가 이긴지 판별하는 배열
		
		for(int i = 0; i < A.length; i++) {
			if(A[i] > B[i]) {
				sumA += 3;
				win[i] = 1;			// A 승리: 1
			}
			else if(B[i] > A[i]) {
				sumB += 3;
				win[i] = 2;			// B 승리: 2
			}
			else {					// 무승부의 경우
				sumA++;
				sumB++;
				cnt++;				// 무승부 카운트 +1
			}
		}
		
		int maxA = 0, maxB = 0;
		char res = 'D';
		
		if(cnt != 10) {						// 전체 다 무승부가 아닐 경우
			for(int i = 0; i < 10; i++) {	// A, B가 각각 최종 승리한 라운드의 값을 받아옴
				if(win[i] == 1) {
					maxA = i;
					continue;
				}
				
				if(win[i] == 2) {
					maxB = i;
					continue;
				}
			}
			
			if(sumA == sumB) {		// 두 팀의 승점이 같은 경우
				if(maxA > maxB) {	// A의 최종 승리라운드가 더 클 경우
					res = 'A';
				}
				else{				// B가 더 클경우
					res = 'B';
				}
			} else {				// 승점이 다른경우
				if(sumA > sumB) {	// A가 승점이 큰 경우
					res = 'A';
				}
				else {				// B가 승점이 큰 경우
					res = 'B';
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(sumA).append(SPACE).append(sumB).append(NEW_LINE);	// 각 팀의 승점을 공백으로 구분짓고
		sb.append(res);												// 승리 여부와 함께 버퍼에 담음

		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
