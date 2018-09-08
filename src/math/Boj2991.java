package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2991번: 사나운 개
 *
 *	@see https://www.acmicpc.net/problem/2991/
 *
 */
public class Boj2991 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] p = new int[3];
		for(int i = 0; i < 3; i++) p[i] = Integer.parseInt(st.nextToken());
		
		int sumA = A + B;
		int sumB = C + D;
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < p.length; i++) {
			int dogCnt = 0;
			
			int tmpA = p[i] % sumA;
			int tmpB = p[i] % sumB;
			
			dogCnt = tmpA <= A && tmpA >= 1 ? dogCnt + 1 : dogCnt;		// 조건에 맞춰 만나는 개의 수 설정
			dogCnt = tmpB <= C && tmpB >= 1 ? dogCnt + 1 : dogCnt;
			
			sb.append(dogCnt).append(NEW_LINE);		// 개의 수를 버퍼에 담고
		}
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
}
