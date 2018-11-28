import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj3671 {
	private static final int INF = 10_000_000;
	private static final char NEW_LINE = '\n';
	
	private static int counts = 0;
	private static int leng = 1;
	private static boolean[] isVisited = new boolean[INF];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int c = Integer.parseInt(br.readLine());
		
		boolean[] isPrime = getPrime();
		
		while(c-- > 0) {
			char[] nums = br.readLine().toCharArray();
			backTracking(nums, isPrime);
			
			sb.append(counts).append(NEW_LINE);
			isVisited = new boolean[INF];
		}
	}
	
	private static boolean[] getPrime() {
		boolean[] arr = new boolean[INF];
		Arrays.fill(arr, true);
		
		arr[0] = arr[1] = false;
		for(int i = 2; i < INF; i++) {
			for(int j = i + i; j < INF; j += i) {
				if(!arr[i]) continue;
				arr[i] = false;
			}
		}
		
		return arr;
	}
	
	private static void backTracking(char[] w, boolean[] arr) {
		
		
	}
}
