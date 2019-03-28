package hashing;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15829번: Hashing
 *
 *	@see https://www.acmicpc.net/problem/15829/
 *
 */
public class Boj15829{
    private static final int MOD = 1_234_567_891;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        char[] word = br.readLine().toCharArray();
        
        System.out.println(hashing(L, word));
    }
    
    private static long hashing(int leng, char[] arr){
        long result = 0;
        
        for(int i = 0; i < leng; i++){
            long value = arr[i] - 'a' + 1;
            result = ((result % MOD) + ((value * getPow(i)) % MOD)) % MOD;
        }
        
        return result;
    }
    
    private static long getPow(int loop) {			// 제곱수 mod 연산
    	long tmp = 1;
    	
    	while(loop-- > 0) {
    		tmp = ((tmp % MOD) * (31 % MOD)) % MOD;
    	}
    	
    	return tmp;
    }
}