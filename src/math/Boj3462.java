package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3462번: 이진 스털링 수
 *
 *	@see https://www.acmicpc.net/problem/3462/
 *
 */
public class Boj3462 {
	private static final String SPACE = " ";
	private static final String END_LINE = "\n";
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int D = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(D-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			sb.append(binaryStirling((n - 1) - m / 2, (m - 1) / 2) ? 0 : 1).append(END_LINE);
		}
		System.out.println(sb.toString());
	}
	
	private static boolean binaryStirling(long n, long k) {	// n의 공집합을 뺀 나머지에서 - m / 2, m의 공집합을 뺀 나머지 / 2
		long[] sum = {getSum(n, 0), getSum(k, 0)};			// nCr = n-1Cr + n-1Cr-1
		sum[1] += getSum(n - k, 0);
		
		return sum[0] > sum[1] ? true: false;		// sum[0]이 크면 짝수
	}
	
	private static long getSum(long loop, long sum) {		
		while(loop > 0) {
			loop /= 2;
			sum += loop;
		}
		
		return sum;
	}
}
