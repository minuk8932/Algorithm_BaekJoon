package dynamic_programming;

/*
 * 
 * 		fibonacchi func (Top - Down) : 상향식
 * 		using for loop
 * 		
 * 		
 * 
 */

public class Fibonacchi_2 {
	private static long[] cache = null;
	private static final int MOD = 1_004;
	
	public static void main(String[] args)throws Exception{
		cache = new long[101];
		cache[1] = 1;
		cache[2] = 1;
		
		for(int n = 3; n <= 100; n++){
			cache[n] = (cache[n - 2] % MOD + cache[n -1] % MOD) % MOD; // change big num to small using mod
																		// it limited in some problems
		}
		
		System.out.println(cache[100]);
	}
}
