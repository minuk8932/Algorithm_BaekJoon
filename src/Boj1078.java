import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1078 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		char[] D = input.toCharArray();
		long dig = Long.parseLong(input);
		
		System.out.println(reverse(D, dig));
	}
	
	private static long reverse(char[] d, long g) {
		long fix = (long) Math.pow(10, d.length);
		double tmp = 0;
		int idx = 0;
		
		for(int n = 1; n < d.length + 1; n++) {
			
		}
		
		return -1;
	}
}
