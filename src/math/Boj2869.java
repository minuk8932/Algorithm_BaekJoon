package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2869번 : 달팽이는 올라가고 싶다
 *
 *	@see https://www.acmicpc.net/problem/2869/
 *
 */
public class Boj2869 {
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());		// 올라가기
		int B = Integer.parseInt(st.nextToken());		// 내려가기
		int V = Integer.parseInt(st.nextToken());		// 나무의 높이
		
		double days = (double)(V-A)/(A-B);				// 남은 높이 / 하루동안 총 이동거리
		int day = (int) Math.ceil(days);					// 총 기간을 올림하여 저장하고
		
		System.out.println(day+1);							// 해당 기간 + 1 결과를 출력
	}

}
