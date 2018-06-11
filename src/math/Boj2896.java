package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2896번: 무알콜 칵테일
 *
 *	@see https://www.acmicpc.net/problem/2896/
 *
 */
public class Boj2896 {
	private static final double INF = 10_000_000;
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double A = Double.parseDouble(st.nextToken());
		double B = Double.parseDouble(st.nextToken());
		double C = Double.parseDouble(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		double i = Double.parseDouble(st.nextToken());
		double j = Double.parseDouble(st.nextToken());
		double k = Double.parseDouble(st.nextToken());
		
		double divA = A / i;		// 나눈 몫을 저장
		double divB = B / j;
		double divC = C / k;
		
		double min = getMin(divA, divB, divC);	// 몫의 값 중 최소값을 min에 저장
		
		A -= (i * min);			// min을 통해 나머지 남는 주스의 값을 구함
		B -= (j * min);
		C -= (k * min);
		
		StringBuilder sb = new StringBuilder();
		sb.append(A).append(SPACE).append(B).append(SPACE).append(C);		// 구한 값들을 버퍼에 담은 후
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 최솟값 생성 메소드
	 * @return 최솟값
	 */
	private static double getMin(double a, double b, double c) {
		double min = INF;
		
		min = Math.min(a, Math.min(b, c));
		
		return min;
	}
}
