package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1356번: 유진수
 *
 *	@see https://www.acmicpc.net/problem/1356/
 *
 */
public class Boj1356 {
	private static final String Y = "YES";
	private static final String N = "NO";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] tenary = br.readLine().toCharArray();
		
		System.out.println(process(tenary));	// 결과 출력
	}
	
	private static String process(char[] w) {
		for(int i = 1; i < w.length; i++) {			// 메소드를 통해 좌우 구간 별 연산 ex) left: 0 ~ i, right: (i + 1) ~ (leng -1)
			if(calculate(w, 0, i) == calculate(w, i, w.length)) return Y;
		}
		
		return N;
	}
	
	private static int calculate(char[] w, int start, int end) {
		int value = 1;
		
		for(int i = start; i < end; i++) {
			value *= (w[i] - '0');
		}
		
		return value;
	}
}
