package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11728번 : 배열 합치기
 *
 *	@see https://www.acmicpc.net/problem/11728/
 *
 */
public class Boj11728 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N + M];
		for(int i = 0; i < 2; i++){													// 배열 합치기
			st = new StringTokenizer(br.readLine(), SPACE);
			
			if(i == 0){
				for(int j = 0; j < N; j++){
					arr[j] = Integer.parseInt(st.nextToken());
				}
			}
			else{
				for(int j = N; j < arr.length; j++){
					arr[j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		Arrays.sort(arr);									// quick sort
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < arr.length; i++){				// 버퍼에 정렬된 배열의 값들을 담고
			sb.append(arr[i]).append(SPACE);
		}
		
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
}
