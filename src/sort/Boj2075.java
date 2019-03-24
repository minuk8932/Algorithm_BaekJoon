package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2075번: N번째 큰 수
 *
 *	@see https://www.acmicpc.net/problem/2075/
 *
 */
public class Boj2075 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N * N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i * N + j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Arrays.sort(arr);
		System.out.println(arr[arr.length - N]);
	}
}
