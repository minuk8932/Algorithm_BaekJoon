package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16114번: 화살표 연산자
 *
 *	@see https://www.acmicpc.net/problem/16114/
 *
 */
public class Boj16114 {
	private static final String E = "ERROR";
	private static final String I = "INFINITE";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		if(N == 0) System.out.println(X > 0 ? I : 0);	// X > 0, INFINITE 그 외, 반복문 실행 x		
		else if(N == 1) System.out.println(X < 0 ? I : 0);	// X < 0, while(true) 이므로 INFINITE	그 외, while(false) 이므로 0	
		else if(N % 2 == 1) System.out.println(E);		// 문제 조건에 따라 ERROR
		else {
			int res = X % (N / 2) == 0 ? X / (N / 2) - 1 : X / (N / 2);	// 나오는 숫자의 갯수, 음수개면 0을 받아옴
			System.out.println(res <= 0 ? 0 : res);			
		}
	}
}
