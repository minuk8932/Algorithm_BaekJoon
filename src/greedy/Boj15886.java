package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15886번: 내 선물을 받아줘 2
 *
 *	@see https://www.acmicpc.net/problem/15886/
 *
 */
public class Boj15886 {
	private static final String MOVE = "EW";
	private static final char SPACE = ' ';

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String line = br.readLine();
		
		int res = line.length();				// 총 문자열 길이를 구함
		line = line.replaceAll(MOVE, " ");		// 이중 EW를 제거 (선물이 놓이는 위치)
		
		int cnt = 0;
		for(char word: line.toCharArray()) {	// 반복문을 통해 남은 문자의 갯수를 센 후
			if(word != SPACE) cnt++;
		}
		
		System.out.println((res - cnt) / 2);	// 지워진 EW의 갯수를 찾은 결과 출력
	}
}
