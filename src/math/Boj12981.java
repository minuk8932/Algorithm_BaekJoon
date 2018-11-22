package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12981번: 공 포장하기
 *
 *	@see https://www.acmicpc.net/problem/12981/
 *
 */
public class Boj12981 {
	private static final int INF = 101;
	private static int[] res = new int[INF];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		System.out.println(calculate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
	}
	
	private static int calculate(int r, int g, int b) {
		int max = getMax(r, g, b);
		int[] arr = new int[3];
		
		for(int i = 0; i < max + 1; i++) {
			arr[0] = getMax(r, i);			// 하나씩 공을 중복없이 넣는 경우
			arr[1] = getMax(g, i);
			arr[2] = getMax(b, i);
			
			res[i] = i;
			
			process(arr[0], i);				// 같은 공만 박스에 넣는 경우
			process(arr[1], i);
			process(arr[2], i);
		}
		
		int min = INF;
		for(int i = 0; i < max + 1; i++) {		// 최소 박스 갯수
			if(min > res[i]) min = res[i];
		}
		
		return min;
	}
	
	private static int getMax(int a, int b, int c) {
		int tmp = a > b ? a : b;
		return tmp > c ? tmp : c;
	}
	
	private static int getMax(int a, int b) {
		int diff = a - b;
		return diff > 0 ? diff : 0;
	}
	
	private static void process(int ball, int idx) {
		if(ball == 0) return;
		res[idx] += (ball / 3) + mod(ball);
	}
	
	private static int mod(int num) {
		return (num % 3) == 0 ? 0 : 1;
	}
}
