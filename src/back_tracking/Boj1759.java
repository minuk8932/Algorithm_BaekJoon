package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1759번: 암호 만들기
 *
 *	@see https://www.acmicpc.net/problem/1759/
 *
 */
public class Boj1759 {
	private static final String NEW_LINE = "\n";
	private static final String EMPTY = "";
	private static final int INF = 10_000;
	
	private static String[] res = new String[INF];
	private static String word = "";
	private static int size = 0;
	private static int L = 0;
	private static int C = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		char[] init = new char[C];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < C; i++) {
			init[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(init);			// 주어진 문자 정렬
		Arrays.fill(res, EMPTY);	// 결과 배열 초기화
		
		search(0, init);			// backtracking 시작
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < res.length; i++) {				// 배열의 문자열들을 버퍼에 담고
			if(!EMPTY.equals(res[i])) sb.append(res[i]).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
	
	/**
	 *  백트래킹 메소드
	 * 
	 */
	private static void search(int start, char[] w) {
		if(start == C) return;		// 모든 문자열 탐색이 완료되었을 때
		
		word += w[start];			// 임시 변수 word에 현재 인덱스에 해당하는 알파벳 하나를 담아줌
		
		if(word.length() == L) {			// 재귀적으로 돌다가 제한 길에와 현재 문자열 길이가 같은 경우
			int c = 0, v = 0;
			
			for(int i = 0; i < L; i++) {				// 해당 문자열을 돌면서 모음인지 자음인지 확인
				if(word.charAt(i) == 'a' || word.charAt(i) == 'e' || 
						word.charAt(i) == 'i' || word.charAt(i) == 'o' || word.charAt(i) == 'u') {
					v++;
				}
				else {
					c++;
				}
			}
			
			if(v >= 1 && c >= 2) {		// 모음과 자음 갯수가 조건과 부합하는 경우
				res[size] = word;		// 문자열을 배열에 담고
				size++;				// 인덱스를 바꿔줌
			}
		}
		
		search(start + 1, w);		// 메소드 재귀 호출
		word = word.substring(0, word.length() - 1);	// 문자열 가장 마지막의 단어 하나를 뺀 후 메소드 재귀 호출
		search(start + 1, w);
	}
}
