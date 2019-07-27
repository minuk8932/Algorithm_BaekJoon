import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj1215 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		System.out.println(josephs(n, k));
	}
	
	private static long josephs(long N, long K) {
		long sum = N > K ? (N - K) * K : 0;
		
		int loop = (int) (Math.sqrt(K)) + 1;
		HashSet<Integer> set = new HashSet<>();
		
		for(int i = 1; i < loop; i++) {			// 어떤 수의 배수는 해당 수 어떤 수 전까지의 합 + 어떤수 보다 작은 나머지의 합
			if(K % i == 0) {
				set.add(i);
				set.add((int) (K / i));
			}
		}
		
		for(int num: set) {
			
		}
		
		return sum;
	}
}
