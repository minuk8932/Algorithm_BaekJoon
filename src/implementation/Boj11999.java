package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author exponential-e
 * 백준 11999번: Milk Pails (Bronze)
 * 
 * @see https://www.acmicpc.net/problem/11999/
 * 
 */

public class Boj11999 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		System.out.println(getMax(X, Y ,M));
	}
	
	private static int getMax(int x, int y, int milk) {
		int max = 0;
		int[] loop = {milk / x + 1, milk / y + 1};
		
		for(int i = 0; i < loop[0]; i++) {
			for(int j = 0; j < loop[1]; j++) {
				int pail = i * x + j * y;
				if(pail <= milk) max = Math.max(pail, max);
			}
		}
		
		return max;
	}
}
