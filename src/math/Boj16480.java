package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16480번: 내심과 외심은 사랑입니다.
 *
 *	@see https://www.acmicpc.net/problem/16480/
 *
 */
public class Boj16480 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long R = Integer.parseInt(st.nextToken());
		long r = Integer.parseInt(st.nextToken());
		
		System.out.println(R * (R - 2 * r));			// 오일러 정리
	}
}
