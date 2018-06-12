package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 11947번: 이런 반전이
 *
 *	@see https:www.acmicpc.net/problem/11947/
 *
 */
public class Boj11947 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			String line = br.readLine();
			long N = Long.parseLong(line);
			long leng = line.length();
			
			long ten = (long) Math.pow(10, leng - 1);
			long half = ten * 5;

			if(N != ten) {				// 이 수가 입력의 길이에 해당하는 10의 배수인지?
				if(N >= half) {			// 아니라면 10의 배수에 5를 곱한 값보다 크거나 같으면 5로 통일, ex) 6785 => 5000
					N = half;
				}
			}
			
			long fN = 0;
			String fwd = String.valueOf(N);
			String rev = null;
			
			for(int i = 0; i < leng; i++) {					// 본래 들어온 숫자를 자릿수 마다 9를 뺀 값으로 바꿔줌
				if(i == 0) {
					rev = String.valueOf((int) (9 - (fwd.charAt(i) - '0')));
				}
				else {
					rev += String.valueOf((int) (9 - (fwd.charAt(i) - '0')));
				}
			}
			
			fN = Long.parseLong(rev);			// 반전시킨 숫자를 fN 변수에 담고

			sb.append(N * fN).append(NEW_LINE);		// 본래 수 * 반전 수의 값을 버퍼에 담음
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
