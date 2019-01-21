package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author minchoba
 * 백준 16561번: 3의 배수
 * 
 * @see https://www.acmicpc.net/problem/16561/
 * 
 */
public class Boj16561 {
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()) / 3;
		int count = 0;
		
		for(int i = 1; i < n - 1; i++) {
			for(int j = 1; j < n - 1; j++) {
				if((n - i - j) > 0) count++;		// 0보다 큰경우 다음 3의 배수가 존재
			}
		}
		
		System.out.println(count);
	}
}
