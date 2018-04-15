package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10101번 : 삼각형 외우기
 *
 *	@see https://www.acmicpc.net/problem/10101/
 *
 */
public class Boj10101 {
	private static final String TRI = "Equilateral";
	private static final String DUO = "Isosceles";
	private static final String UNI = "Scalene";
	private static final String NONE = "Error";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int c = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		if (a + b + c != 180){			// 조건에 따른 해당 값 버퍼에 저장
			sb.append(NONE);
		}
	    else {
	        if (a == b) {
	            if (b == c){
	            	sb.append(TRI);
	            }
	            else {
	            	sb.append(DUO);
	            }
	        }
	        else {
	            if (b == c){
	            	sb.append(DUO);
	            }
	            else {
	                if (a == c) {
	                	sb.append(DUO);
	                }
	                else {
	                	sb.append(UNI);
	                }
	            }
	        }
	    }

		System.out.println(sb.toString());	// 결과값 한번에 출력
	}
}
