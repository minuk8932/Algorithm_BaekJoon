package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1343번: 폴리오미노
 *
 *	@see https://www.acmicpc.net/problem/1343/
 *
 */
public class Boj1343 {
	private static final char[] POLYOMINO = {'A', 'B'};
	private static final char DOT = '.';
	
	private static final int A = 0;
	private static final int NUMBER_OF_A = 4;
	private static final int B = 1;
	private static final int NUMBER_OF_B = 2;
	private static final int FAILED = -1;
	
	private static char[] res = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] board = br.readLine().toCharArray();
		res = new char[board.length];
		
		int xCnt = 0;
		
		for(char tmp : board) {			// 보드판 검사
			if(tmp == DOT) {			// '.'이 나온경우
				if(xCnt % 2 != 0) {		// X의 갯수가 짝수개인지 확인
					xCnt = -1;			// 아니면 -1 저장하고 종료
					break;
				}
				
				continue;				// 다음으로 넘김
			}
			
			xCnt++;			// 'X'가 나오면 카운트 +1
		}
		
		// X의 개수가 중간에 홀수개로 종료되었거나, X로만 이루어진 문자열일 때 홀수개인 경우는 참을 그 외는 거짓을 저장
		boolean isPassed = (xCnt == -1) || (xCnt % 2 != 0) ? true : false;
		
		if(!isPassed) {			// 짝수개로 보드를 채울 수 있는 경우에만 실행	
			xCnt = 0;
			
			for(int i = 0; i < res.length; i++) {
				if(board[i] != DOT) xCnt++;			// 'X'인 경우 카운트 +1
				if(board[i] == DOT)	res[i] = DOT;	// '.'인 경우 결과 배열에 '.' 저장
				
				if(board[i] == DOT) {			// 반복문 중간에 '.'이 나오는 경우, 
					prefix(xCnt, i);			// 즉 맨 끝부분을 제외한 나머지 X의 보드를 prefix 메소드로 처리
					xCnt = 0;
					
					continue;				// 메소드 완료 후 다음 인덱스로 넘어감
				}
				
				if(i == res.length - 1) {	// 보드의 맨 끝 부분인 경우
					suffix(xCnt, i);		// suffix 메소드로 처리
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < res.length; i++) {		// 버퍼에 배열의 값들을 담고
			sb.append(res[i]);
		}
		
		System.out.println(isPassed ? FAILED : sb.toString());		// 패스한 경우 -1, 그 외엔 버퍼의 값을 한번에 출력
	}
	
	/**
	 * prefix 메소드
	 * 
	 */
	private static void prefix(int count, int idx) {
		if(count == NUMBER_OF_B ) {						// X의 갯수가 2개인 경우 "BB"
			for(int j = idx - count; j < idx; j++) {
				res[j] = POLYOMINO[B];
			}
		}
		else {													// 그 외,
			int loop = count % NUMBER_OF_A == 0 ? idx : idx - 2;		// A의 갯수에 따라 반복문 횟수를 정하고
			
			for(int j = idx - count; j < loop; j++) {		// 4의 배수만큼 "AAAA"를 배열에 저장
				res[j] = POLYOMINO[A];
			}
			
			if(count % NUMBER_OF_A != 0) {					// 나머지가 남는다면 "BB"를 그 뒤의 배열에 저장
				for(int j = loop; j < loop + 2; j++) {
					res[j] = POLYOMINO[B];
				}
			}
		}
	}
	
	/**
	 * suffix 메소드
	 * 
	 */
	private static void suffix(int count, int idx) {
		idx += 1;					// prefix와는 다르게 idx 수가 1개 부족하므로, 미리 +1
		
		if(count == NUMBER_OF_B ) {					// 이후는 prefix 메소드와 같은 방식으로 작동
			for(int j = idx - count; j < idx; j++) {
				res[j] = POLYOMINO[B];
			}
		}
		else {						
			int loop = count % NUMBER_OF_A == 0 ? idx : idx - 2;
			
			for(int j = idx - count; j < loop; j++) {
				res[j] = POLYOMINO[A];
			}
			
			if(count % NUMBER_OF_A != 0) {
				for(int j = loop; j < loop + 2; j++) {
					res[j] = POLYOMINO[B];
				}
			}
		}
	}
}
