package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.PriorityQueue;

/**
 * 
 * 	@author exponential-e
 *	백준 2870번: 수학숙제
 *
 *	@see https://www.acmicpc.net/problem/2870/
 *
 */
public class Boj2870 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[][] paper = new char[N][];
		for(int i = 0; i < N; i++) {
			paper[i] = br.readLine().toCharArray();
		}
		
		System.out.println(find(N, paper));
	}
	
	private static String find(int n, char[][] p) {
		PriorityQueue<BigInteger> pq = new PriorityQueue<>();
		
		for(int i = 0; i < n; i++) {
			StringBuilder make = new StringBuilder();
			
			for(char c: p[i]) {										// make number
				if(c < '0' || c > '9') {
					if(make.length() != 0) {
						pq.add(new BigInteger(make.toString()));
						make = new StringBuilder();
					}
					
					continue;
				}
				
				make.append(c);
			}
			
			if(make.length() != 0) pq.add(new BigInteger(make.toString()));
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!pq.isEmpty()) {
			sb.append(pq.poll()).append(NEW_LINE);
		}
		
		return sb.toString();
	}
}
