package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * 	@author exponential-e
 *	백준 17283번: I am Groot
 *
 *	@see https://www.acmicpc.net/problem/17283/
 *
 */
public class Boj17283 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		int R = Integer.parseInt(br.readLine());
		
		System.out.println(rootLength(L, R));
	}
	
	private static int rootLength(int leng, int rate) {
		ArrayList<Integer> len = new ArrayList<>();
		int total = 0, idx = 1;
		
		while(leng * rate / 100 > 5) {
			len.add(leng * rate / 100);
			leng *= rate;
			leng /= 100;
			
			total += leng * (int) Math.pow(2, idx++);
		}
		
		return total;
	}
}
