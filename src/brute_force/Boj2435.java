package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2435번: 기상청 인턴 신현수
 *
 *	@see https://www.acmicpc.net/problem/2435/
 *
 */
public class Boj2435 {
	private static final int MINIMUM = -1_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] temperature = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			temperature[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getMinTemp(temperature, K));			// 결과 출력
	}
	
	private static int getMinTemp(int[] arr, int k) {
		int max = MINIMUM;
		
		for(int i = 0; i < arr.length && i + k <= arr.length; i++) {		// k개의 합을 구하기 위한 범위가 초과될 경우는 제외
			int sum = getPartSum(i, k, arr);
			if(sum > max) max = sum;
		}
		
		return max;
	}
	
	private static int getPartSum(int idx, int range, int[] arr) {		
		int sum = 0;
		
		for(int i = idx; i < idx + range; i++) {
			sum += arr[i];
		}
		
		return sum;
	}
}
