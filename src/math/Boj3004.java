package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj3004 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(getRes(N));
	}
	
	private static int getRes(int n) {
		int num = (n / 2 + 1 + (n % 2));
		return num * (num - (n % 2));
	}
}
