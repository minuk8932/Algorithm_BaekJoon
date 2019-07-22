package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17296번: But can you do it in 0.5x A presses?
 *
 *	@see https://www.acmicpc.net/problem/17296/
 *
 */
public class Boj17296 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		double[] button = new double[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			button[i] = Double.parseDouble(st.nextToken());
		}
		
		System.out.println(counting(N, button));
	}
	
	private static int counting(int n, double[] arr) {
		boolean flag = false;
		int count = 0;
		
		for(int i = 0; i < n; i++) {
			if(arr[i] == 0) continue;
			int val = (int) (arr[i] * 10);
			int head = (int) arr[i];
			
			if(flag) {								// 이미 버튼을 눌러둔 경우
				if(head != 0) count += head;
 			}
			else {				
				if(val % 10 == 5) {					// 안눌렀는데 누르면서 들어가야하는 경우
					if(head != 0) count += head;
					count++;
				}
				else {
					count += head;
				}
				
				flag = true;
			}
		}
		
		return count;
	}
}
