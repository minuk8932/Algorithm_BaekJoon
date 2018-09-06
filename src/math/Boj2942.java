package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2942번: 퍼거슨과 사과
 *
 *	@see https://www.acmicpc.net/problem/2942/
 *
 */
public class Boj2942 {
	private static char SPACE = ' ';
	private static char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		
		int GCD = getGcd(R, G);			// 최대 공약수 메소드를 통해 GCD를 구함
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= GCD; i++) {		// 최대 공약수의 약수들을 통해 나오는 값들을 버퍼에 담음
			if(GCD % i == 0) {
				sb.append(i).append(SPACE).append(R / i).append(SPACE).append(G / i).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());	// 결과 값 한번에 출력
	}
	
	private static int getGcd(int num1, int num2) {
		if(num2 == 0) {		// num1, num2를 나눈 나머지가 0인 경우
			return num1;		// num1이 최대 공약수로 반환됨
		}
		
		return getGcd(num2, num1 % num2);		// 아직 약수를 찾지 못한 경우 재귀호출
	}
}
