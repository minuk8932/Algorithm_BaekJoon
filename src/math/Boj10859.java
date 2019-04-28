package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * 
 * 	@author minchoba
 *	백준 10859번: 뒤집어진 소수
 *
 *	@see https://www.acmicpc.net/problem/10859
 *
 */
public class Boj10859 {
	private static final char[] REVERSE = {'0', '1', '2', 'X', 'X', '5', '9', 'X', '8', '6'};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		char[] num = line.toCharArray();
		
		System.out.println(isPrime(num, Long.parseLong(line)));
	}
	
	private static String isPrime(char[] arr, long origin) {
		if(!prime(origin)) return "no";							// 현재 수가 소수인가
		
		ArrayDeque<Character> stack = new ArrayDeque<>();
		for(char c: arr) {
			if(c == '3' || c == '4' || c == '7') return "no";
			stack.push(REVERSE[c - '0']);						// 뒤집어 버리기
		}
		
		String rev = "";
		while(!stack.isEmpty()) {
			rev += stack.pop();
		}
		
		long value = Long.parseLong(rev);
		if(!prime(value)) return "no";							// 뒤집은 수도 소수인가	
		
		return "yes";
	}
	
	private static boolean prime(long n) {
		if(n == 1) return false;
	    
		for(int i = 2; (long) i * i <= n; i++){
	        if(n % i == 0) return false;
	    }
	    
	    return true;
	}
}
