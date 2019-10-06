package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17521번: Byte Coin
 *
 *	@see https://www.acmicpc.net/problem/17521/
 *
 */
public class Boj17521 {
	private static int[] fin;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long W = Long.parseLong(st.nextToken());
		
		int[] graph = new int[n];
		for(int i = 0; i < n; i++) {
			graph[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(getProfit(n, W, graph));
	}
	
	private static long getProfit(int n, long w, int[] arr) {
		search(n, arr);
		long coin = 0;
		
		for(int i = 0; i < n; i++) {
			if(fin[i] == 0) continue;
			
			if(fin[i] == -1) {					// 매수
				long val = w / arr[i];
				coin += val;
				w %= arr[i];
			}
			else {								// 매도
				long val = coin * arr[i];
				w += val;
				coin = 0;
			}
		}
		
		return w + arr[n - 1] * coin;
	}
	
	private static void search(int n, int[] arr) {
		fin = new int[n];
		
		boolean incre = false, decre = false;
		int prev = arr[0];
		
		for(int i = 1; i < n; i++) {			
			if(prev > arr[i]) {
				if(decre) {					// 변곡점 체크
					fin[i - 1] = 1;
					decre = false;
				}
				
				incre = true;
			}
			
			if(prev < arr[i]){
				if(incre) {
					fin[i - 1] = -1;
					incre = false;
				}
				
				decre = true;
			}
			
			prev = arr[i];
		}
		
		int first = 0;
		for(int i = 0; i < n; i++) {
			if(first != 0) break;
			
			first = fin[i];
		}
		
		if(first == 1 || (first == 0 && arr[0] < arr[n - 1])) {		// 초기 매도할지 매수할지 결정
			fin[0] = -1;
		}
	}
}
