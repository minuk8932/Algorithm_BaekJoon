import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj1016 {
	private static boolean[] prime;
	private static HashSet<Long> hs = new HashSet<>();
	private static int factor;
	
	private static ArrayList<Long> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());
		
		int N = (int) Math.sqrt(max); 
		
		getPrime(N, min);
		System.out.println(getNotPow(N, max, min));
	}
	
	private static void getPrime(int n, long m) {
		prime = new boolean[n + 1];
		
		Arrays.fill(prime, true);
		prime[0] = prime[1] = false;
		
		for(int i = 2; i < n + 1; i++) {
			if(!prime[i]) continue;
			
			for(int j = i + i; j < n + 1; j += i) {
				prime[j] = false;
			}
			
			list.add((long) i * i);
		}
		
//		for(int i = 0; i < n + 1; i++) {
//			if(!prime[i]) continue;
//			long value = i * i;
//			
//			if(value >= m) {
//				factor = i;
//				break;
//			}
//		}
	}
	
	private static long getNotPow(int n, long left, long right) {
		for(long p: list) {
			long min = Long.MAX_VALUE;
			
			if(left % p == 0) min = ((left / p) + 1) * p;
			else min = left;
			
			long max = (right / p) * p;
			
			for(long i = min; i < max + 1; i += p) {
				if(hs.contains(i - left)) continue;
				hs.add(i - left);
			}
		}
		
		long size = right - left + 1 - hs.size();
		return size;
	}
}
