package dynamic_programming;

/*
 *  	fibonacchi func (Bottom - Up) 하향
 *  	use recursion
 *  	
 *  	According to wasting space, get effective speed
 *  
 *  	it using system stack, so it can't used in huge number
 *  	=> else get error
 *  
 */

public class Fibonacchi {
	private static long[] cache = null;	// array for memorization
	
	public static void main(String[] args) throws Exception{
		cache = new long[51];	// input (max value + 1)
		
		System.out.println(fibo(50)); // input max value
	}

	private static long fibo(int n){
		
		if(cache[n] != 0){		// if you have cache array, don't use recursion again : memorization
			return cache[n];
		}
		
		if (n >= 3){
			return cache[n] = fibo(n-2) + fibo(n-1);
		}
		
		else{
			return 1;
		}
	}
}
