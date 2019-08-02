import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Boj14715 {
	private static boolean[] prime = new boolean[1_000_001];
	private static ArrayList<Integer> primeList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		isPrime();
		
		System.out.println(getDivide(K));
	}
	
	private static void isPrime() {
		Arrays.fill(prime, true);
		prime[0] = prime[1] = false;
		
		for(int i = 2; i < 1_001; i++) {
			if(!prime[i]) continue;
			
			for(int j = i + i; j < prime.length; j += i) {
				prime[j] = false;
			}
		}
		
		for(int i = 0; i < prime.length; i++) {
			if(prime[i]) primeList.add(i);
		}
	}
	
	private static int getDivide(int k) {
		int count = 0;
		prime[0] = prime[1] = true;
		
		while(k > 0 && !prime[k]) {
			k = (int) Math.sqrt(k);
			count++;
		}
		
		return count;
	}
}
