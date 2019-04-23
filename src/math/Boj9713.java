package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 9713번: Sum of odd Sequence
 *
 *	@see https://www.acmicpc.net/problem/9713/
 *
 */
public class Boj9713{
    private static final String NEW_LINE = "\n";
    private static int[] seq = new int[101];
    
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		getSequence();
		
		while(T-- > 0) {
			sb.append(seq[Integer.parseInt(br.readLine())]).append(NEW_LINE);
		}
		
        System.out.println(sb.toString());
	}
	
	private static void getSequence() {
		int sum = 0;
		 
        for(int i = 1; i < 101; i++){		// 홀수 합 미리 구하기
        	if(i % 2 == 1) sum += i;
        	seq[i] = sum;
        }
	}
}
