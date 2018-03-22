package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1357번 : 뒤집힌 덧셈
 *
 *	@see https://www.acmicpc.net/problem/1357/
 *
 */
public class Boj1357 {
	private static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		String x = st.nextToken();
		String y = st.nextToken();

		StringBuilder sb = new StringBuilder();

		x = sb.append(x).reverse().toString();			// 숫자x 순서를 거꾸로 문자열로 변환해 버퍼에 담음
		sb.delete(0, x.length());								// 끝의 0 삭제
		
		y = sb.append(y).reverse().toString();			// 위와같이 y도 처리
		sb.delete(0, y.length());
		
		int tmp = Integer.parseInt(x) + Integer.parseInt(y);	// 정수형으로 변환하여 둘을 합하고 tmp에 담아줌 
		
		// tmp를 문자열로 바꾸어 순서를 역전시키고 이를 다시 정수형으로 변환해 0xx 같은 숫자의 출력을 막으며 버퍼에 있는 값을 꺼내와 출력
		System.out.println(Integer.parseInt(sb.append(String.valueOf(tmp)).reverse().toString()));	
	}
}
