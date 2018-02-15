package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 3053번 : 택시 기하학
 *
 *	@see https://www.acmicpc.net/problem/3053
 *
 */
public class Boj3053 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		double pi = 3.1415926535897930;	// Math.PI : 원인 모를 오류, 컴파일러마다 다르게 인식해서 그런듯..
		double ucl = pi * N * N;				// 유클리드
		double taxi = 2 * N * N;				// 택시(맨해튼 거리)
		
		System.out.printf("%f \n", ucl);	// 유클리드 원 넓이
		System.out.printf("%f", taxi);		// 택시 원 넓이
	}
}
