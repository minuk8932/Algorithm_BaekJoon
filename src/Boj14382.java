import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj14382 {
	private static final String CASE = "Case #";
	private static final String COLON = ": ";
	private static final String INSOM = "INSOMNIA";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			long result = counting(N);
			sb.append(CASE).append(t + 1).append(COLON).append(result == -1 ? INSOM: result).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
	
	private static long counting(int n) {
		if(n == 0) return -1;
		
		boolean[] num = new boolean[10];
		int count = 1;
		long last = -1;
		
		while(!rotated(num)) {
			long target = n * count;
			last = target;
			
			while(target > 0) {
				num[(int) target % 10] = true;
				target /= 10;
			}
			
			count++;
		}
		
		return last;
	}
	
	private static boolean rotated(boolean[] arr) {
		for(int i = 0; i < arr.length; i++) {
			if(!arr[i]) return false;
		}
		
		return true;
	}
}
