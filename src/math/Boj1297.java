package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1297 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int s = Integer.parseInt(st.nextToken());			// 대각선
		int h = Integer.parseInt(st.nextToken());			// 높이
		int w = Integer.parseInt(st.nextToken());		// 너비
		br.close();
		
		double val = 0.0;
		double sPow = Math.pow(s, 2);						// 각 값들의 제곱
		double hPow = Math.pow(h, 2);
		double wPow = Math.pow(w, 2);
		
		val = sPow /(hPow + wPow);							// 대각선 비율의 제곱을 대각선 실제 값의 제곱과 나누어 줌
		
		int realH = (int)Math.sqrt(hPow * val);			// 구하려는 값 = sqrt( (해당 값 비율)^2 * (실제 대각선의 길이)^2 / (비율에 따른 대각선의 비율)^2 )
		int realW = (int)Math.sqrt(wPow * val);
		
		System.out.println(realH + " " + realW);			// 결과 값 출력
	}
}
