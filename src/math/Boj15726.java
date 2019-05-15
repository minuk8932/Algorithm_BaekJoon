package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 * 백준 15726번: 이칙 연산
 * 
 * @see https://www.acmicpc.net/problem/15726/
 *
 */
public class Boj15726 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double A = Double.parseDouble(st.nextToken());
		double B = Double.parseDouble(st.nextToken());
		double C = Double.parseDouble(st.nextToken());
		
		System.out.println((long) Math.max(A * B / C, A / B * C));		// 결과 값에서만 소수점을 제거
	}
}
